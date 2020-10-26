package com.example.register;

public class UserHelperClass {
    String name , username , password , phoneno, email, profession ;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String username, String password, String phoneno, String email, String profession) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneno = phoneno;
        this.email = email;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
