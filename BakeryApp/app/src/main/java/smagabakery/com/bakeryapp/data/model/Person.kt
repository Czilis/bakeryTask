package smagabakery.com.bakeryapp.data.model

import java.util.*

class Person(val firstName: String, val lastName: String, val avatarUrl: String?, val birthDay: String, val description: String?, val id: Long = Random().nextLong())