package ru.aquabtw.aquagens.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import ru.aquabtw.aquagens.data.serializers.LocationSerializer
import ru.aquabtw.aquagens.data.serializers.GeneratorIDSerializer
import ru.aquabtw.aquagens.data.serializers.UUIDSerializer

private val module = SerializersModule {
    contextual(UUIDSerializer)
    contextual(LocationSerializer)
    contextual(GeneratorIDSerializer)
}

val json = Json {
    serializersModule = module
}

val prettyJson = Json {
    prettyPrint = true
    serializersModule = module
}
