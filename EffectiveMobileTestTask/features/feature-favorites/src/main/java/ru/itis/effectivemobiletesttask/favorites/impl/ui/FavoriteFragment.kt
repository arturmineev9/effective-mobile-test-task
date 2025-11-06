package ru.itis.effectivemobiletesttask.favorites.impl.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.effectivemobiletesttask.core_utils.launchAndCollectIn
import ru.itis.effectivemobiletesttask.favorites.R
import ru.itis.effectivemobiletesttask.favorites.databinding.FragmentFavoriteBinding
import ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter.CourseAdapter
import ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter.CourseItemModel

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.uiState.launchAndCollectIn(viewLifecycleOwner) { state ->
            adapter.items = state.courses.map { course ->
                CourseItemModel(
                    course = course,
                    onFavoriteClick = { viewModel.onFavoriteClick(it) },
                    onDetailsClick = { }
                )
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = CourseAdapter()
        binding.favoriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FavoriteFragment.adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
