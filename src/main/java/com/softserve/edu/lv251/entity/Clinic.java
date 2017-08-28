package com.softserve.edu.lv251.entity;

import javax.persistence.*;
import java.util.List;

/**
 *
 */


@Entity
public class Clinic extends BaseEntity {
    private String clinic_name;

    @Column(name = "photo", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String photo;

    @Column(columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "clinic", cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })

    private List<Doctor> doctors;


    @OneToOne(cascade = {CascadeType.ALL})
    private Contact contact;

    @OneToOne(mappedBy = "clinic")
    private Moderator moderator;

    public Clinic() {
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getClinic_name() {
        return clinic_name;
    }

    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "clinic_name='" + clinic_name + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +

                '}';
    }
}
