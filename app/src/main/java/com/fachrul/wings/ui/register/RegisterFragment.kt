package com.fachrul.wings.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fachrul.wings.data.entity.LoginEntity
import com.fachrul.wings.databinding.FragmentLoginBinding
import com.fachrul.wings.databinding.FragmentRegisterBinding
import com.fachrul.wings.view_model.LoginViewModel
import com.fachrul.wings.view_model.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment:Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding
    private val vm: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        initBinding()
        return binding?.root
    }
    fun initBinding(){
        binding?.register?.setOnClickListener {
            if (binding?.usernameEt?.text.isNullOrEmpty() || binding?.passwordEt?.text.isNullOrEmpty()){
                Toast.makeText(requireContext(),"Username dan password tidak boleh kosong",Toast.LENGTH_SHORT).show()
            } else {
                vm.register(LoginEntity(binding?.usernameEt?.text.toString(),binding?.passwordEt?.text.toString()))
                Toast.makeText(requireContext(),"Register berhasil",Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }
}