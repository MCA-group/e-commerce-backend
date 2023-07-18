package com.mcagroupecommerce.backendapplication.responses;

public class AuthResponse {

    private String jwt;

    private boolean status;

    private String name;

    private String email;

    public AuthResponse() {
        // TODO Auto-generated constructor stub
    }

    public AuthResponse(String jwt, boolean status, String name, String email) {
        super();
        this.jwt = jwt;
        this.status = status;
        this.name = name;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
