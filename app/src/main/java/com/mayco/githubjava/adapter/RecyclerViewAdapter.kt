package com.mayco.githubjava.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mayco.githubjava.R
import com.mayco.githubjava.network.response.GitHubResponse
import kotlin.properties.Delegates

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var items: List<GitHubResponse> by Delegates.observable(emptyList()) { _, old, new ->
        if (old != new) notifyDataSetChanged()
    }

    fun setList(git: List<GitHubResponse>) {
        this.items = git
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_github, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.avatar)
        private val discretion = itemView.findViewById<TextView>(R.id.discretion)
        private val username = itemView.findViewById<TextView>(R.id.username)

        fun bind(gitHub: GitHubResponse) {

            discretion.text = gitHub.bio
            username.text = gitHub.name

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.git_branch)
                .error(R.drawable.ic_star)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(gitHub.avatar_url)
                .into(image)
//            Glide.with(name.context).load(gitHub.bio).into(name)
        }
    }
}
