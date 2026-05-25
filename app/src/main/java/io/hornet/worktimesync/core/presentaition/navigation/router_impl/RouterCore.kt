package io.hornet.worktimesync.core.presentaition.navigation.router_impl

import androidx.navigation.NavHostController

class RouterCore {
    var navController: NavHostController? = null

    fun attachNavController(navController: NavHostController) {
        this.navController = navController
    }
    fun detachNavController() {
        this.navController = null
    }
}