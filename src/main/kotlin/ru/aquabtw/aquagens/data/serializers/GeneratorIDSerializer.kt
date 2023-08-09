package ru.aquabtw.aquagens.data.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ru.aquabtw.aquagens.AquaGens
import ru.aquabtw.aquagens.generators.Generator

object GeneratorIDSerializer : KSerializer<Generator> {
    private val generatorManager = AquaGens.get().generatorManager

    override val descriptor = PrimitiveSerialDescriptor("PLAYER-GEN", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Generator {
        return generatorManager.getGenerator(decoder.decodeString())
            ?: throw(IllegalArgumentException("couldn't find ${decoder.decodeString()} gen while deserializing playergenerator."))
    }

    override fun serialize(encoder: Encoder, value: Generator) {
        encoder.encodeString(value.id)
    }

}