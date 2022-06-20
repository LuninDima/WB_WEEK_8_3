package com.example.wb_week_8_3.view.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_week_8_3.R
import com.example.wb_week_8_3.model.data.CatModel
import com.facebook.drawee.view.SimpleDraweeView

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.RecyclerItemViewHolder>() {

    private var data: List<CatModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<CatModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_favorite_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: CatModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.apply {
                    val favoriteImage = findViewById<SimpleDraweeView>(R.id.favoriteFragmentDraw)
                    favoriteImage.setImageURI(data.url)
                }
            }
        }

    }

}