package com.example.firstapi

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.plantodo.utils.extensions.inflate
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.main_activity_item.view.*


class MainPageAdapter : RecyclerView.Adapter<MainPageAdapter.ViewHolder>() {
    private var listener: ((Item) -> Unit)? = null
    private var callback = object : SortedListAdapterCallback<Item>(this){

        override fun areItemsTheSame(item1: Item, item2: Item) = item1.tel_raqami == item2.tel_raqami

        override fun compare(o1: Item, o2: Item) = o1.full_name.length.compareTo(o2.full_name.length)

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

    }

    private var sortedList = SortedList(Item::class.java,callback)


    fun submitList(list: List<Item>) {
        sortedList.beginBatchedUpdates()
        sortedList.addAll(list)
        sortedList.endBatchedUpdates()
    }

    fun  setOnClick(f : (Item) -> Unit){
        listener = f
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.main_activity_item))

    override fun getItemCount(): Int = sortedList.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            itemView.apply {
                val data = sortedList[adapterPosition]
                val imageUrl = data.image
                userName.text = data.full_name
                userPhone.text = data.tel_raqami
                Picasso.get().load(imageUrl).into(findViewById<CircleImageView>(R.id.mainPicture))
                cardView.setOnClickListener { listener?.invoke(data) }
            }
        }
    }
}