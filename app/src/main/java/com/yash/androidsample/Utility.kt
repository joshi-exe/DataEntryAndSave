package com.yash.androidsample

class Utility private constructor() {

    companion object {
        val Instance = Utility()
    }

    var loggedinuser: UserModel? = null

}