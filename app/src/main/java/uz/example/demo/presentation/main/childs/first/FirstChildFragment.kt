package uz.example.demo.presentation.main.childs.first

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.example.android.util.extension.singleClick
import uz.example.demo.R
import uz.example.demo.databinding.FragmentFirstChildBinding
import uz.example.demo.utils.IdResource

class FirstChildFragment : Fragment(R.layout.fragment_first_child) {

    private val binding: FragmentFirstChildBinding by viewBinding()
    private val controller by lazy {
        Navigation.findNavController(
            requireActivity(), R.id.fc_view
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToSettings.singleClick {
            controller.navigate(
                IdResource.to_settingsFragment
            )
        }
    }
}