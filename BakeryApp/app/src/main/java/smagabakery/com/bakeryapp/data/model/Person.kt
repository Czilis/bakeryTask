package smagabakery.com.bakeryapp.data.model

import org.joda.time.DateTime

data class Person(val firstName:String, val lastName:String, val avatarUrl: String?, val birthDay:DateTime, val description:String?)