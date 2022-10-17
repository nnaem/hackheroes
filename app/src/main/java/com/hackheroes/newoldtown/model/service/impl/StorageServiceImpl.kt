package com.hackheroes.newoldtown.model.service.impl

import com.hackheroes.newoldtown.model.Suggestion
import com.hackheroes.newoldtown.model.service.StorageService
import com.google.firebase.firestore.DocumentChange.Type.REMOVED
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class StorageServiceImpl @Inject constructor() : StorageService {
    private var allListenerRegistration: ListenerRegistration? = null
    private var userListenerRegistartion: ListenerRegistration? = null

    override fun addListener(
        userId: String,
        onDocumentEvent: (Boolean, Suggestion) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val query = Firebase.firestore.collection(SUGGESTION_COLLECTION).whereEqualTo(USER_ID, userId)

        userListenerRegistartion = query.addSnapshotListener { value, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }

            value?.documentChanges?.forEach {
                val wasDocumentDeleted = it.type == REMOVED
                val suggestion = it.document.toObject<Suggestion>().copy(title = it.document.id)
                onDocumentEvent(wasDocumentDeleted, suggestion)
            }
        }
    }

    override fun addListener(
        onDocumentEvent: (Boolean, Suggestion) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val query = Firebase.firestore.collection(SUGGESTION_COLLECTION);

        allListenerRegistration = query.addSnapshotListener { value, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }

            value?.documentChanges?.forEach {
                val wasDocumentDeleted = it.type == REMOVED
                val suggestion = it.document.toObject<Suggestion>().copy(title = it.document.id)
                onDocumentEvent(wasDocumentDeleted, suggestion)
            }
        }
    }

    override fun removeListener() {
        allListenerRegistration?.remove()
        userListenerRegistartion?.remove()
    }

    override fun getSuggestion(
        suggestionId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Suggestion) -> Unit
    ) {
        Firebase.firestore
            .collection(SUGGESTION_COLLECTION)
            .document(suggestionId)
            .get()
            .addOnFailureListener { error -> onError(error) }
            .addOnSuccessListener { result ->
                val suggestion = result.toObject<Suggestion>()?.copy(title = result.id)
                onSuccess(suggestion ?: Suggestion())
            }
    }

    override fun saveSuggestion(suggestion: Suggestion, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(SUGGESTION_COLLECTION)
            .document(suggestion.title)
            .set(suggestion)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun updateSuggestion(suggestion: Suggestion, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(SUGGESTION_COLLECTION)
            .document(suggestion.title)
            .set(suggestion)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun deleteSuggestion(suggestionId: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(SUGGESTION_COLLECTION)
            .document(suggestionId)
            .delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun deleteAllForUser(userId: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(SUGGESTION_COLLECTION)
            .whereEqualTo(USER_ID, userId)
            .get()
            .addOnFailureListener { error -> onResult(error) }
            .addOnSuccessListener { result ->
                for (document in result) document.reference.delete()
                onResult(null)
            }
    }

    companion object {
        private const val SUGGESTION_COLLECTION = "suggestions"
        private const val USER_ID = "userId"
    }
}