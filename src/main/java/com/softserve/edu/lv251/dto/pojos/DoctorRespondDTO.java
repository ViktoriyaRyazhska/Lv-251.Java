package com.softserve.edu.lv251.dto.pojos;

/**
 * Created by Marian Brynetskyi on 29.08.2017.
 */
public class DoctorRespondDTO {

    private long id;

    private String firstname;

    private String lastname;

    private String clinic;

    private String specialization;

    private String photo;

    private boolean responded;

    public DoctorRespondDTO() {
    }

    public String getPhoto() {
        return photo;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorRespondDTO that = (DoctorRespondDTO) o;

        if (id != that.id) return false;
        if (!firstname.equals(that.firstname)) return false;
        if (!lastname.equals(that.lastname)) return false;
        if (!clinic.equals(that.clinic)) return false;
        return specialization.equals(that.specialization);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + clinic.hashCode();
        return result;
    }
}
