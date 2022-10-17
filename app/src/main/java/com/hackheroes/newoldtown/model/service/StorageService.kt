package com.hackheroes.newoldtown.model.service

import com.hackheroes.newoldtown.model.Suggestion

interface StorageService {
    fun addListener(
        userId: String,
        onDocumentEvent: (Boolean, Suggestion) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun addListener(
        onDocumentEvent: (Boolean, Suggestion) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun removeListener()
    fun getSuggestion(suggestionId: String, onError: (Throwable) -> Unit, onSuccess: (Suggestion) -> Unit)
    fun saveSuggestion(suggestion: Suggestion, onResult: (Throwable?) -> Unit)
    fun updateSuggestion(suggestion: Suggestion, onResult: (Throwable?) -> Unit)
    fun deleteSuggestion(suggestionId: String, onResult: (Throwable?) -> Unit)
    fun deleteAllForUser(userId: String, onResult: (Throwable?) -> Unit)
}
