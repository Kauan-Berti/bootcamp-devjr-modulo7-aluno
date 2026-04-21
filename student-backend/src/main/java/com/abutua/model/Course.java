package com.abutua.model;

public class Course {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int idCurso;
    private int period;
    
    public Course() {
    }

    public Course(int id, String name, String email, String phone, int idCurso, int period) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.idCurso = idCurso;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    
    

    

}
