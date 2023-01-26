package com.example.product.productlist.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "registration")
public class Registration implements Persistable<Long> {

    @Id
    @Column("id")
    private Long userId;

    @Column("username")
    private String userName;

    @Column("mail")
    private String userMail;

    @Column("password")
    private String userPassword;

    @Override
    @JsonIgnore
    public Long getId() {
        return userId;
    }

    public void setUserId(Long productId) {
        this.userId = productId;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return userId == null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
