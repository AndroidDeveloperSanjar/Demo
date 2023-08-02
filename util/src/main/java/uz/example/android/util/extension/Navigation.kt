package uz.example.android.util.extension

import androidx.navigation.NavController

fun NavController.hasDestination(label: String) = backQueue.any {
    it.destination.label == label
}

