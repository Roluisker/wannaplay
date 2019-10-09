package com.game.firebase.livedata

import com.game.firebase.response.FirebaseApiResponse
import com.game.firebase.FirebaseExecutor
import com.google.firebase.firestore.*

typealias DocumentSnapshotOrException = FirebaseApiResponse<Map<String, Any>?, FirebaseFirestoreException?>

class FirestoreDocumentLiveData(
    private val ref: DocumentReference,
    private val executors: FirebaseExecutor
) :
    LingeringLiveData<DocumentSnapshotOrException>(), EventListener<DocumentSnapshot> {

    private var listenerRegistration: ListenerRegistration? = null

    override fun beginLingering() {
        listenerRegistration = ref.addSnapshotListener(executors.networkIO(), this)
    }

    override fun endLingering() {
        listenerRegistration?.remove()
    }

    override fun onEvent(snapshot: DocumentSnapshot?, e: FirebaseFirestoreException?) {
        if (snapshot != null) {
            postValue(DocumentSnapshotOrException(snapshot.data, null))
        } else if (e != null) {
            postValue(DocumentSnapshotOrException(null, e))
        }
    }

}