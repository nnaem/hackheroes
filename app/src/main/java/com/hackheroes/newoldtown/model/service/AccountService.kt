package com.hackheroes.newoldtown.model.service

interface AccountService {
    fun hasUser(): Boolean
    fun isAnonymousUser(): Boolean
    fun getUserId(): String
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun sendRecoveryEmail(email: String, onResult: (Throwable?) -> Unit)
    fun createAnonymousAccount(onResult: (Throwable?) -> Unit)
    fun createAccount(email: String, password: String, onSuccess: () -> Unit, onFail: (Throwable?) -> Unit)
    fun deleteAccount(onResult: (Throwable?) -> Unit)
    fun signOut()
}
