package org.gdg.withgo.data.repository.postgre

import io.reactivex.Completable
import org.gdg.withgo.domain.AuthUsecase

class AuthRepository(): AuthUsecase {
    override fun register(email: String, name: String, password: String, phone: String) = Completable.create {

    }

    override fun login(email: String, password: String) = Completable.create {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun unregister(email: String) = Completable.create {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun modifyName(email: String, name: String) = Completable.create {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun modifyPassword(email: String, password: String) = Completable.create {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}