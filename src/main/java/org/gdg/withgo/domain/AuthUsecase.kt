package org.gdg.withgo.domain

import io.reactivex.Completable

interface AuthUsecase: Usecase {
    fun register(email: String, name: String, password: String, phone: String): Completable
    fun login(email: String, password: String): Completable
    fun unregister(email: String): Completable
    fun modifyName(email: String, name: String): Completable
    fun modifyPassword(email: String, password: String): Completable
}