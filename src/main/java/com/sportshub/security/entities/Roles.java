package com.sportshub.security.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="\"roles\"")
public class Roles {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;


    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Users> users;

    public Roles() {
    }

    public Roles(String name, Set<Users> user) {
        this.name = name;
        this.users = user;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Users> getUser() {
        return users;
    }

    public void setUser(Set<Users> user) {
        this.users = user;
    }
}
