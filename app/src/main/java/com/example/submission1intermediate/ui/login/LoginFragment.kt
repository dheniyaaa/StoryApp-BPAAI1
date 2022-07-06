package com.example.submission1intermediate.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.submission1intermediate.MainActivity
import com.example.submission1intermediate.R
import com.example.submission1intermediate.databinding.LoginFragmentBinding
import com.example.submission1intermediate.utils.ViewModelFactory
import com.example.submission1intermediate.vstate.State
import com.google.android.material.snackbar.Snackbar
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LoginFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action()

    }

    private fun action(){
        binding.apply {
            btnLogin.setOnClickListener { loginUser() }
            btnRegister.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment) )
        }
    }



    private fun loginUser(){
        val email = binding.tiEmailLogin.text.toString().trim()
        val password = binding.tiPasswordLogin.text.toString().trim()

        lifecycleScope.launchWhenResumed {
            loginViewModel.userLogin(email, password).observe(viewLifecycleOwner){
                it.let { resource ->
                    when(resource.status){
                        State.SUCCESS ->{
                            it.data?.body?.loginResult?.token?.let { token ->
                                loginViewModel.saveAuthToken(token)
                                Intent(requireContext(), MainActivity::class.java).also { intent ->
                                    intent.putExtra(MainActivity.EXTRA_TOKEN, token )
                                    startActivity(intent)
                                    requireActivity().finish()
                                }
                            }
                            Toast.makeText(requireContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show()

                        }
                        State.ERROR ->{
                            Snackbar.make(binding.root, getString(R.string.login_failure), Snackbar.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}