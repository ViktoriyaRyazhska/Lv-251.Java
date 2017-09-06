package com.softserve.edu.lv251.dto.pojos;

public class DoctorSearchAngularDTO extends DoctorsSearchDTO {
    private String district;

    public DoctorSearchAngularDTO() {
    }

    public String getDistrict() {

        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
