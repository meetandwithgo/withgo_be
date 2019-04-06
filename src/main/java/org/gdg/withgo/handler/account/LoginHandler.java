package org.gdg.withgo.handler.account;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.gdg.withgo.data.model.account.LoginRequest;
import org.gdg.withgo.service.Postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHandler implements RequestHandler<LoginRequest, Boolean> {

    @Override
    public Boolean handleRequest(LoginRequest input, Context context) {
        context.getLogger().log("Login : "+ input);

        try(Connection connection = Postgresql.create()) {
            input.assertFields();
            PreparedStatement stmt = connection.prepareStatement("select count(*) from account where email=? AND password=?");
            stmt.setString(1, input.getEmail());
            stmt.setString(2, input.getPassword());
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                context.getLogger().log(String.format("match count : %d ", count));
                return count > 0;
            }
        } catch (AssertionError e){
            context.getLogger().log(e.getMessage());
        } catch (SQLException e) {
            context.getLogger().log(e.getSQLState());
        }
        return null;
    }

}
