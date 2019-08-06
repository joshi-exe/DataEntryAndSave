package com.yash.androidsample

class Utility private constructor() {

    companion object {
        val Instance = Utility()
        var alertPref: String = "switch_pref_"
        var statusPref: String ="status_pref_"
    }

    var loggedinuser: UserModel? = null

}