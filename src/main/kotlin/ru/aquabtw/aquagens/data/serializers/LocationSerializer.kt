package ru.aquabtw.aquagens.data.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bukkit.Bukkit
import org.bukkit.Location

object LocationSerializer : KSerializer<Location> {
    override val descriptor = PrimitiveSerialDescriptor("LOCATION", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Location {
        val encoded = decoder.decodeString().split(";")
        return Location(Bukkit.getWorld(encoded[0]), encoded[1].toDouble(), encoded[2].toDouble(), encoded[3].toDouble())
    }

    override fun serialize(encoder: Encoder, value: Location) {
        encoder.encodeString("${value.world.name};${value.x};${value.y};${value.z}")
    }

}