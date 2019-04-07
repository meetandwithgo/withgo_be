package org.gdg.withgo.data.repository.postgre

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.organization.Organization
import org.gdg.withgo.domain.OrganizationUsecase
import org.gdg.withgo.service.Postgresql
import java.sql.SQLException

class OrganizationRepository : OrganizationUsecase {

    override fun add(uid: Int, name: String): Single<Organization> = Single.create {
        try {
            val id = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("insert into organization (name) values (?) RETURNING id")
                stmt.setString(1, name)
                val res = stmt.executeQuery()
                if (res.next()) {
                    res.getInt(1)
                } else {
                    null
                }
            }
            if (id != null) {
                val throwable = addMember(id, uid).blockingGet()
                if (throwable != null) {
                    it.onError(throwable)
                } else {
                    it.onSuccess(Organization(id, name))
                }
            } else {
                it.onError(Throwable("Failure insert"))
            }
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun addMember(id: Int, uid: Int) = Completable.create {
        try {
            val res = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("insert into organization_member (user_id, org_id) values (?,?)")
                stmt.setInt(1, uid)
                stmt.setInt(2, id)
                stmt.executeUpdate() > 0
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failure update"))
            }
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun removeMember(id: Int, uid: Int) = Completable.create {
        try {
            val res = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("delete from organization where org_id=? AND user_id=?")
                stmt.setInt(1, id)
                stmt.setInt(2, uid)
                stmt.executeUpdate() > 0
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failure update"))
            }
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun remove(id: Int) = Completable.create {
        try {
            val res = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("delete from organization where id=?")
                stmt.setInt(1, id)
                stmt.executeUpdate() > 0
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failure update"))
            }
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun rename(id: Int, name: String) = Completable.create {
        try {
            val res = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("update organization set name=? WHERE id=?")
                stmt.setString(1, name)
                stmt.setInt(2, id)
                stmt.executeUpdate() > 0
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failure update"))
            }
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun load(uid: Int): Single<List<Organization>> = Single.create {
        try {
            val res = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("SELECT (o.id, o.name) FROM organization org JOIN organization_member org_member ON org.id=org_member.org_id WHERE org_member.uid=?")
                stmt.setInt(1, uid)
                val resultSet = stmt.executeQuery()
                val orgList = ArrayList<Organization>()
                while (resultSet.next()) {
                    val id = resultSet.getInt(1)
                    val name = resultSet.getString(2)
                    orgList.add(Organization(id, name))
                }
                orgList
            }
            it.onSuccess(res)
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

}