package uz.example.demo.presentation.sign.login

import uz.example.android.core.sharedpreferences.storages.UserStorage
import uz.example.demo.base.BaseViewModel

class LoginViewModel(
    private val userStorage: UserStorage
) : BaseViewModel() {

    fun setUserIsLogin(isLogin: Boolean) {
        userStorage.isLogin.updateAsync(isLogin)
    }

}