/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.group.pow.resources

interface ListDocumentSnapshotDeserializer<T> : Deserializer<ArrayList<Map<String, Any>>, T>