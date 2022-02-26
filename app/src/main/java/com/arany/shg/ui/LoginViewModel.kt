package com.arany.shg.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    private var _phoneNumber = MutableLiveData<String>()
    private var _password = MutableLiveData<String>()

    var phoneNumber:LiveData<String> = _phoneNumber
    var password:LiveData<String> = _password

    fun setPassword(inputPassword: String){
        _password.value = inputPassword
    }

    fun setPhoneNumber(inputPhoneNumber: String){
        _phoneNumber.value = inputPhoneNumber
    }


}