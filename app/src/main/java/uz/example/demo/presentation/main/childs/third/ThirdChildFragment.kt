package uz.example.demo.presentation.main.childs.third

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import uz.example.demo.R

class ThirdChildFragment : Fragment(R.layout.fragment_third_child) {

    private val controller by lazy {
        Navigation.findNavController(
            requireActivity(), R.id.fc_view
        )
    }

}