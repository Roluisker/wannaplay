/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.books.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.game.core.AppConstants
import com.game.core.BaseRepository
import com.game.core.model.Book
import com.game.core.response.BooksDocumentSnapshotDeserializer
import com.game.firebase.livedata.FirestoreFetchCollectionLiveData
import com.game.firebase.response.DeserializeListDocumentSnapshotTransform
import com.google.firebase.firestore.FirebaseFirestore
import com.group.pow.resources.Resource


class BooksRepositoryImpl(private val fireStore: FirebaseFirestore) : BaseRepository(),
    BooksRepository {

    override fun fetchBooksByCategory(categorId: Int): LiveData<Resource<ArrayList<Book>>> {
        val query = fireStore.collection(AppConstants.BOOKS_NAME_COLLECTION).whereEqualTo(
            AppConstants.CATEGORY_ID_PARAM, categorId)

        return Transformations.map(
            FirestoreFetchCollectionLiveData(query),
            DeserializeListDocumentSnapshotTransform(BooksDocumentSnapshotDeserializer())
        )
    }
}