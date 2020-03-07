package dog.snow.androidrecruittest.ui.main.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.FragmentPhotoDetailBinding
import dog.snow.androidrecruittest.ui.main.fragments.detail.vm.PhotoDetailFragmentVM
import dog.snow.androidrecruittest.ui.main.fragments.detail.vm.PhotoDetailFragmentVMF
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

private const val LAYOUT = R.layout.fragment_photo_detail
const val KEY_PHOTO_ID = "photo id key"

class PhotoDetailFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory by instance<PhotoDetailFragmentVMF>()
    private lateinit var viewModel: PhotoDetailFragmentVM

    private lateinit var binding: FragmentPhotoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fun initDataBinding() {
            binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        }

        fun initViewModel(){
            viewModel = ViewModelProvider(this, viewModelFactory).get(PhotoDetailFragmentVM::class.java)
        }

        fun bindData(){
            GlobalScope.launch {
                arguments?.getInt(KEY_PHOTO_ID)?.let {
                    binding.photoDetail = viewModel.getPhotoDetail(it)
                }
            }
        }

        initDataBinding()
        initViewModel()
        bindData()
        return binding.root
    }

}