package com.unisussex.hms.hibernate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unisussex.hms.hibernate.enums.UserStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private RoleEntity role;
    @Type(type = "org.hibernate.type.TrueFalseType")
    private boolean mustChangePassword;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginDate;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public boolean isMustChangePassword() {
        return mustChangePassword;
    }
    public UserEntity setMustChangePassword(boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
        return this;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }
    public UserEntity setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", mustChangePassword=" + mustChangePassword +
                ", userStatus=" + userStatus +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }
}