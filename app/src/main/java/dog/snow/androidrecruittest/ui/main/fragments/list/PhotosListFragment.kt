package dog.snow.androidrecruittest.ui.main.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.FragmentPhotosListBinding
import dog.snow.androidrecruittest.ui.main.fragments.list.adapters.PhotosListAdapter
import dog.snow.androidrecruittest.ui.main.fragments.list.model.PhotoWithExtendedInfo
import dog.snow.androidrecruittest.ui.main.fragments.list.vm.PhotosListFragmentVM
import dog.snow.androidrecruittest.ui.main.fragments.list.vm.PhotosListFragmentVMF
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class PhotosListFragment : Fragment(), KodeinAware, PhotosListAdapter.ItemInteractionListener{
    override val kodein by closestKodein()
    private val viewModelFactory by instance<PhotosListFragmentVMF>()
    private lateinit var viewModel: PhotosListFragmentVM

    private lateinit var binding: FragmentPhotosListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fun initBinding(){
            binding =  FragmentPhotosListBinding.inflate(inflater, container, false)
        }

        fun initViewModel(){
            viewModel = ViewModelProvider(this, viewModelFactory).get(PhotosListFragmentVM::class.java)
        }

        fun subscribeOnData(){
            binding.listener = this
            viewModel.getPhotosWithExtendedInfo().observe(viewLifecycleOwner, Observer {
                binding.photos = it
            })
        }

        initBinding()
        initViewModel()
        subscribeOnData()
        return binding.root
    }

    override fun onClick(item: PhotoWithExtendedInfo) {

    }
}