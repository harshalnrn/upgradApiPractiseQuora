package com.upgrad.quora.service.entity;

import org.springframework.jmx.export.annotation.ManagedNotification;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "answer")
@NamedQueries({
        @NamedQuery(name = "findByAnswerId", query = "select a from AnswerEntity a where a.uuid=:answerId"),
        @NamedQuery(name = "getListOfAnswers", query = "select a from AnswerEntity a where a.questionEntity.uuid=:questionId")
})
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @Column(name = "uuid")
    public String uuid;
    @Column(name = "ans")
    public String ans;
    @Column(name = "date")
    public ZonedDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity userEntity;                                   //if this deleted, then list<answerentity> to be deleted for that user
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    //note that we use uuid corresponding to this foreign key, and not fk value directly in query
    public QuestionEntity questionEntity;                            //if this deleted, that list<answerseneity> to be deleted for that question


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

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }
}
