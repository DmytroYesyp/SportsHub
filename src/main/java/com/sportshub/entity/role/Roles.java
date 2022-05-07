package com.sportshub.entity.role;

import com.sportshub.entity.user.User;

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
    private Set<User> users = new HashSet<>();

    public Roles() {
    }

    public Roles(String name, Set<User> user) {
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

    public Set<User> getUser() {
        return users;
    }

    public void setUser(Set<User> user) {
        this.users = user;
    }
}
