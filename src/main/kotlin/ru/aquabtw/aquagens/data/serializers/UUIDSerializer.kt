package ru.aquabtw.aquagens.data.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

object UUIDSerializer : KSerializer<UUID> {

    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        var str = decoder.decodeString()

        if(str.length == 32) {
            str = StringBuilder(str)
                .insert(8, "-")
                .insert(13, "-")
                .insert(18, "-")
                .insert(23, "-")
                .toString()
        }
        return UUID.fromString(str)
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }

}