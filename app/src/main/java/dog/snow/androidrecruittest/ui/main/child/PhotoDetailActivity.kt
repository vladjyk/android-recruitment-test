package dog.snow.androidrecruittest.ui.main.child

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.ActivityPhotoDetailBinding
import dog.snow.androidrecruittest.ui.main.child.vm.PhotoDetailActivityVM
import dog.snow.androidrecruittest.ui.main.child.vm.PhotoDetailActivityVMF
import kotlinx.android.synthetic.main.activity_photo_detail.*
import kotlinx.android.synthetic.main.appbar_child_layout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

const val EXTRA_PHOTO_ID = "dog.snow.androidrecruittest.ui.main.child.photoid"

class PhotoDetailActivity : AppCompatActivity(), KodeinAware{
    override val kodein by closestKodein()
    private val viewModelFactory by instance<PhotoDetailActivityVMF>()
    private lateinit var viewModel: PhotoDetailActivityVM

    private lateinit var binding: ActivityPhotoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        fun initBinding(){
            binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)
        }

        fun initActionBar(){
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        fun initViewModel(){
            viewModel = ViewModelProvider(this, viewModelFactory).get(PhotoDetailActivityVM::class.java)
        }

        fun bindData(){
            if (intent != null && intent.hasExtra(EXTRA_PHOTO_ID)){
                val photoId = intent.getIntExtra(EXTRA_PHOTO_ID, -1)

                GlobalScope.launch {
                    binding.photoDetail = viewModel.getPhotoDetail(photoId)
                }
            }
        }

        super.onCreate(savedInstanceState)
        initBinding()
        initActionBar()
        initViewModel()
        bindData()
    }
}