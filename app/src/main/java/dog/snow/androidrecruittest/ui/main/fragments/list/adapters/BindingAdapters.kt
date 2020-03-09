package dog.snow.androidrecruittest.ui.main.fragments.list.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.main.fragments.list.viewmodel.model.PhotoWithExtendedInfo

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("android:photosList", "android:itemInteractionListener")
        fun setPhotosList(recyclerView: RecyclerView,
            photos: List<PhotoWithExtendedInfo>?,
            itemInteractionListener: PhotosListAdapter.ItemInteractionListener?
        ) {
            if (photos == null || itemInteractionListener == null) return

            val layoutManager = recyclerView.layoutManager
            val context = recyclerView.context

            if (layoutManager == null) {
                recyclerView.layoutManager = LinearLayoutManager(context)
            }

            if (recyclerView.adapter == null) {
                val driversAdapter = PhotosListAdapter(
                    itemInteractionListener
                )

                recyclerView.adapter = driversAdapter

                driversAdapter.submitList(photos)
            } else {
                val driversAdapter = recyclerView.adapter as PhotosListAdapter

                driversAdapter.submitList(photos)
            }
        }

        @JvmStatic
        @BindingAdapter("android:imageUrl")
        fun loadImage(imageView: ImageView, imageUrl: String?) {
            if (imageUrl == null) return

            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView)
        }
    }


}