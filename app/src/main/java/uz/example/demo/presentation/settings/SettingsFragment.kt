package uz.example.demo.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yariksoffice.lingver.Lingver
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.example.android.util.extension.navigate
import uz.example.android.util.extension.singleClick
import uz.example.demo.App
import uz.example.demo.databinding.FragmentSettingsBinding
import uz.example.demo.utils.IdResource
import uz.example.demo.utils.LayoutResource
import uz.example.demo.utils.StringResource

class SettingsFragment : Fragment(LayoutResource.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding()
    private val vm: SettingsViewModel by viewModel()

    private val uz by lazy {
        getString(StringResource.uz)
    }

    private val en by lazy {
        getString(StringResource.en)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnUzb.singleClick {
                Lingver.getInstance().setLocale(context = requireContext(), language = uz)
                vm.changeLanguage(uz)
                requireActivity().recreate()
            }

            btnEng.singleClick {
                Lingver.getInstance().setLocale(context = requireContext(), language = en)
                vm.changeLanguage(en)
                requireActivity().recreate()
            }

            tvHelloWorld.singleClick {
                navigate(IdResource.to_root)
            }

            btnLight.singleClick {
                vm.changeTheme(getString(StringResource.light))
                (requireActivity().application as App).setupTheme()
                requireActivity().recreate()
            }

            btnNight.singleClick {
                vm.changeTheme(getString(StringResource.night))
                (requireActivity().application as App).setupTheme()
                requireActivity().recreate()
            }
        }
    }
}