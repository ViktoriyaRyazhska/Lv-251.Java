package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.BaseDAO;
import com.softserve.edu.lv251.dao.ContactsDAO;
import com.softserve.edu.lv251.dao.DoctorsDAO;
import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.*;
import com.softserve.edu.lv251.idl.WebRoles;

import com.softserve.edu.lv251.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 21.07.2017.
 */
@Service("doctorService")
public class DoctorServiceImpl extends PagingSizeServiceImpl<Doctor> implements DoctorsService {

    @Autowired
    private ContactsDAO contactsDAO;


    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DoctorsDAO doctorsDAO;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    ModeratorService moderatorService;

    @Autowired
    private Mapper mapper;


    @Override
    public void addDoctor(Doctor doctor) {
        doctorsDAO.addEntity(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorsDAO.getAllEntities();
    }

    @Override
    public void update(Doctor doctor) {
        doctorsDAO.updateEntity(doctor);
    }

    @Override
    public Doctor find(long id) {
        return doctorsDAO.getEntityByID(id);
    }

    @Override
    public void delete(Doctor doctor) {
        doctorsDAO.deleteEntity(doctor);
    }

    @Override
    public List<DoctorsSearchDTO> searchByLetters(String letters) {
        List<Doctor> doctors = doctorsDAO.searchByLetters(letters);
        List<DoctorsSearchDTO> results = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorsSearchDTO result = new DoctorsSearchDTO();
            mapper.map(doctor, result);
            results.add(result);
        }
        return results;


    }

    @Override
    public List<Doctor> getDoctorsByColumnNameAndValue(String columnName, Object value) {
        return this.doctorsDAO.getEntitiesByColumnNameAndValue(columnName, value);
    }

    @Override
    public Doctor findByEmail(String email) {
        List<Doctor> doctors = getDoctorsByColumnNameAndValue("email", email);
        return doctors.isEmpty() ? null : doctors.get(0);
    }

    @Transactional
    @Override
    public Doctor registerNewDoctorAccount(UserDTO accountDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstname(accountDto.getFirstName());
        doctor.setLastname(accountDto.getLastName());
        doctor.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        doctor.setEmail(accountDto.getEmail());
        doctor.setEnabled(true);
        doctor.setRoles(Arrays.asList(rolesService.findByName(WebRoles.ROLE_USER.name()),
                rolesService.findByName(WebRoles.ROLE_DOCTOR.name())));
        doctor.setPhoto(StoredImagesService.getDefaultPictureBase64encoded("User_Default.png"));
        Contact contact = new Contact();
        contact.setUser(doctor);
        contact.setEmail(accountDto.getEmail());
        this.contactsDAO.addEntity(contact);
        doctor.setContact(contact);
        addDoctor(doctor);

        return doctor;
    }

    public List<Appointment> appointmentsInThisMonth(Long id, Date date) {
        return doctorsDAO.appointmentsInThisMonth(id, date);
    }

    @Override
    public List<Doctor> searchByDistrict(String name) {
        return doctorsDAO.searchByDistrict(name);
    }

    @Override
    public List<Doctor> searchBySpecialization(String name) {
        return doctorsDAO.searchBySpecialization(name);
    }

    @Override
    public List<PatientDTO> getDoctorPatients(long doctorId) {
        List<PatientDTO> patients = new ArrayList<>();
        Doctor doctor = doctorsDAO.getEntityByID(doctorId);
        List<Appointment> appointments = doctor.getDocAppointments();
        for (Appointment a : appointments) {
            PatientDTO patient = new PatientDTO();
            mapper.map(a.getUser(), patient);
            patients.add(patient);
        }

        return patients;
    }

    @Override
    public BaseDAO<Doctor> getDao() {
        return doctorsDAO;
    }


    @Override
    public List<Doctor> getByClinic(Long clinicId) {
        List<Doctor> doctors = doctorsDAO.getEntitiesByColumnNameAndValue("clinics", clinicId);
        return doctors.isEmpty() ? null : doctors;
    }

    @Override
    @Transactional
    public Doctor addDoctorAccount(DoctorDTO accountDto) {
        Doctor doctor = new Doctor();

        doctor.setFirstname(accountDto.getFirstName());
        doctor.setLastname(accountDto.getLastName());

        doctor.setMiddlename("");
        doctor.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        doctor.setEmail(accountDto.getEmail());
        doctor.setEnabled(true);

        if (accountDto.getMultipartFile() != null) {
            doctor.setPhoto(StoredImagesService.getBase64encodedMultipartFile(accountDto.getMultipartFile()));
        } else {
            doctor.setPhoto(StoredImagesService.getDefaultPictureBase64encoded("User_Default.png"));
        }

        doctor.setRoles(Arrays.asList(
                rolesService.findByName(WebRoles.ROLE_DOCTOR.name()),
                rolesService.findByName(WebRoles.ROLE_USER.name())));
        Contact contact = new Contact();
        contact.setEmail(accountDto.getEmail());
        this.contactsDAO.addEntity(contact);
        doctor.setContact(contact);
        doctor.setDescription(accountDto.getDescription());
        if (specializationService.findByName(accountDto.getSpecialization()) == null) {
            Specialization specialization = new Specialization();
            specialization.setName(accountDto.getSpecialization());
            specializationService.add(specialization);
            doctor.setSpecialization(specialization);
        } else {
            doctor.setSpecialization(specializationService.findByName(accountDto.getSpecialization()));
        }
        doctor.setClinic(clinicService.getByName(accountDto.getClinic()));
        addDoctor(doctor);

        return doctor;
    }

    public List<SearchResultDoctorDTO> getDoctorByNameWithLimitAndOffset(String name, int offset, int limit) {
        List<Doctor> doctors;
        if (name == null) {
            doctors = doctorsDAO.getWithOffsetAndLimit(offset, limit);
        } else {
            doctors = doctorsDAO.searchByNameAndSpecialisationWithOffsetAndLimit(name, offset, limit);
        }
        List<SearchResultDoctorDTO> results = new ArrayList<>();
        for (Doctor doctor : doctors) {
            SearchResultDoctorDTO result = new SearchResultDoctorDTO();
            mapper.map(doctor, result);
            results.add(result);
        }

        return results;
    }


    @Override
    public DoctorsSearchDTO findById(long id) {
        Doctor doctor = doctorsDAO.getEntityByID(id);
        DoctorsSearchDTO doctorsSearchDTO = new DoctorsSearchDTO();
        mapper.map(doctor, doctorsSearchDTO);

        return doctorsSearchDTO;
    }
    @Transactional
    @Override
    public void makeDoctorFromUser(UserToDoctor userToDoctor, String email) {
        Moderator moderator=moderatorService.getByEmail(email);
        Clinic clinic =clinicService.getClinicByID(moderator.getClinics().getId());
        Doctor doctor=new Doctor();
        User user=userService.findByEmail(userToDoctor.getEmail());
        doctor.setFirstname(user.getFirstname());
        doctor.setLastname(user.getLastname());
        doctor.setPassword(user.getPassword());
        doctor.setEmail(user.getEmail());
        doctor.setPhoto(user.getPhoto());
        doctor.setSpecialization(specializationService.findByName(userToDoctor.getSpecialization()));
        doctor.setClinic(clinic);
        doctor.setContact(user.getContact());
        doctor.setDescription(userToDoctor.getDescription());
        doctor.setRoles(Arrays.asList(
                rolesService.findByName(WebRoles.ROLE_DOCTOR.name()),
                rolesService.findByName(WebRoles.ROLE_USER.name())));
        addDoctor(doctor);
        userService.deleteUser(user);


    }
}
