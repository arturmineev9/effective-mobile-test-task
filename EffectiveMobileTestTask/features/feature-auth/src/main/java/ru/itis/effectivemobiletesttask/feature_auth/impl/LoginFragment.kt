package ru.itis.effectivemobiletesttask.feature_auth.impl

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.itis.effectivemobiletesttask.core_utils.observe
import ru.itis.effectivemobiletesttask.feature_auth.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.isLoginEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.loginButton.isEnabled = isEnabled
        }
    }

    private fun setupClickListeners() {
        binding.emailInput.addTextChangedListener {
            viewModel.onEmailChanged(it.toString())
        }

        binding.passwordInput.addTextChangedListener {
            viewModel.onPasswordChanged(it.toString())
        }

        binding.loginButton.setOnClickListener {
            viewModel.onLoginClicked()
            //findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        binding.registerLink.setOnClickListener {
            // TODO
        }

        binding.forgotPasswordLink.setOnClickListener {
            // TODO
        }

        binding.vkButton.setOnClickListener {
            openUrl("https://vk.com/")
        }

        binding.okButton.setOnClickListener {
            openUrl("https://ok.ru/")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
