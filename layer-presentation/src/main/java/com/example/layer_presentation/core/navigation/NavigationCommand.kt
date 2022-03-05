package com.example.layer_presentation.core.navigation

import android.os.Bundle
import androidx.navigation.NavController

interface NavigationCommand {
    var args: Bundle?
    fun execute(navController: NavController)
}