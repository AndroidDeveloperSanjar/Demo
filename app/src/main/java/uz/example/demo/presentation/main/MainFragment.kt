package uz.example.demo.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.example.demo.R
import uz.example.demo.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val vm: MainViewModel by viewModel()

    private val controller by lazy {
        (childFragmentManager.findFragmentById(R.id.fc_view) as NavHostFragment).navController
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            bnView.setOnItemSelectedListener {
                it.itemId.let(controller::navigate)
                return@setOnItemSelectedListener true
            }


        }
    }
}