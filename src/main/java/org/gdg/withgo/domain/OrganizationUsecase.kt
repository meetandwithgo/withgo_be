package org.gdg.withgo.domain

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.organization.Organization

interface OrganizationUsecase {
    fun add(email: String, name: String): Single<Organization>
    fun remove(id: Int): Completable
    fun load(email: String): Single<List<Organization>>
    fun rename(id: Int, name: String): Completable
}