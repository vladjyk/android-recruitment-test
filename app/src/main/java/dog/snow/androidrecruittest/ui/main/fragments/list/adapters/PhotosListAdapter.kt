package dog.snow.androidrecruittest.ui.main.fragments.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.PhotosListItemBinding
import dog.snow.androidrecruittest.ui.main.fragments.list.model.PhotoWithExtendedInfo

private val ITEM_LAYOUT = R.layout.photos_list_item

class PhotosListAdapter(private val listener: ItemInteractionListener) :
    ListAdapter<PhotoWithExtendedInfo, PhotosListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotosListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: PhotosListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotoWithExtendedInfo) {
            fun setItemClickListener(){
                binding.root.setOnClickListener {
                    listener.onClick(item, binding.ivThumb)
                }
            }

            binding.item = item
            binding.executePendingBindings()
            setItemClickListener()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PhotoWithExtendedInfo>() {
            override fun areItemsTheSame(oldItem: PhotoWithExtendedInfo, newItem: PhotoWithExtendedInfo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoWithExtendedInfo, newItem: PhotoWithExtendedInfo): Boolean =
                oldItem == newItem
        }
    }

    interface ItemInteractionListener{
        fun onClick(item: PhotoWithExtendedInfo, imageView: ImageView)
    }
}