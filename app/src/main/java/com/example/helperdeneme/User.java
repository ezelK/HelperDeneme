package com.example.helperdeneme;

public class User {

    private String id, nameSurname, age, email, gender, ssn, phone, service,ImageURl;

    public User(String id, String nameSurname, String age, String email, String gender, String ssn, String phone, String service, String imageURl) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.ssn = ssn;
        this.phone = phone;
        this.service = service;
        ImageURl = imageURl;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getSsn() {
        return ssn;
    }

    public String getPhone() {
        return phone;
    }

    public String getService() {
        return service;
    }

    public String getImageURl() {
        return ImageURl;
    }

}
