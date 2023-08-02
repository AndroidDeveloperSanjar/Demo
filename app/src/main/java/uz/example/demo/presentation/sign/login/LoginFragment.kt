package uz.example.demo.presentation.sign.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.example.android.util.extension.navigate
import uz.example.android.util.extension.singleClick
import uz.example.demo.R
import uz.example.demo.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val vm: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.singleClick {
            vm.setUserIsLogin(true)
            navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}