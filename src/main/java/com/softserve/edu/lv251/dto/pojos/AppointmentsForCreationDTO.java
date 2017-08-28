package com.softserve.edu.lv251.dto.pojos;

import java.util.Date;

/**
 * Created by Marian Brynetskyi on 07.08.2017.
 */
public class AppointmentsForCreationDTO {
    private Date appointmentDate;
    private double duration;
    private long doctor;

    public AppointmentsForCreationDTO() {
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public long getDoctor() {
        return doctor;
    }

    public void setDoctor(long doctor) {
        this.doctor = doctor;
    }
}
