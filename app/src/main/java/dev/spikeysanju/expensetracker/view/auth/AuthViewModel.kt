package dev.spikeysanju.expensetracker.view.auth


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spikeysanju.expensetracker.data.local.datastore.AuthDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    application: Application
) :
    AndroidViewModel(application) {

    // init datastore
    private val authDataStore = AuthDataStore(application)

    // get authentication status
    val getAuthStatus = authDataStore.authenticated


    // save authentication status
    fun saveToDataStore(authenticated: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            authDataStore.saveToDataStore(authenticated)
        }
    }
}
