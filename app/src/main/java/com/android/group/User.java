package com.android.group;

public class User {

    private int resourceID;
    private String name;
    private int age;
    private String sex;
    private String vax;
    private String covid;
    private String status;

    public User(int resourceID, String name, int age, String sex, String vax, String covid, String status) {
        this.resourceID = resourceID;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.vax = vax;
        this.covid = covid;
        this.status = status;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getVax() {
        return vax;
    }

    public void setVax(String vax) {
        this.vax = vax;
    }

    public String getCovid() {
        return covid;
    }

    public void setCovid(String covid) {
        this.covid = covid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
