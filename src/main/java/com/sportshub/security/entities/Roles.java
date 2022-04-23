package com.sportshub.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="\"roles\"")
public class Roles {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Users> users = new HashSet<>();

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
