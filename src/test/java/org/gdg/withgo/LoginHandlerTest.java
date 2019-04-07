package org.gdg.withgo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.gdg.withgo.handler.account.LoginHandler;
import org.gdg.withgo.handler.account.RegisterHandler;
import org.gdg.withgo.data.model.account.LoginRequest;
import org.gdg.withgo.data.model.account.RegisterRequest;
import org.gdg.withgo.service.Postgresql;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class LoginHandlerTest {

    private Context getContextMock(){
        Context contextMock = mock(Context.class);
        LambdaLogger loggerMock = mock(LambdaLogger.class);
        when(contextMock.getLogger()).thenReturn(loggerMock);
        doAnswer(invocation -> {
            System.out.println(invocation.getArguments()[0]);
            return null;
        }).when(loggerMock).log(anyString());
        return contextMock;
    }

    @Before
    public void registerHandlerTest(){
        clear();
        Context contextMock = getContextMock();
        RegisterRequest request = new RegisterRequest();
        RegisterHandler handler = new RegisterHandler();
        request.setEmail("boxfoxsg619@gmail.com");
        request.setName("boxfox");
        request.setPassword("123456");
        request.setPhone("010-3312-3123");
        boolean res = handler.handleRequest(request, contextMock);
        Assert.assertTrue(res);
    }

    @Test
    public void loginRequestTest(){
        Context contextMock = getContextMock();
        LoginRequest request = new LoginRequest();
        LoginHandler handler = new LoginHandler();
        request.setEmail("boxfoxsg619@gmail.com");
        request.setPassword("123456");
        boolean res = handler.handleRequest(request, contextMock);
        Assert.assertTrue(res);
    }

    @After
    public void clear(){
        try(Connection connection = Postgresql.INSTANCE.create()){
            PreparedStatement stmt = connection.prepareStatement("delete from account where email='boxfoxsg619@gmail.com'");
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
