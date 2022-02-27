package com.edgetechs.unihelp.Admin.frags

import android.location.Address
class DataHelper1 {

    lateinit var category :String
    lateinit var Description:String
    lateinit var Code:String




    constructor(){}

    constructor(
        category: String,
        Description: String,
        Code: String,
        ) {
        this.category= category
        this. Description =  Description
        this.Code= Code
    }
}