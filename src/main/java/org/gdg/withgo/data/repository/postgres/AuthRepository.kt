package org.gdg.withgo.data.repository.postgres

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.domain.AuthUsecase
import org.gdg.withgo.service.Postgresql
import java.sql.SQLException

class AuthRepository : AuthUsecase {
    override fun checkExists(email: String, phone: String): Single<Boolean> = Single.create {
        try {
            Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("select count(*) from account where phone=? OR email=?")
                stmt.setString(1, phone)
                stmt.setString(2, email)
                val resultSet = stmt.executeQuery()
                if (resultSet.next()) {
                    val count = resultSet.getInt(1)
                    it.onSuccess(count > 0)
                } else {
                    it.onError(Throwable("sql query error"))
                }
            }
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun register(email: String, name: String, password: String, phone: String) = Completable.create {
        this.checkExists(email, phone).subscribe({ res ->
            if (res) {
                it.onError(Throwable("User already exists with Email, Phone"))
            } else {
                try {
                    Postgresql.create().use { connection ->
                        val stmt = connection.prepareStatement("insert into account (name, email, phone, password) values(?, ?, ?, ?)")
                        stmt.setString(1, name)
                        stmt.setString(2, email)
                        stmt.setString(3, phone)
                        stmt.setString(4, password)
                        val res = stmt.executeUpdate()
                        if (res == 1) {
                            it.onComplete()
                        } else {
                            it.onError(Throwable("Failure the insert row"))
                        }
                    }
                } catch (e: SQLException) {
                    it.onError(Throwable(e.sqlState))
                }
            }
        }, { t -> it.onError(t) })
    }

    override fun login(email: String, password: String) = Completable.create {
        try {
            val status = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("select count(*) from account where email=? AND password=?")
                stmt.setString(1, email)
                stmt.setString(2, password)
                val resultSet = stmt.executeQuery()
                if (resultSet.next()) {
                    val count = resultSet.getInt(1)
                    count > 0
                }else {
                    false
                }
            }
            if (status) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failure login with unknown reason"))
            }
        } catch (e: SQLException) {
            it.onError(Throwable(e.sqlState))
        }
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