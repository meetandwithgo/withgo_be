package org.gdg.withgo.handler.account

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.account.LoginRequest
import org.gdg.withgo.data.repository.postgre.AuthRepository
import org.gdg.withgo.service.Postgresql

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class LoginHandler : RequestHandler<LoginRequest, Boolean> {

    private val authRepository: AuthRepository = AuthRepository()

    override fun handleRequest(input: LoginRequest, context: Context): Boolean? {
        context.logger.log("Login : $input")
        return try {
            input.assertFields()
            val throwable = authRepository.login(input.email, input.password).blockingGet()
            if (throwable != null) {
                context.logger.log(throwable.message)
                false
            } else {
                context.logger.log("Login success")
                true
            }
        } catch (e: AssertionError) {
            context.logger.log(e.message)
            false
        }
    }

}
