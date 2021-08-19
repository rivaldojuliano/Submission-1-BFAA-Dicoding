package dev.codewithrivaldo.mysubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.codewithrivaldo.mysubmission.databinding.ItemUserRowBinding
import dev.codewithrivaldo.mysubmission.model.data.User

class CardViewUserAdapter(private val listUser: ArrayList<User>): RecyclerView.Adapter<CardViewUserAdapter.CardViewUserHolder>() {
    inner class CardViewUserHolder(private val binding: ItemUserRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(imgAvatar)

                tvName.text = user.name
                tvUsername.text = user.username
                tvCity.text = user.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewUserHolder {
        val binding = ItemUserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewUserHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewUserHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}