package com.kienpham.coffee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "pass_word")
    private String passWord;
    @Column(name = "user_sdt")
    private String phone;
    @Column(name = "full_name")
    private String fullName;
    private String role;

}
