package ru.itis.effectivemobiletesttask.feature_main.impl.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.effectivemobiletesttask.core_ui.WindowUtils
import ru.itis.effectivemobiletesttask.core_utils.launchAndCollectIn
import ru.itis.effectivemobiletesttask.feature_main.R
import ru.itis.effectivemobiletesttask.feature_main.databinding.FragmentMainBinding
import ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter.CourseItemModel
import ru.itis.effectivemobiletesttask.feature_main.impl.ui.adapter.CourseAdapter

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

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
        setupBarsColor()
        setupRecyclerView()
        setupClickListeners()

        viewModel.uiState.launchAndCollectIn(viewLifecycleOwner) { state ->
            adapter.items = state.courses.map { course ->
                CourseItemModel(
                    course = course,
                    onFavoriteClick = viewModel::onFavoriteClick
                )
            }
            binding.progressBar.isVisible = state.isLoading

            val sortText = if (state.isSorted) {
                getString(R.string.original_format)
            } else {
                getString(R.string.sort_by_date_desc)
            }
            binding.sortButton.text = sortText

            if (state.error != null) {
                Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupBarsColor() {
        WindowUtils.setSystemBarsColor(
            requireActivity(),
            ru.itis.effectivemobiletesttask.core_ui.R.color.color_dark,
            ru.itis.effectivemobiletesttask.core_ui.R.color.color_dark_gray
        )
    }

    private fun setupRecyclerView() {
        adapter = CourseAdapter()
        binding.coursesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainScreenFragment.adapter
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
