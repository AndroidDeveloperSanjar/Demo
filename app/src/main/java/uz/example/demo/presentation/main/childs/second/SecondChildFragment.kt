package uz.example.demo.presentation.main.childs.second

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import uz.example.demo.R

class SecondChildFragment : Fragment(R.layout.fragment_second_child) {

    private val controller by lazy {
        Navigation.findNavController(
            requireActivity(), R.id.fc_view
        )
    }

}