package com.example.pizzahut.Modals;

public class UserModal {
    private String Name,Email,Password,U_id;

    public UserModal(String name, String email, String password, String u_id) {
        Name = name;
        Email = email;
        Password = password;
        U_id = u_id;
    }

    public UserModal() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getU_id() {
        return U_id;
    }

    public void setU_id(String u_id) {
        U_id = u_id;
    }
}
