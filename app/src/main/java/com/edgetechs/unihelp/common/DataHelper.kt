package com.edgetechs.unihelp.common

import android.location.Address

class DataHelper {

    lateinit var fullname :String
    lateinit var institutename:String
    lateinit var enrolment:String
    lateinit var emailAddress:String
    lateinit var mobile:String
    lateinit var password:String



    constructor(){}

    constructor(
        fullname: String,
        institutename: String,
        enrolment: String,
        emailAdress: String,
        mobile: String,
        password: String,

    ) {
        this.fullname = fullname
        this.institutename = institutename
        this.enrolment=enrolment
        this.emailAddress = emailAdress
        this.mobile = mobile
        this.password = password

    }
}