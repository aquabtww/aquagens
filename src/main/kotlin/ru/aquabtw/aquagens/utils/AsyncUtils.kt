package ru.aquabtw.aquagens.utils

import org.bukkit.Bukkit
import ru.aquabtw.aquagens.AquaGens

private val scheduler = Bukkit.getScheduler()
private val plugin = AquaGens.get()

fun async(body: () -> Unit) {
   scheduler.runTaskAsynchronously(plugin) { _ ->
       body()
   }
}