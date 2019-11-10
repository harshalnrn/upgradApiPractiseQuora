package com.upgrad.quora.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")

//MENTION NOT NULL CONTRAINT IF MENTIONED IN DB SCHEMA

//you can replace getters and settters with lombok library, which also includes logging support
@NamedQueries({
        @NamedQuery(name = "findByUsername", query = "select u from UserEntity u where u.userName=:userName"),
        @NamedQuery(name="findByUserId",query = "select u from UserEntity u where u.uuid=:uuid")
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
    private String firstName;
    @Column(name = "lastname")
    @Size(max = 30)
    private String lastName;
    @Column(name = "username")
    @Size(max = 30)
    private String userName;
    @Column(name = "email")
    @Size(max = 50)
    private String emailAddress;
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
    private String aboutMe;
    @Column(name = "dob")
    @Size(max = 30)
    private String dob;
    @Column(name = "role")
    @Size(max = 30)
    private String role;
    @Column(name = "contactnumber")
    @Size(max = 30)
    private String contactNumber;

//@OneToMany(mappedBy = "userEntity")        //bidirectional mapping transient field //mappedBy =child object field name in UserEntity entity
//private List<UserAuthenticationEntity> list;


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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
