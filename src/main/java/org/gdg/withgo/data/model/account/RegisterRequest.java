package org.gdg.withgo.data.model.account;

import org.gdg.withgo.data.model.Model;

public class RegisterRequest extends Model {
    private String email;
    private String name;
    private String phone;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void assertFields() {
        valid(email, "Email not found");
        valid(name, "Name not found");
        valid(phone, "Phone not found");
        valid(password, "Password not found");
    }
}
