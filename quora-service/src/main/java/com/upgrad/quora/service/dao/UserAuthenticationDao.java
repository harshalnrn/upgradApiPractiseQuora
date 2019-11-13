package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository
public interface UserAuthenticationDao extends JpaRepository<UserAuthenticationEntity,Integer> {


}
