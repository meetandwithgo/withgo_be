package org.gdg.withgo.data.model.account;

import org.gdg.withgo.data.model.Model;

public class LoginRequest extends Model {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void assertFields() throws IllegalArgumentException {
        valid(email, "Email not found");
        valid(password, "Password not found");
    }
}
