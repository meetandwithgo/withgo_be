package org.gdg.withgo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.gdg.withgo.model.account.RegisterRequest;
import org.gdg.withgo.service.Postgresql;

import java.sql.*;

public class RegisterHandler implements RequestHandler<RegisterRequest, Boolean> {

    public Boolean handleRequest(RegisterRequest input, Context context){
        context.getLogger().log("Input : "+ input);

        try(Connection connection = Postgresql.create()) {
            input.assertFields();
            PreparedStatement stmt = connection.prepareStatement("select count(*) from account where phone=? OR email=?");
            stmt.setString(1, input.getPhone());
            stmt.setString(2, input.getEmail());
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                context.getLogger().log(String.format("match count : %d ", count));
                if(count > 0){
                    return false;
                }
            }
            PreparedStatement insertStmt = connection.prepareStatement("insert into account (name, email, phone, password) values(?, ?, ?, ?)");
            insertStmt.setString(1, input.getName());
            insertStmt.setString(2, input.getEmail());
            insertStmt.setString(3, input.getPhone());
            insertStmt.setString(4, input.getPassword());
            int res = insertStmt.executeUpdate();
            if(res == 1){
                return true;
            }
        } catch (AssertionError e){
            context.getLogger().log(e.getMessage());
        } catch (SQLException e) {
            context.getLogger().log(e.getSQLState());
        }
        return false;
    }
}
