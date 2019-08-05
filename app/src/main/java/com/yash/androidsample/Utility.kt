package com.yash.androidsample

class Utility private constructor() {

    companion object {
        val Instance = Utility()
        var alertPref: String = "switch_pref"
    }

    var loggedinuser: UserModel? = null

}