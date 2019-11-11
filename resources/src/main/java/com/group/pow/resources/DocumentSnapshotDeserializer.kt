package com.group.pow.resources

interface DocumentSnapshotDeserializer<T> : Deserializer<Map<String, Any>, T>
interface ListDocumentSnapshotDeserializer<T> : Deserializer<ArrayList<Map<String, Any>>, T>