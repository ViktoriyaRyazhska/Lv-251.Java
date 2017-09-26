package com.softserve.edu.lv251.dto.pojos;

import com.softserve.edu.lv251.customannotations.ValidEmail;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by Kovalevskyy Vitaliy.
 */
public class PersonalInfoDTO {

    //data from User
    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String firstname;

    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String lastname;

    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    @ValidEmail
    private String email;

    private MultipartFile photo;

    //data from Contact
    private String address;
    private String city;
    private String zipCode;
    private String firstPhone;
    private String secondPhone;
    private String thirdPhone;


    public PersonalInfoDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }


}
