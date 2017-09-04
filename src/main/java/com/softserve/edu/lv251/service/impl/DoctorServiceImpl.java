package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.BaseDAO;
import com.softserve.edu.lv251.dao.ContactDAO;
import com.softserve.edu.lv251.dao.DoctorDAO;
import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.*;
import com.softserve.edu.lv251.idl.WebRoles;
import com.softserve.edu.lv251.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Admin on 21.07.2017.
 */
@Service("doctorService")
public class DoctorServiceImpl extends PagingSizeServiceImpl<Doctor> implements DoctorService {

    @Autowired
    private ContactDAO contactDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    ModeratorService moderatorService;

    @Autowired
    private Mapper mapper;
    @Autowired
    private ContactsService contactsService;

    @Override
    public void addDoctor(Doctor doctor) {
        doctorDAO.addEntity(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorDAO.getAllEntities();
    }

    @Override
    public List<DoctorsSearchDTO> getAllDoctors() {
        List<DoctorsSearchDTO> list = new LinkedList<>();
        doctorDAO.getAllEntities().forEach((doctor)-> list.add(mapper.map(doctor,DoctorsSearchDTO.class)));
        return list;
    }

    @Override
    public void update(Doctor doctor) {
        doctorDAO.updateEntity(doctor);
    }

    @Override
    public Doctor find(long id) {
        return doctorDAO.getEntityByID(id);
    }

    @Override
    public void delete(Doctor doctor) {
        doctorDAO.deleteEntity(doctor);
    }

    @Override
    public List<DoctorsSearchDTO> searchByLetters(String letters) {
        List<Doctor> doctors = doctorDAO.searchByLetters(letters);
        System.out.println(doctors);
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
        return this.doctorDAO.getEntitiesByColumnNameAndValue(columnName, value);
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
        this.contactDAO.addEntity(contact);
        doctor.setContact(contact);
        addDoctor(doctor);

        return doctor;
    }

    public List<Appointment> appointmentsInThisMonth(Long id, Date date) {
        return doctorDAO.appointmentsInThisMonth(id, date);
    }

    @Override

    public List<DoctorsSearchDTO> searchByDistrict(String name) {

        List<Doctor> doctors = doctorDAO.searchByDistrict(name);
        List<DoctorsSearchDTO> results = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorsSearchDTO result = new DoctorsSearchDTO();
            mapper.map(doctor, result);
            results.add(result);
        }
        return results;

    }

    @Override

    public List<DoctorsSearchDTO> searchBySpecialization(String name) {

        List<Doctor> doctors = doctorDAO.searchBySpecialization(name);
        List<DoctorsSearchDTO> results = new ArrayList<>();

        for (Doctor doctor : doctors) {
            DoctorsSearchDTO result = new DoctorsSearchDTO();
            mapper.map(doctor, result);
            results.add(result);
        }
        return results;

    }

    @Override
    public List<PatientDTO> getDoctorPatients(long doctorId) {
        List<PatientDTO> patients = new ArrayList<>();
        Doctor doctor = doctorDAO.getEntityByID(doctorId);
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
        return doctorDAO;
    }


    @Override
    public List<Doctor> getByClinic(Long clinicId) {
        List<Doctor> doctors = doctorDAO.getEntitiesByColumnNameAndValue("clinic", clinicId);
        return doctors;
    }

    public List<DoctorsSearchDTO> getByClinic(Clinic clinic) {
        if (clinic == null) {
            return null;
        } else {
            List<Doctor> doctors = doctorDAO.getByClinic(clinic);
            List<DoctorsSearchDTO> results = new ArrayList<>();

            for (Doctor doctor : doctors) {
                DoctorsSearchDTO result = new DoctorsSearchDTO();
                mapper.map(doctor, result);
                results.add(result);
            }
            return results;
        }
    }

    @Override
    @Transactional
    public Doctor addDoctorAccount(DoctorDTO accountDto, String email) {
        Doctor doctor = new Doctor();
        Moderator moderator = moderatorService.getByEmail(email);
        Clinic clinic = clinicService.getClinicByID(moderator.getClinic().getId());
        doctor.setFirstname(accountDto.getFirstName());
        doctor.setLastname(accountDto.getLastName());
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
        this.contactDAO.addEntity(contact);
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
        doctor.setClinic(clinic);
        addDoctor(doctor);

        return doctor;
    }

    public List<SearchResultDoctorDTO> getDoctorByNameWithLimitAndOffset(String name, int offset, int limit) {
        List<Doctor> doctors;
        if (name == null) {
            doctors = doctorDAO.getWithOffsetAndLimit(offset, limit);
        } else {
            doctors = doctorDAO.searchByNameAndSpecialisationWithOffsetAndLimit(name, offset, limit);
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
        Doctor doctor = doctorDAO.getEntityByID(id);
        DoctorsSearchDTO doctorsSearchDTO = new DoctorsSearchDTO();
        mapper.map(doctor, doctorsSearchDTO);

        return doctorsSearchDTO;
    }

    @Transactional
    @Override
    public void makeDoctorFromUser(UserToDoctor userToDoctor, String email) {
        Moderator moderator = moderatorService.getByEmail(email);

        Clinic clinic = clinicService.getClinicByID(moderator.getClinic().getId());
        Doctor doctor = new Doctor();
        User user = userService.findByEmail(userToDoctor.getEmail());
        Contact contact= new Contact();
        contact.setEmail(user.getEmail());
        contactsService.addContacts(contact);
        doctor.setFirstname(user.getFirstname());
        doctor.setLastname(user.getLastname());
        doctor.setPassword(user.getPassword());
        doctor.setEmail(user.getEmail());
        doctor.setPhoto(user.getPhoto());
        if (specializationService.findByName(userToDoctor.getSpecialization())!=null){
        doctor.setSpecialization(specializationService.findByName(userToDoctor.getSpecialization()));}
        else
            {Specialization specialization = new Specialization();
            specialization.setName(userToDoctor.getSpecialization());
        }
        doctor.setClinic(clinic);
        doctor.setContact(contact);
        doctor.setDescription(userToDoctor.getDescription());
        doctor.setRoles(Arrays.asList(
                rolesService.findByName(WebRoles.ROLE_DOCTOR.name()),
                rolesService.findByName(WebRoles.ROLE_USER.name())));
        addDoctor(doctor);
//        userService.deleteUser(user);
    }

    @Override
    public List<DoctorRespondDTO> getDoctorsByUser(long userId) {
        List<DoctorRespondDTO> doctorRespondDTOS = new LinkedList<>();
        Date date = new Date();

        doctorDAO.getAllEntities().forEach(doctor -> {
            doctor.getDocAppointments().forEach(appointment -> {
                if (appointment.getUser().getId() == userId
                        && appointment.getIsApproved()
                        && appointment.getAppointmentDate().before(date)
                        ) {
                    doctorRespondDTOS.add(mapper.map(doctor, DoctorRespondDTO.class));
                }
            });
        });


        return doctorRespondDTOS.stream().distinct().collect(Collectors.toList());
    }


    @Override
    public Doctor getById(long doctorId) {
        return doctorDAO.getEntityByID(doctorId);

    }
}