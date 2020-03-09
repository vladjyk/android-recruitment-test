package dog.snow.androidrecruittest.ui.main.detail

import android.os.Bundle
import android.transition.Fade
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.ActivityPhotoDetailBinding
import dog.snow.androidrecruittest.ui.main.detail.viewmodel.PhotoDetailActivityVM
import dog.snow.androidrecruittest.ui.main.detail.viewmodel.PhotoDetailActivityVMF
import kotlinx.android.synthetic.main.appbar_child_layout.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

const val EXTRA_PHOTO_ID = "dog.snow.androidrecruittest.ui.main.detail.photoid"

class PhotoDetailActivity : AppCompatActivity(), KodeinAware{
    override val kodein by closestKodein()
    private val viewModelFactory by instance<PhotoDetailActivityVMF>()
    private lateinit var viewModel: PhotoDetailActivityVM

    private lateinit var binding: ActivityPhotoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        fun initBinding(){
            binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)
        }

        fun excludeTransitionAnimation(){
            val fade = Fade()
            fade.excludeTarget(R.id.appbar, true)
            fade.excludeTarget(android.R.id.statusBarBackground, true)
            fade.excludeTarget(android.R.id.navigationBarBackground, true)

            window.enterTransition = fade
            window.exitTransition = fade
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
                    val photoDetail = viewModel.getPhotoDetail(photoId)
                    binding.photoDetail = photoDetail
                    launch (Main) {
                        title = photoDetail.photoTitle
                    }
                }
            }
        }

        super.onCreate(savedInstanceState)
        initBinding()
        excludeTransitionAnimation()
        initActionBar()
        initViewModel()
        bindData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}