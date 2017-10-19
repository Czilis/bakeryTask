package smagabakery.com.bakeryapp.data.remote

import com.google.gson.*
import smagabakery.com.bakeryapp.data.model.People
import java.lang.reflect.Type

class PeopleDeserializer : JsonDeserializer<People> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): People =
            with(json as JsonArray) {
                val personList = json[0].asJsonObject.get("items") as JsonArray
                val mappedPersonList = personList
                        .map { it as JsonObject }
                        .map { PersonDeserializer.deserialize(it) }
                        .toList()
                People(mappedPersonList)
            }
}