package ru.aquabtw.aquagens.generators

import org.bukkit.Material
import ru.aquabtw.aquagens.AquaGens

class GeneratorManager(
    main: AquaGens
) {

    private val _generators: MutableSet<Generator> = mutableSetOf()

    fun getGenerator(id: String?) = _generators.firstOrNull { it.id == id}

    fun updateFromConfig() {
        _generators.add(
            Generator(
                "dirt",
                Material.DIRT,
                "DIrt",
                Generator.Drop(
                    Material.DIRT,
                    "Dirt drop.",
                    1
                )
            )
        )
    }

}