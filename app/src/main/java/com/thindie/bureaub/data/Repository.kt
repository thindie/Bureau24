package com.thindie.bureaub.data

import app.cash.sqldelight.coroutines.asFlow
import com.thindie.bureaub.BureauBDb
import com.thindie.bureaub.Notes
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

private const val WHITESPACE = " "
class Repository(private val db: BureauBDb) {

    private val flow = db.bureauQueries.getAll().asFlow().map { it.executeAsList() }

    val tags = flow.map { it.flatMap { it.tag.split(WHITESPACE) } }

    fun note(tag: String) = flow.map { it.reduce { acc, _ ->  acc  }  }.filterNotNull()
        .filter { it.tag.contains(tag) }
    suspend fun addNote(title: String, description: String, tag: String? = null) {
        db.bureauQueries.insert(
            id = null,
            title = title,
            description = description,
            tag = tag.orEmpty()
        )
    }

    suspend fun updateTag(tag: String, id: Int) {
        val note = flow.firstOrNull()?.firstOrNull { it.tag == tag }
        note?.let { db.bureauQueries.upsertNote(note.tag.plus(WHITESPACE).plus(tag), id.toLong()) }
    }

}