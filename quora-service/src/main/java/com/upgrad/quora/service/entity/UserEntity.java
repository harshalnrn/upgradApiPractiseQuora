package com.upgrad.quora.service.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")

//MENTION NOT NULL CONTRAINT IF MENTIONED IN DB SCHEMA

//you can replace getters and settters with lombok library, which also includes logging support
@NamedQueries({
        @NamedQuery(name = "findByUsername", query = "select u from UserEntity u where u.username=:username")
}
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //here we are generating sequence from java. also know how to use the sequence generator at db end here
    @Column(name = "id")
    private Integer id;
    @Column(name = "uuid")
    @Size(max = 200)
    private String uuid;
    @Column(name = "firstname")
    @Size(max = 30)
    private String firstname;
    @Column(name = "lastname")
    @Size(max = 30)
    private String lastname;
    @Column(name = "username")
    @Size(max = 30)
    private String username;
    @Column(name = "email")
    @Size(max = 50)
    private String email;
    @Column(name = "password")
    @Size(max = 255)
    private String password;
    @Column(name = "salt")
    @Size(max = 200)
    private String salt;
    @Column(name = "country")
    @Size(max = 30)
    private String country;
    @Column(name = "aboutme")
    @Size(max = 50)
    private String aboutme;
    @Column(name = "dob")
    @Size(max = 30)
    private String dob;
    @Column(name = "role")
    @Size(max = 30)
    private String role;
    @Column(name = "contactnumber")
    @Size(max = 30)
    private String contactnumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
}
