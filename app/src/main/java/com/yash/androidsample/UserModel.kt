package com.yash.androidsample

class UserModel() {
    var username: String? = null
    var userpassword: String? = null

    constructor(name: String, pwd: String) : this() {
        this.username = name
        this.userpassword = pwd
    }
}