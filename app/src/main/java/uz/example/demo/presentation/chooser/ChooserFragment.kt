package uz.example.demo.presentation.chooser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.example.android.util.extension.navigate
import uz.example.demo.R

class ChooserFragment : Fragment(R.layout.fragment_chooser) {

    private val vm: ChooserViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (vm.userIsLogin()) {
            navigate(R.id.action_chooserFragment_to_mainFragment)
        } else {
            navigate(R.id.action_chooserFragment_to_loginFragment)
        }
    }
}