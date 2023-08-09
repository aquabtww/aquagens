package ru.aquabtw.aquagens.data

import org.bukkit.Bukkit
import org.bukkit.Location
import ru.aquabtw.aquagens.AquaGens
import ru.aquabtw.aquagens.generators.Generator
import ru.aquabtw.aquagens.generators.PlayerGenerator
import java.util.*

class PlayerManager(
    private val main: AquaGens
) {

    private val database = main.database

    private val _players: MutableSet<PlayerModel> = mutableSetOf()
    val players: Set<PlayerModel>
        get() = Collections.unmodifiableSet(_players)

    fun getPlayer(uuid: UUID) = _players.firstOrNull { it.uuid == uuid }

    fun addPlayer(uuid: UUID): PlayerModel {
        val playerModel = database.get(uuid)
            ?: database.create(uuid)

        // Start gen drops.
        playerModel.gens.forEach {
            val taskId = activateGenerator(it)
            playerModel.taskIDs[it.location] = taskId
        }

        _players.add(playerModel)
        return playerModel
    }

    fun removePlayer(uuid: UUID) {
        println(getPlayer(uuid))
        val playerModel = getPlayer(uuid)
            ?: return

        // Stop gen drops.
        val scheduler = Bukkit.getScheduler()
        playerModel.taskIDs.forEach { (_, id) ->
            scheduler.cancelTask(id)
        }

        database.save(playerModel)
        _players.remove(playerModel)
    }

    fun addGenerator(playerModel: PlayerModel, location: Location, generator: Generator) {
        val playerGenerator = PlayerGenerator(generator, location)
        playerModel.gens.add(playerGenerator)

        val taskId = activateGenerator(playerGenerator)
        playerModel.taskIDs[location] = taskId

        database.save(playerModel)
    }

    fun removeGenerator(playerModel: PlayerModel, generator: PlayerGenerator) {
        if (!playerModel.taskIDs.contains(generator.location)) return

        val taskId = playerModel.taskIDs[generator.location]!!

        Bukkit.getScheduler().cancelTask(taskId)
        playerModel.taskIDs.remove(generator.location)
        playerModel.gens.remove(generator)
        database.save(playerModel)
    }

    private fun activateGenerator(playerGenerator: PlayerGenerator): Int {
        val task = Bukkit.getScheduler().runTaskTimer(main, Runnable {
            playerGenerator.drop()
        }, 40L, 40L)
        return task.taskId
    }
}