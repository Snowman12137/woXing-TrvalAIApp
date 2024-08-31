package com.example.kamteamapp.ui.login.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var isLoginSuccessful = false
        private set

    fun login(username: String, password: String,repassowrd:String):Boolean {
        viewModelScope.launch {
            // 假设这里进行登录请求
            // 根据请求结果更新 isLoginSuccessful 状态
            // 例如，这里我们简单模拟登录成功
            isLoginSuccessful = true
        }
        return isLoginSuccessful
    }
}