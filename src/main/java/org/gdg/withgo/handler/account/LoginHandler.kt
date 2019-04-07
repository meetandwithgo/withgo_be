package org.gdg.withgo.handler.account

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.account.LoginRequest
import org.gdg.withgo.data.repository.postgres.AuthRepository
import java.lang.AssertionError

class LoginHandler : RequestHandler<LoginRequest, Boolean> {

    private val authRepository: AuthRepository = AuthRepository()

    override fun handleRequest(input: LoginRequest, context: Context): Boolean {
        context.logger.log("Login : $input")
        return try {
            input.assertFields()
            val throwable = authRepository.login(input.email, input.password).blockingGet()
            if (throwable != null) {
                throw throwable
            }
            context.logger.log("Login success")
            true
        } catch (e: AssertionError) {
            context.logger.log("assert error ${e.message}")
            false
        } catch (e: Throwable) {
            context.logger.log("error occuard ${e.message}")
            false
        }
    }

}
