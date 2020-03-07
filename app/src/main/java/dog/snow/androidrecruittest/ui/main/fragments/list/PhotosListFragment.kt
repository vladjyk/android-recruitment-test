package dog.snow.androidrecruittest.ui.main.fragments.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.databinding.FragmentPhotosListBinding
import dog.snow.androidrecruittest.extend.view.hide
import dog.snow.androidrecruittest.extend.view.show
import dog.snow.androidrecruittest.ui.main.MainActivity
import dog.snow.androidrecruittest.ui.main.fragments.list.adapters.PhotosListAdapter
import dog.snow.androidrecruittest.ui.main.fragments.list.model.PhotoWithExtendedInfo
import dog.snow.androidrecruittest.ui.main.fragments.list.vm.PhotosListFragmentVM
import dog.snow.androidrecruittest.ui.main.fragments.list.vm.PhotosListFragmentVMF
import kotlinx.android.synthetic.main.fragment_photos_list.*
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
            viewModel.fetchAll()
        }

        initBinding()
        initViewModel()
        subscribeOnData()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        fun inflateMenu(){
            inflater.inflate(R.menu.photos_list_fragment_menu, menu)
        }

        fun initSearch(){
            val searchView = menu.findItem(R.id.action_search).actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty())
                        viewModel.fetchAll()
                    else viewModel.fetchWithTitle(newText)

                    return false
                }
            })
        }

        inflateMenu()
        initSearch()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(item: PhotoWithExtendedInfo) {
        (context as MainActivity).showDetailActivity(item.id)
    }
}