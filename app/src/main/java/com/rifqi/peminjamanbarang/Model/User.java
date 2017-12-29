package com.rifqi.peminjamanbarang.Model;

/**
 * Created by USER on 27/12/2017.
 */

public class User {
    private String Name;
    private String Password;
    private String Phone;

    public User(String password, String phone) {
        Password = password;
        Phone = phone;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
