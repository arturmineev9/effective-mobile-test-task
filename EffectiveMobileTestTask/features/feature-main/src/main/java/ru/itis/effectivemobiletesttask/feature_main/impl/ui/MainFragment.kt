package ru.itis.effectivemobiletesttask.feature_main.impl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.effectivemobiletesttask.core_utils.launchAndCollectIn
import ru.itis.effectivemobiletesttask.feature_main.databinding.FragmentMainBinding
import ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter.CourseAdapter
import ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter.CourseItemModel


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupClickListeners()

        viewModel.uiState.launchAndCollectIn(viewLifecycleOwner) { state ->
            adapter.items = state.courses.map { course ->
                CourseItemModel(
                    course = course,
                    onFavoriteClick = { viewModel.onFavoriteClick(it) },
                    onDetailsClick = { /* TODO */ }
                )
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = CourseAdapter(
            onFavoriteClick = {},
            onDetailsClick = {}
        )
        binding.coursesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = this@MainFragment.adapter
        }
    }

    private fun setupClickListeners() {
        binding.sortButton.setOnClickListener {
            viewModel.onSortClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}