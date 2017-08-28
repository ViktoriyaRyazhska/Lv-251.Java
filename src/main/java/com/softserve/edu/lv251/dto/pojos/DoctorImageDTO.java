package com.softserve.edu.lv251.dto.pojos;

import com.softserve.edu.lv251.entity.Doctor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 31.07.2017.
 */
public class DoctorImageDTO extends Doctor {
    private BufferedImage img;

    DoctorImageDTO() {
    }

    DoctorImageDTO(Doctor doctor) {
        try {
            this.setImg(ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(doctor.getPhoto()))));
        } catch (Exception e) {
        }
        setLastname(doctor.getLastname());
        setAppointments(doctor.getAppointments());
        setClinic(doctor.getClinic());
        setContact(doctor.getContact());
        setDescription(doctor.getDescription());
        setDocAppointments(doctor.getDocAppointments());
        setEmail(doctor.getEmail());
        setEnabled(doctor.isEnabled());
        setFirstname(doctor.getFirstname());
        setLastname(doctor.getLastname());
        setMiddlename(doctor.getMiddlename());
        setPassword(doctor.getPassword());
        setPhoto(doctor.getPhoto());
        setRoles(doctor.getRoles());
        setSpecialization(doctor.getSpecialization());
        setTestsResults(doctor.getTestsResults());
        setTokenExpired(doctor.isTokenExpired());
        setId(doctor.getId());
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public static List<DoctorImageDTO> convert(List<Doctor> list) {
        List<DoctorImageDTO> list1 = new LinkedList<>();
        for (Doctor d :
                list) {
            list1.add(new DoctorImageDTO(d));
        }
        return list1;
    }

    public static DoctorImageDTO convert(Doctor doctor) {
        return new DoctorImageDTO(doctor);
    }
}
