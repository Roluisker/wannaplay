/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.firebase.response

import androidx.arch.core.util.Function
import com.game.firebase.livedata.DocumentListSnapshotOrException
import com.group.pow.resources.Deserializer
import com.group.pow.resources.ListDocumentSnapshotDeserializer
import com.group.pow.resources.Resource

class DeserializeListDocumentSnapshotTransform<T>(
    private val deserializer: ListDocumentSnapshotDeserializer<T>
) : Function<DocumentListSnapshotOrException, Resource<T>> {

    override fun apply(input: DocumentListSnapshotOrException): Resource<T> {
        val (hashMap, exception) = input

        if (exception != null) {
            return Resource.error(exception.code.toString(), null)
        }

        if (hashMap != null) {
            try {
                return Resource.success(deserializer.deserialize(hashMap))
            } catch (exception: Deserializer.DeserializerException) {
                return Resource.error("Error", null)
            }
        }
        return Resource.error("Error", null)
    }
}