package ru.aquabtw.aquagens.data.database

import kotlinx.serialization.encodeToString
import ru.aquabtw.aquagens.data.PlayerModel
import ru.aquabtw.aquagens.utils.async
import ru.aquabtw.aquagens.utils.json
import java.io.File
import java.util.*

class JsonDatabase(
    private val dataFolder: File
) : Database {

    override fun get(uuid: UUID): PlayerModel? {
        val file = File(dataFolder, "$uuid.json")
        if (!file.exists()) file.createNewFile()

        val content = file.inputStream().readBytes().toString(Charsets.UTF_8)
        return try {
            json.decodeFromString<PlayerModel>(content)
        } catch(ex: Exception) {
            //ex.printStackTrace()
            null
        }
    }

    override fun create(uuid: UUID): PlayerModel {
        val playerModel = PlayerModel(uuid)
        save(playerModel)
        return playerModel
    }

    override fun save(playerModel: PlayerModel) {
        val file = File(dataFolder, playerModel.uuid.toString() + ".json")
        val content = json.encodeToString(playerModel)
        async {
            file.bufferedWriter().use { out ->
                out.write(content)
            }
        }
    }

}