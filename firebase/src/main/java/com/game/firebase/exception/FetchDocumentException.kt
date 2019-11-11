package com.game.firebase.exception

import com.google.firebase.firestore.FirebaseFirestoreException

class FetchDocumentException(message: String, code: Code) : FirebaseFirestoreException(message, code)