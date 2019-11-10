package com.upgrad.quora.service.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Table(name = "question")
//@NamedQuery(name = "", query = "")
public class QuestionEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    @Size(max = 200)
    private String uuid;
    @Column(name = "content")
    @Size(max = 500)
    private String content;
    @Column(name = "date")
    private ZonedDateTime date;
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ManyToOne(cascade =  CascadeType.REMOVE ,fetch = FetchType.LAZY)
    private UserEntity userId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
