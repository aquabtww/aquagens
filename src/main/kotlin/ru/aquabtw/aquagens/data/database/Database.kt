package ru.aquabtw.aquagens.data.database

import ru.aquabtw.aquagens.data.PlayerModel
import java.util.*

interface Database {

    fun get(uuid: UUID): PlayerModel?

    fun create(uuid: UUID): PlayerModel

    fun save(playerModel: PlayerModel)

}