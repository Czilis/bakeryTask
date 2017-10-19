package smagabakery.com.bakeryapp.data.remote

import com.google.gson.JsonObject
import smagabakery.com.bakeryapp.data.model.Person

object PersonDeserializer {

    fun deserialize(json: JsonObject): Person =
            with(json) {
                try {
                    val firstName = getString("firstNamE")
                    val lastName = getString("last_name")
                    val avatarUrl = getNullableStringWithMultipleKeys(listOf("avatar", "avataR"))
                    val birthDay = getString("bDay")
                    val description = getNullable("description")
                    Person(firstName, lastName, avatarUrl, birthDay, description)
                } catch (exc: Exception) {

                    System.err.println(exc)
                    throw RuntimeException()
                }

            }
}

private fun JsonObject.getNullable(key: String): String? = if (has(key)) get(key).asString else null

private fun JsonObject.getString(key: String) = get(key).asString

private fun JsonObject.getNullableStringWithMultipleKeys(keys: List<String>, currentKeyIndex: Int = 0): String? =
        if (currentKeyIndex >= keys.size) {
            null
        } else {
            val currentKey = keys[currentKeyIndex]
            if (has(currentKey)) {
                getString(currentKey)
            } else {
                getNullableStringWithMultipleKeys(keys, currentKeyIndex + 1)
            }

        }