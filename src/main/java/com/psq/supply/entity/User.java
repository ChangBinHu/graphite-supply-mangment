package com.psq.supply.entity;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * @author psq
 * @description
 * @create 2025-03-30 15:25
 **/
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String name) { this.username = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
