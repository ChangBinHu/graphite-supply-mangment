package com.psq.supply.repository;

import com.psq.supply.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:26
 **/
public interface UserRepository extends JpaRepository<User, Long> {

}
