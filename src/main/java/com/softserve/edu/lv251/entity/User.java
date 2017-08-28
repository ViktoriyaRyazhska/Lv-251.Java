package com.softserve.edu.lv251.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends BaseEntity {

    private String firstname;
    private String lastname;

    @Column( columnDefinition = "varchar(255) default ''")
    private String middlename;

    private String email;
    private String password;



    @OneToMany(mappedBy = "from")
    private List<Message> messagesfrom;
    @OneToMany(mappedBy = "to")
    private List<Message> messagesTo;

    @Column(name = "enabled", nullable = false, columnDefinition = "bit(1) default 1")
    private boolean enabled;

    @Column(name = "tokenExpired", nullable = false, columnDefinition = "bit(1) default 1")
    private boolean tokenExpired;

    @Column(name = "photo", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String photo;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Respond> responds;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = Role.class)
    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Role> roles;

    @OneToOne
    private Contact contact;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<TestsResult> testsResults;


    public User() {
    }

    public List<Respond> getResponds() {
        return responds;
    }

    public void setResponds(List<Respond> responds) {
        this.responds = responds;
    }

    public List<TestsResult> getTestsResults() {
        return testsResults;
    }

    public void setTestsResults(List<TestsResult> testsResults) {
        this.testsResults = testsResults;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    @Override
    public String toString() {
        return "User{" +
                " firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
