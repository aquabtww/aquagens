package ru.aquabtw.aquagens

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import ru.aquabtw.aquagens.commands.TestCommand
import ru.aquabtw.aquagens.data.database.Database
import ru.aquabtw.aquagens.data.database.JsonDatabase
import ru.aquabtw.aquagens.data.PlayerManager
import ru.aquabtw.aquagens.generators.GeneratorManager
import ru.aquabtw.aquagens.listeners.BlockEvents
import ru.aquabtw.aquagens.listeners.ConnectionEvents

class AquaGens : JavaPlugin() {

    lateinit var generatorManager: GeneratorManager
        private set

    lateinit var playerManager: PlayerManager
        private set

    lateinit var database: Database
        private set

    override fun onEnable() {
        // Initialize Database
        dataFolder.mkdir()
        database = JsonDatabase(dataFolder)

        // Initialize MiniMessage
        miniMessage = MiniMessage.miniMessage()

        generatorManager = GeneratorManager(this)
        generatorManager.updateFromConfig()

        playerManager = PlayerManager(this)

        registerListeners()
        registerCommands()
    }

    private fun registerListeners() {
        setOf(
            BlockEvents(this),
            ConnectionEvents(this)
        ).forEach {
            Bukkit.getPluginManager().registerEvents(it, this)
        }
    }

    private fun registerCommands() {
        setOf(
            TestCommand(this)
        ).forEach {
            getCommand(it.name)?.setExecutor(it)
        }
    }

    companion object {
        /**
         * Singleton instance will not be used outside of utilities.
         **/
        fun get(): AquaGens {
            return getPlugin(AquaGens::class.java)
        }
    }

}

internal lateinit var miniMessage: MiniMessage
    private set