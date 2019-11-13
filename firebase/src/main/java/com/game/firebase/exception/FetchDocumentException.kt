/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.firebase.exception

import com.google.firebase.firestore.FirebaseFirestoreException

class FetchDocumentException(message: String, code: Code) : FirebaseFirestoreException(message, code)