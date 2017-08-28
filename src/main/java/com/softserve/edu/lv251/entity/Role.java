package com.softserve.edu.lv251.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marian Brynetskyi on 11.07.2017.
 */
@Entity
public class Role extends BaseEntity {

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = User.class)
    @JoinTable(
            name = "roles_users",
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<User> users;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
