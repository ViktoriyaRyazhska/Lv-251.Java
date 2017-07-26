package com.softserve.edu.lv251.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11.07.2017.
 */
@Entity
public class Doctors extends Users {
    @Column(length = 10000)
    private String description;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Clinics clinics;
@JsonIgnore
    @OneToMany(mappedBy = "doctors", cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private List<Appointments> docAppointments;

    @ManyToOne
    private Specialization specialization;

    public Doctors() {
    }

    public List<Appointments> getDocAppointments() {
        return docAppointments;
    }

    public void setDocAppointments(List<Appointments> docAppointments) {
        this.docAppointments = docAppointments;
    }

    public Clinics getClinics() {
        return clinics;
    }

    public void setClinics(Clinics clinics) {
        this.clinics = clinics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "description='" + description + '\'' +
                ", clinics=" + clinics +
                ", docAppointments=" + docAppointments +
                ", specialization=" + specialization +
                '}';
    }
}
