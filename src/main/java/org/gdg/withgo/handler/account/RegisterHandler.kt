package org.gdg.withgo.handler.account

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.account.RegisterRequest
import org.gdg.withgo.data.repository.postgre.AuthRepository

class RegisterHandler : RequestHandler<RegisterRequest, Boolean> {

    private val authRepository: AuthRepository = AuthRepository()

    override fun handleRequest(input: RegisterRequest, context: Context): Boolean? {
        context.logger.log("Register : $input")

        return try {
            input.assertFields()
            val throwable = authRepository.register(input.email, input.name, input.password, input.phone).blockingGet()
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
