package com.example.aria.a0920.Data

import android.accounts.Account

class Data {
    var username :String =""
    var account :String =""
    var password : String =""
    var email : String =""
    var phone : String =""
    var api_token : String =""
constructor(username: String, account: String, password:String, email:String, phone:String){
    this.username=username
    this.account=account
    this.password=password
    this.email=email
    this.phone=phone
}

//class Data {
//    var id: Int =0
//    var username :String =""
//    var account :String =""
//    var email : String =""
//    var phone : String =""
////    var created_at : String =""
////    var updated_at : String =""
//    constructor(id:Int,  account: String, username: String, email:String, phone:String){
//        this.id=id
//        this.username=username
//        this.account=account
//        this.email=email
//        this.phone=phone
////        this.created_at =created_at
////        this.updated_at =updated_at
//    }
}