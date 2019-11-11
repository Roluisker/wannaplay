package com.game.books.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.game.core.BaseRepository
import com.game.core.model.Book
import com.game.core.response.BooksDocumentSnapshotDeserializer
import com.game.firebase.livedata.FirestoreFetchCollectionLiveData
import com.game.firebase.response.DeserializeListDocumentSnapshotTransform
import com.google.firebase.firestore.FirebaseFirestore
import com.group.pow.resources.Resource

class BooksRepositoryImpl : BaseRepository(), BooksRepository {

    private val booksCollection = FirebaseFirestore.getInstance().collection("books")

    override fun fetchBooks(categoryId: Int): LiveData<Resource<ArrayList<Book>>> {
        val documentLiveData = FirestoreFetchCollectionLiveData(booksCollection)
        return Transformations.map(
            documentLiveData,
            DeserializeListDocumentSnapshotTransform(BooksDocumentSnapshotDeserializer())
        )
    }
}