package org.gdg.withgo.domain

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.organization.Organization

interface OrganizationUsecase {
    fun add(uid: Int, name: String): Single<Organization>
    fun addMember(id: Int, uid: Int): Completable
    fun remove(id: Int): Completable
    fun load(uid: Int): Single<List<Organization>>
    fun rename(id: Int, name: String): Completable
    fun removeMember(id: Int, uid: Int): Completable
}