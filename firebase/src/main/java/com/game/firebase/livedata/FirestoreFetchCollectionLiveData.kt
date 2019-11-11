package com.game.firebase.livedata

import androidx.lifecycle.LiveData
import com.game.firebase.exception.FetchDocumentException
import com.game.firebase.response.FirebaseApiResponse
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import java.util.ArrayList

typealias DocumentListSnapshotOrException = FirebaseApiResponse<ArrayList<Map<String, Any>>?, FirebaseFirestoreException?>

class FirestoreFetchCollectionLiveData(ref: CollectionReference) : LiveData<DocumentListSnapshotOrException>() {
    init {
        ref.get()
            .addOnSuccessListener { documents ->

                var result = ArrayList<Map<String, Any>>()

                documents.forEach {
                    it.data?.let { docuMap ->
                        result.add(docuMap)
                    }
                }

                postValue(DocumentListSnapshotOrException(result, null))

            }
            .addOnFailureListener { exception ->
                postValue(
                    DocumentListSnapshotOrException(
                        null, FetchDocumentException(
                            exception.message ?: "Unkown",
                            FirebaseFirestoreException.Code.UNKNOWN
                        )
                    )
                )
            }
    }
}