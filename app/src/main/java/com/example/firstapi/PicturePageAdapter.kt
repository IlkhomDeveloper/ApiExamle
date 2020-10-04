package com.example.firstapi

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.plantodo.utils.extensions.inflate
import kotlinx.android.synthetic.main.main_activity_item.view.*
import kotlinx.android.synthetic.main.picture_item.view.*


class PicturePageAdapter : RecyclerView.Adapter<PicturePageAdapter.ViewHolder>() {
    private var listener: ((Item) -> Unit)? = null
    private var callback = object : SortedListAdapterCallback<Pics>(this){
        override fun compare(o1: Pics, o2: Pics): Int = o1.pic.length.compareTo(o2.pic.length)

        override fun areContentsTheSame(oldItem: Pics, newItem: Pics) = oldItem.pic == newItem.pic

        override fun areItemsTheSame(item1: Pics, item2: Pics) = item1.pic==item2.pic

    }

    private var sortedList = SortedList(Pics::class.java,callback)


    fun submitList(list: List<Pics>) {
        sortedList.beginBatchedUpdates()
        sortedList.addAll(list)
        sortedList.endBatchedUpdates()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.main_activity_item))

    override fun getItemCount(): Int = sortedList.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            itemView.apply {
                val data = sortedList[adapterPosition]
                pictureInside.loadFromUrl(data.pic)
            }
        }
    }
}