package deepak.developer.movie_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import deepak.developer.movie_app.R
import deepak.developer.movie_app.databinding.FragmentDetailsBinding
import deepak.developer.movie_app.databinding.FragmentMovieBinding


class MovieDetailsFragment : Fragment() {

    private var movieDetailFragmentBinding: FragmentDetailsBinding? = null

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


    }

    override fun onDestroyView() {
        super.onDestroyView()

        movieDetailFragmentBinding = null
    }
}