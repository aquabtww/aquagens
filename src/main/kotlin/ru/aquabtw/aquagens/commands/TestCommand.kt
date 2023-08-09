package ru.aquabtw.aquagens.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.aquabtw.aquagens.AquaGens

class TestCommand(
    private val main: AquaGens
) : CommandExecutor {

    val name = "test"

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        val generator = main.generatorManager.getGenerator("dirt")
            ?: return false

        println(main.playerManager.players)

        sender.inventory.addItem(generator.toItemStack())
        return true
    }

}