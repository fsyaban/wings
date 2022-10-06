package com.fachrul.wings.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fachrul.wings.data.entity.LoginEntity
import com.fachrul.wings.data.entity.Result
import com.fachrul.wings.databinding.FragmentLoginBinding
import com.fachrul.wings.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private val vm: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        initBinding()
        observeLiveData()
        return binding?.root
    }

    private fun initBinding() {
        binding?.login?.setOnClickListener {
            if (binding?.usernameEt?.text.isNullOrEmpty() || binding?.passwordEt?.text.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Username dan password tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                vm.login(
                    LoginEntity(
                        binding?.usernameEt?.text.toString(),
                        binding?.passwordEt?.text.toString()
                    )
                )
            }
        }

        binding?.register?.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.loginToRegister())
        }
    }

    private fun observeLiveData(){
        vm.loginState?.observe(viewLifecycleOwner){
            when(it){
                is Result.Success -> {
                    findNavController().navigate(LoginFragmentDirections.loginToProduct())
                }
                is Result.Error->{
                    Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}