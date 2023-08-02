package uz.example.android.util.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface EventSource<EVENT> {
    val events: Flow<EVENT>
    suspend fun sendEvent(event: EVENT)
}

fun <VM, EVENT> VM.event(event: EVENT) where VM : EventSource<EVENT>, VM : ViewModel {
    viewModelScope.launch { sendEvent(event) }
}
