package com.example.submission1intermediate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission1intermediate.data.remote.Story
import com.example.submission1intermediate.databinding.ActivityMainBinding
import com.example.submission1intermediate.ui.StoryAdapter
import com.example.submission1intermediate.ui.addstory.AddStory
import com.example.submission1intermediate.ui.login.AuthActivity
import com.example.submission1intermediate.utils.ViewModelFactory
import com.example.submission1intermediate.vstate.State
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listStoryAdapter: StoryAdapter
    private lateinit var recyclerView: RecyclerView
    private var token: String = ""

    companion object{
        const val EXTRA_TOKEN = "extra_token"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = obtainViewModel(this@MainActivity)

        token = intent.getStringExtra(EXTRA_TOKEN)!!

        setSwipeRefreshLayout()
        setRecyclerView()
        getStory()

        binding.btnAddStory.setOnClickListener {
            Intent(this, AddStory::class.java).also {
                startActivity(it)
            }
        }
       binding.btnLogout.setOnClickListener {
           mainViewModel.saveAuthToken("")
           val intent =  Intent(this, AuthActivity::class.java)
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
           startActivity(intent)
           finish()

        }


    }

   private fun getStory(){
       binding.swipeRefresh.isRefreshing = true

        lifecycleScope.launch {
           lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
                mainViewModel.getAllStory(token).observeForever{
                    it.let { resource ->

                        when(resource.status){
                            State.SUCCESS -> {
                                updateRecyclerViewData(it.data!!.body.stories)
                                binding.swipeRefresh.isRefreshing =false

                            }
                            State.ERROR -> {
                                Toast.makeText(this@MainActivity, "An error occurred", Toast.LENGTH_SHORT).show()
                                binding.swipeRefresh.isRefreshing = false
                            }
                        }
                    }
                }

           }
        }
    }

    private fun setRecyclerView(){
       val linearLayoutManager = LinearLayoutManager(this)
        listStoryAdapter = StoryAdapter()
        recyclerView = binding.rvStory
        recyclerView.apply {
            adapter = listStoryAdapter
            layoutManager = linearLayoutManager
        }

    }

    private fun updateRecyclerViewData(story: List<Story>){
        val recyclerViewState = recyclerView.layoutManager?.onSaveInstanceState()
        listStoryAdapter.submitList(story)
        recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

    private fun setSwipeRefreshLayout(){
        binding.swipeRefresh.setOnRefreshListener {
            getStory()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel{
        return ViewModelProvider(activity, viewModelFactory)[MainViewModel::class.java]
    }


















}