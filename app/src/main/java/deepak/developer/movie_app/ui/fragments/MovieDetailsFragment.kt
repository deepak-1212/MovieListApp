package deepak.developer.movie_app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import deepak.developer.movie_app.databinding.FragmentDetailsBinding
import deepak.developer.movie_app.ui.view_models.MovieViewModel
import deepak.developer.movie_app.utils.Status

private const val TAG = "MovieDetailsFragmentTAG"
@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var movieDetailFragmentBinding: FragmentDetailsBinding? = null
    private val viewModel: MovieViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailFragmentBinding = FragmentDetailsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return movieDetailFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        movieDetailFragmentBinding?.backPress?.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel.getMovieDetails(args.imdbId!!)

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            when (it.getContentIfNotHandled()?.status) {

                Status.LOADING -> {
                    movieDetailFragmentBinding?.detailsProgress?.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    movieDetailFragmentBinding?.detailsProgress?.visibility = View.GONE
                    Log.i(TAG, "onViewCreated ERROR: ${it.getContentIfNotHandled()}")
                }

                Status.SUCCESS -> {
                    movieDetailFragmentBinding?.detailsProgress?.visibility = View.GONE

                    movieDetailFragmentBinding?.movieDetails = it.peekContent().data

                }

                null -> {

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        movieDetailFragmentBinding = null
    }
}