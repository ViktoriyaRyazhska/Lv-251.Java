package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Shmidt on 15.08.2017.
 */
@Entity
public class Admin extends User {

    @OneToMany
    private List<Moderator> moderators;

    public Admin() {
    }

    public List<Moderator> getModerators() {
        return moderators;
    }

    public void setModerators(List<Moderator> moderators) {
        this.moderators = moderators;
    }
}
