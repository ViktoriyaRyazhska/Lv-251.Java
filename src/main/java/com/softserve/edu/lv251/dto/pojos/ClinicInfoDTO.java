package com.softserve.edu.lv251.dto.pojos;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Yana Martynyak on 02.08.2017.
 */
public class ClinicInfoDTO {

    //data from clinic
    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String clinic_name;
    private String description;

    //data from Contact
    private String address;
    private String city;
    private String zipCode;
    private String firstPhone;
    private String secondPhone;
    private String thirdPhone;
    private String email;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getThirdPhone() {
        return thirdPhone;
    }

    public void setThirdPhone(String thirdPhone) {
        this.thirdPhone = thirdPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
