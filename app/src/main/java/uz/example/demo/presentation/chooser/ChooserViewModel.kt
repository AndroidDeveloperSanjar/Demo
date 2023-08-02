package uz.example.demo.presentation.chooser

import androidx.lifecycle.ViewModel
import uz.example.android.core.sharedpreferences.storages.UserStorage

class ChooserViewModel(
    private val userStorage: UserStorage
) : ViewModel() {

    fun userIsLogin() = userStorage.isLogin.value

}