package com.example.submission1intermediate.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.submission1intermediate.data.remote.Story
import com.example.submission1intermediate.databinding.ActivityDetailStoryBinding
import com.example.submission1intermediate.utils.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class DetailStory : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelFactory by  instance()
    private lateinit var  detailStoryViewModel: DetailStoryViewModel
    private lateinit var binding: ActivityDetailStoryBinding


    companion object{
        const val DATA_USER = "DATA_USER"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportPostponeEnterTransition()

        val story = intent.getParcelableExtra<Story>(DATA_USER)
        setDetailStory(story)

        binding.narrowBack.setOnClickListener { onSupportNavigateUp() }

    }

    private fun setDetailStory(dataStory: Story?){
        if (dataStory != null){
            with(binding){
                tvUsernameDetail.text = dataStory.name

                Glide.with(this@DetailStory)
                    .load(dataStory.photoUrl)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            TODO("Not yet implemented")
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            supportStartPostponedEnterTransition()
                            return false
                        }

                    })
                    .into(ivDetailStory)

                tvDescription.text = dataStory.description
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}