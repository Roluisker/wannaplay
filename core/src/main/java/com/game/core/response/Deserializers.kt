package com.game.core.response

import com.game.core.model.Book
import com.group.pow.resources.ListDocumentSnapshotDeserializer

class BooksDocumentSnapshotDeserializer : ListDocumentSnapshotDeserializer<ArrayList<Book>> {
    override fun deserialize(input: ArrayList<Map<String, Any>>): ArrayList<Book> {
        return ArrayList<Book>().apply {
            input.forEach { book ->
                add(
                    Book(
                        book["title"] as String,
                        book["description"] as String,
                        book["image"] as String
                    )
                )
            }
        }
    }
}