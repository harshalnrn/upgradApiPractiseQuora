package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository
//JpaRepository belongs to spring data jpa library, which is another abstraction layer over JPA
public interface AnswerDao extends JpaRepository<AnswerEntity, Integer> {
}
