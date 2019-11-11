package com.upgrad.quora.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Table(name="user_auth")
@NamedQuery(name = "findByToken",query = "select u from UserAuthenticationEntity u where u.accessToken=:accessToken ")
public class UserAuthenticationEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    @Size(max =200 )
    private String uuid;
    @Column(name = "access_token")
    @Size(max = 500)
    private String accessToken;
    @Column(name = "expires_at")
    private ZonedDateTime expiresAt;
    @Column(name = "login_at")
    private ZonedDateTime loginAt;
    @Column(name = "logout_at")
    private ZonedDateTime logoutAt;

    //mention the association below and which is foreign key and referenced key
    //crud delete operation on child object(parent table) cascaded to parent object(child table)
    @ManyToOne(fetch = FetchType.LAZY)                 //make sure if its one to one or many to one
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity userEntity; //if user deleted, first user auth would be deleted, then user

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



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public ZonedDateTime getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(ZonedDateTime loginAt) {
        this.loginAt = loginAt;
    }

    public ZonedDateTime getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(ZonedDateTime logoutAt) {
        this.logoutAt = logoutAt;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
