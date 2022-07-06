package com.example.submission1intermediate.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1intermediate.data.remote.Story
import com.example.submission1intermediate.databinding.ItemStoryBinding
import com.example.submission1intermediate.ui.detail.DetailStory
import com.example.submission1intermediate.ui.detail.DetailStory.Companion.DATA_USER

class StoryAdapter : ListAdapter<Story, StoryAdapter.StoryViewHolder>(DiffCallBack) {


    companion object{
        private val DiffCallBack = object : DiffUtil.ItemCallback<Story>(){
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(holder.itemView.context, story)
    }


    class StoryViewHolder(private val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, userStory: Story){
            binding.apply {
                tvUsername.text = userStory.name
                Glide.with(itemView.context)
                    .load(userStory.photoUrl)
                    .into(ivStory)
                tvDescription.text = userStory.description


                root.setOnClickListener {
                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            root.context as Activity,
                            Pair(tvUsername, "username"),
                            Pair(ivStory, "story_image"),
                            Pair(tvDescription, "description")
                        )


                    Intent(context, DetailStory::class.java ).also { intent ->
                        intent.putExtra(DATA_USER, userStory)
                        context.startActivity(intent, optionsCompat.toBundle())
                    }
                }



            }

        }

    }




}