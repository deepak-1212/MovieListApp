package deepak.developer.movie_app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import deepak.developer.movie_app.databinding.FragmentMovieBinding
import deepak.developer.movie_app.ui.adapters.MoviePagingAdapter
import deepak.developer.movie_app.ui.view_models.MovieViewModel

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var movieFragmentBinding: FragmentMovieBinding? = null
    private val viewModel: MovieViewModel by viewModels()
    private var movieAdapter: MoviePagingAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieFragmentBinding = FragmentMovieBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return movieFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MoviePagingAdapter()
        movieFragmentBinding?.movieRecycler?.adapter = movieAdapter

        movieFragmentBinding?.movieSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let {
//                    viewModel.setQuery(it)
//                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.setQuery(it)
                }
                return false
            }
        })


        movieAdapter?.onMovieClick {
            val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        viewModel.list.observe(viewLifecycleOwner) {
            movieAdapter?.submitData(lifecycle, it)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

        movieFragmentBinding = null
        movieAdapter = null
    }

}