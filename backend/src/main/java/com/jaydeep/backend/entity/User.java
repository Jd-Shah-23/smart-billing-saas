package com.jaydeep.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "user_seq",allocationSize = 1)
    @Column(name ="user_id")
    private Long userId;
    private String loginId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userCity;
    private LocalDateTime createDate;
    private Boolean activeFlag;

    @OneToMany(mappedBy = "user")
    private List<Customer> customers;

    public User(String loginId,String name, String email, String userPassword,String userCity) {
        this.loginId=loginId;
        this.userName = name;
        this.userEmail = email;
        this.userPassword = userPassword;
        this.userCity = userCity;
    }

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.activeFlag = true;
    }

}
