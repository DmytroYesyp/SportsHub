package com.sportshub.entity.user;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sportshub.entity.role.Roles;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="\"users\"")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="user_seq")
    @SequenceGenerator(name="user_seq",
            sequenceName="SEQ_USER", allocationSize=1)
    private long id;



    private String firstName;
    private String lastName;

    private String password;
    //private String logo_url;

    private String email;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @ManyToMany()
    @JoinTable(
            name = "userHasRoles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    @JsonBackReference
    private Set<Roles> roles = new HashSet<>();


    public User() {
    }

    public User(String firstName, String lastName, String password, String email, Set<Roles> role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.roles = role;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
