package com.psq.supply.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:25
 **/
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
