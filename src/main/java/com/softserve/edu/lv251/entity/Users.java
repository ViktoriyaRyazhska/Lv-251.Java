package com.softserve.edu.lv251.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Users extends BaseEntity {

    private String firstname;
    private String lastname;
    private String middlename;
    private String email;
    private String password;

    @Column(name = "photo", nullable = false, length = 65535, columnDefinition="TEXT")
    private String photo;
@JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Appointments> appointments;
    @JsonIgnore
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Roles> roles;

    @OneToOne
    private Contacts contact;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<MedicalCard> medicalCards;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<TestsResults> testsResults;


    public Users() {
    }

    public List<TestsResults> getTestsResults() {
        return testsResults;
    }

    public void setTestsResults(List<TestsResults> testsResults) {
        this.testsResults = testsResults;
    }

    public List<MedicalCard> getMedicalCards() {
        return medicalCards;
    }

    public void setMedicalCards(List<MedicalCard> medicalCards) {
        this.medicalCards = medicalCards;
    }

    public Contacts getContact() {
        return contact;
    }

    public void setContact(Contacts contact) {
        this.contact = contact;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
