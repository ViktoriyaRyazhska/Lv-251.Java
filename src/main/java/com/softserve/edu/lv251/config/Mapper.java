package com.softserve.edu.lv251.config;

import com.softserve.edu.lv251.dao.ContactsDAO;
import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.*;
import com.softserve.edu.lv251.idl.WebRoles;
import com.softserve.edu.lv251.service.Base64;
import com.softserve.edu.lv251.service.ClinicService;
import com.softserve.edu.lv251.service.RolesService;
import com.softserve.edu.lv251.service.SpecializationService;
import com.softserve.edu.lv251.service.impl.StoredImagesService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
public class Mapper extends ConfigurableMapper{

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RolesService rolesService;

    @Autowired
    ContactsDAO contactsDAO;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private ClinicService clinicService;

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(UserDTO.class, Users.class)
                .field("firstName", "firstname")
                .field("lastName", "lastname")
                .field("password", "password")
                .field("email", "email")
                .exclude("matchingPassword")
                .byDefault().register();

        factory.classMap(PersonalInfoDTO.class, Users.class)
                .field("firstname", "firstname")
                .field("lastname", "lastname")
                .field("email", "email").customize(new CustomMapper<PersonalInfoDTO, Users>() {

            @Override
            public void mapBtoA(Users user, PersonalInfoDTO personalInfoDTO, MappingContext context) {
                personalInfoDTO.setPhoto(new Base64(user.getPhoto().getBytes()));
            }

            @Override
            public void mapAtoB(PersonalInfoDTO personalInfoDTO, Users user, MappingContext context) {
                if(personalInfoDTO.getPhoto().getSize() != 0){
                    String photo= StoredImagesService.getBase64encodedMultipartFile(personalInfoDTO.getPhoto());
                    user.setPhoto(photo);
                }
            }
        })
                .register();

        factory.classMap(PersonalInfoDTO.class, Contacts.class)
                .field("address", "address")
                .field("city", "city")
                .field("zipCode", "zipCode")
                .field("firstPhone", "firstPhone")
                .field("secondPhone", "secondPhone")
                .field("thirdPhone", "thirdPhone")
                .field("email", "email")
                .byDefault().register();

        factory.classMap(ClinicInfoDTO.class, Clinics.class)
                .field("clinic_name", "clinic_name")
                .field("description", "description")

                .byDefault().register();

        factory.classMap(ClinicInfoDTO.class, Contacts.class)
                .field("address", "address")
                .field("city", "city")
                .field("zipCode", "zipCode")
                .field("firstPhone", "firstPhone")
                .field("secondPhone", "secondPhone")
                .field("thirdPhone", "thirdPhone")
                .byDefault().register();

        factory.classMap(Clinics.class, ClinicLatLngDTO.class)
                .customize(new CustomMapper<Clinics, ClinicLatLngDTO>() {
                    @Override
                    public void mapAtoB(Clinics clinics, ClinicLatLngDTO latLng, MappingContext context) {
                        if(clinics.getContact()!=null){
                            double lat = clinics.getContact().getLatitude();
                            double lng = clinics.getContact().getLongitude();
                            latLng.setLat(lat);
                            latLng.setLng(lng);
                            latLng.setId(clinics.getId());
                        }
                    }
                });

        factory.classMap(Appointments.class, AppointmentsForCreationDTO.class)
                .field("appointmentDate", "appointmentDate")
                .field("duration", "duration")
                .customize(new CustomMapper<Appointments, AppointmentsForCreationDTO>() {
                    @Override
                    public void mapAtoB(Appointments a, AppointmentsForCreationDTO b, MappingContext mappingContext) {
                        b.setDoctors(a.getDoctors().getId());
                    }
                })
                .register();

        factory.classMap(Users.class, PatientDTO.class)
                .customize(new CustomMapper<Users, PatientDTO>() {
                    @Override
                    public void mapAtoB(Users users, PatientDTO patientDTO, MappingContext context) {
                        patientDTO.setId(users.getId());
                        String fullName = users.getLastname() + " "
                                + users.getFirstname() + " "
                                + users.getLastname();
                        patientDTO.setFullName(fullName);
                    }
                }).register();

        factory.classMap(Doctors.class, SearchResultDoctorDTO.class).customize(new CustomMapper<Doctors, SearchResultDoctorDTO>() {
            @Override
            public void mapAtoB(Doctors doctor, SearchResultDoctorDTO searchResultDoctorDTO, MappingContext context) {
                searchResultDoctorDTO.setId(doctor.getId());
                searchResultDoctorDTO.setDescription(doctor.getDescription());
                searchResultDoctorDTO.setFirstName(doctor.getFirstname());
                searchResultDoctorDTO.setLastName(doctor.getLastname());
                searchResultDoctorDTO.setMiddleName(doctor.getMiddlename());
                searchResultDoctorDTO.setSpecialisation(doctor.getSpecialization().getName());
                searchResultDoctorDTO.setPhoto(doctor.getPhoto());

                ContactsDTO contacts = new ContactsDTO();
                contacts.setAddress(doctor.getContact().getAddress());
                contacts.setLatitude(doctor.getContact().getLatitude());
                contacts.setLongitude(doctor.getContact().getLongitude());
                contacts.setCity(doctor.getContact().getCity());
                contacts.setDistrict(doctor.getContact().getDistrict().getName());
                contacts.setEmail(doctor.getContact().getEmail());
                List<String> phones = new ArrayList<>();
                phones.add(doctor.getContact().getFirstPhone());
                phones.add(doctor.getContact().getSecondPhone());
                phones.add(doctor.getContact().getThirdPhone());
                contacts.setPhones(phones);
                searchResultDoctorDTO.setContacts(contacts);

                searchResultDoctorDTO.setClinicId(doctor.getClinics().getId());
                searchResultDoctorDTO.setClinicName(doctor.getClinics().getClinic_name());
            }
        });

        factory.classMap(Clinics.class, SearchResultClinicDTO.class).customize(new CustomMapper<Clinics, SearchResultClinicDTO>() {
            @Override
            public void mapAtoB(Clinics clinic, SearchResultClinicDTO searchResultClinicDTO, MappingContext context) {


                searchResultClinicDTO.setId(clinic.getId());
                searchResultClinicDTO.setName(clinic.getClinic_name());
                searchResultClinicDTO.setDescription(clinic.getDescription());
                searchResultClinicDTO.setPhoto(clinic.getPhoto());

                ContactsDTO contacts = new ContactsDTO();
                contacts.setAddress(clinic.getContact().getAddress());
                contacts.setLatitude(clinic.getContact().getLatitude());
                contacts.setLongitude(clinic.getContact().getLongitude());
                contacts.setCity(clinic.getContact().getCity());
                contacts.setDistrict(clinic.getContact().getDistrict().getName());
                contacts.setEmail(clinic.getContact().getEmail());
                List<String> phones = new ArrayList<>();
                phones.add(clinic.getContact().getFirstPhone());
                phones.add(clinic.getContact().getSecondPhone());
                phones.add(clinic.getContact().getThirdPhone());
                contacts.setPhones(phones);
                searchResultClinicDTO.setContacts(contacts);

            }
        }).register();

        factory.classMap(Appointments.class, AppointmentDTO.class).customize(new CustomMapper<Appointments, AppointmentDTO>() {
            @Override
            public void mapAtoB(Appointments appointments, AppointmentDTO appointmentDTO, MappingContext context) {
                appointmentDTO.setAppointmentDate(appointments.getAppointmentDate().getTime());
                appointmentDTO.setDuration(appointments.getDuration());
                appointmentDTO.setDescription(appointments.getDescription());
                appointmentDTO.setStatus(appointments.getIsApproved());

                appointmentDTO.setPatientId(appointments.getUsers().getId());
                appointmentDTO.setPatientFirstName(appointments.getUsers().getFirstname());
                appointmentDTO.setPatientLastName(appointments.getUsers().getLastname());
                appointmentDTO.setPatientMiddleName(appointments.getUsers().getMiddlename());

                appointmentDTO.setDoctorId(
                        appointments.getDoctors().getId());
                appointmentDTO.setDoctorFirstName(
                        appointments.getDoctors().getFirstname());
                appointmentDTO.setDoctorLastName(
                        appointments.getDoctors().getLastname());
                appointmentDTO.setDoctorMiddleName(
                        appointments.getDoctors().getMiddlename());
                appointmentDTO.setDoctorSpecialisation(
                        appointments.getDoctors().getSpecialization().getName());

                appointmentDTO.setClinicId(
                        appointments.getDoctors().getClinics().getId());
                appointmentDTO.setClinicName(
                        appointments.getDoctors().getClinics().getClinic_name());
            }
        }).register();

        factory.classMap(Clinics.class,ClinicSearchDTO.class)
                .field("id","id")
                .field("clinic_name","clinic_name")
                .field("photo","photo")
                .field("description","description")
                .byDefault().register();

        factory.classMap(Districts.class,DistrictsDTO.class)
                .field("name","name")
                .byDefault().register();


        factory.classMap(Appointments.class,AppointmentsDTO.class)
                .customize(new CustomMapper<Appointments, AppointmentsDTO>() {
                    @Override
                    public void mapAtoB(Appointments appointments, AppointmentsDTO appointmentsDTO, MappingContext context) {
                        super.mapAtoB(appointments, appointmentsDTO, context);
                        appointmentsDTO.setId(appointments.getId());
                        appointmentsDTO.setTitle(appointments.getUsers().getFirstname() + appointments.getUsers().getLastname());
                        if (appointments.getIsApproved()!=null) {
                            if (Calendar.getInstance().getTime().compareTo(appointments.getAppointmentDate()) < 0) {
                                appointmentsDTO.setColor(appointments.getIsApproved() ? "#4CAF50" : "#E53935");
                            } else {
                                appointmentsDTO.setColor(appointments.getIsApproved() ? "#424242" : "#546E7A");
                            }
                        }
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        appointmentsDTO.setStart(df.format(appointments.getAppointmentDate()));
                    }
                }).register();

          factory.classMap(Doctors.class,DoctorsSearchDTO.class)
                  .field("id","id")
                  .field("firstname", "firstname")
                  .field("lastname", "lastname")
                  .field("middlename","middlename")
                  .field("description","description")
                  .field("photo","photo")
                  .customize(new CustomMapper<Doctors, DoctorsSearchDTO>() {
                      @Override
                      public void mapAtoB(Doctors doctors, DoctorsSearchDTO doctorsSearchDTO, MappingContext context) {
                          doctorsSearchDTO.setClinicName(doctors.getClinics().getClinic_name());
                          doctorsSearchDTO.setSpecialisation(doctors.getSpecialization().getName());
                      }
                  }).register();




//        factory.classMap(DoctorDTO.class,Doctors.class)
//                .field("firstName", "firstname")
//                .field("lastName", "lastname")
//                .field("description","description")
//                .field("email", "email").customize(new CustomMapper<DoctorDTO, Doctors>() {
//            @Override
//            public void mapAtoB(DoctorDTO doctorDTO, Doctors doctors, MappingContext context) {
//                String password=bCryptPasswordEncoder.encode(doctorDTO.getPassword());
//                doctors.setPassword(password);
//                String photo= StoredImagesService.getBase64encodedMultipartFile(doctorDTO.getMultipartFile());
//                doctors.setPhoto(photo);
//                doctors.setRoles(Arrays.asList(
//                        rolesService.findByName(WebRoles.ROLE_DOCTOR.name()),
//                        rolesService.findByName(WebRoles.ROLE_USER.name())));
//                Contacts contact = new Contacts();
//                contact.setEmail(doctorDTO.getEmail());
//                contactsDAO.addEntity(contact);
//                doctors.setContact(contact);
//                doctors.setDescription(doctorDTO.getDescription());
//                if(specializationService.findByName(doctorDTO.getSpecialization())==null){
//                    Specialization specialization= new Specialization();
//                    specialization.setName(doctorDTO.getSpecialization());
//                    specializationService.add(specialization);
//                    doctors.setSpecialization(specialization);
//                }else{ doctors.setSpecialization(specializationService.findByName(doctorDTO.getSpecialization()));
//                }
//                doctors.setClinics(clinicService.getByName(doctorDTO.getClinic()));
//
//            }
//        }).register();

    }
}