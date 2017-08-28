package com.softserve.edu.lv251.config;

import com.softserve.edu.lv251.dao.ContactDAO;
import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.*;
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
import java.util.Calendar;
import java.util.List;

@Component
public class Mapper extends ConfigurableMapper {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RolesService rolesService;

    @Autowired
    ContactDAO contactDAO;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private ClinicService clinicService;

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(UserDTO.class, User.class)
                .field("firstName", "firstname")
                .field("lastName", "lastname")
                .field("password", "password")
                .field("email", "email")
                .exclude("matchingPassword")
                .byDefault().register();

        factory.classMap(PasswordDTO.class, User.class)
                .field("password", "password")
                .byDefault().register();

        factory.classMap(PersonalInfoDTO.class, User.class)
                .field("firstname", "firstname")
                .field("lastname", "lastname")
                .field("email", "email").customize(new CustomMapper<PersonalInfoDTO, User>() {

            @Override
            public void mapBtoA(User user, PersonalInfoDTO personalInfoDTO, MappingContext context) {
                personalInfoDTO.setPhoto(new Base64(user.getPhoto().getBytes()));
            }

            @Override
            public void mapAtoB(PersonalInfoDTO personalInfoDTO, User user, MappingContext context) {
                if (personalInfoDTO.getPhoto().getSize() != 0) {
                    String photo = StoredImagesService.getBase64encodedMultipartFile(personalInfoDTO.getPhoto());
                    user.setPhoto(photo);
                }
            }
        })
                .register();

        factory.classMap(PersonalInfoDTO.class, Contact.class)
                .field("address", "address")
                .field("city", "city")
                .field("zipCode", "zipCode")
                .field("firstPhone", "firstPhone")
                .field("secondPhone", "secondPhone")
                .field("thirdPhone", "thirdPhone")
                .field("email", "email")
                .byDefault().register();

        factory.classMap(ClinicInfoDTO.class, Clinic.class)
                .field("clinic_name", "clinic_name")
                .field("description", "description")

                .byDefault().register();

        factory.classMap(ClinicInfoDTO.class, Contact.class)
                .field("address", "address")
                .field("city", "city")
                .field("zipCode", "zipCode")
                .field("firstPhone", "firstPhone")
                .field("secondPhone", "secondPhone")
                .field("thirdPhone", "thirdPhone")
                .byDefault().register();

        factory.classMap(Clinic.class, ClinicLatLngDTO.class)
                .customize(new CustomMapper<Clinic, ClinicLatLngDTO>() {
                    @Override
                    public void mapAtoB(Clinic clinic, ClinicLatLngDTO latLng, MappingContext context) {
                        if (clinic.getContact() != null) {
                            double lat = clinic.getContact().getLatitude();
                            double lng = clinic.getContact().getLongitude();
                            latLng.setLat(lat);
                            latLng.setLng(lng);
                            latLng.setId(clinic.getId());
                        }
                    }
                }).register();

        factory.classMap(Appointment.class, AppointmentsForCreationDTO.class)
                .field("appointmentDate", "appointmentDate")
                .field("duration", "duration")
                .customize(new CustomMapper<Appointment, AppointmentsForCreationDTO>() {
                    @Override
                    public void mapAtoB(Appointment a, AppointmentsForCreationDTO b, MappingContext mappingContext) {
                        b.setDoctor(a.getDoctor().getId());
                    }
                })
                .register();

        factory.classMap(User.class, PatientDTO.class)
                .customize(new CustomMapper<User, PatientDTO>() {
                    @Override
                    public void mapAtoB(User user, PatientDTO patientDTO, MappingContext context) {
                        patientDTO.setId(user.getId());
                        String fullName = user.getLastname() + " "
                                + user.getFirstname() + " "
                                + user.getLastname();
                        patientDTO.setFullName(fullName);
                    }
                }).register();

        factory.classMap(Doctor.class, SearchResultDoctorDTO.class).customize(new CustomMapper<Doctor, SearchResultDoctorDTO>() {
            @Override
            public void mapAtoB(Doctor doctor, SearchResultDoctorDTO searchResultDoctorDTO, MappingContext context) {
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

                searchResultDoctorDTO.setClinicId(doctor.getClinic().getId());
                searchResultDoctorDTO.setClinicName(doctor.getClinic().getClinic_name());
            }

        }).register();


        factory.classMap(Clinic.class, SearchResultClinicDTO.class).customize(new CustomMapper<Clinic, SearchResultClinicDTO>() {
            @Override
            public void mapAtoB(Clinic clinic, SearchResultClinicDTO searchResultClinicDTO, MappingContext context) {


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

        factory.classMap(Appointment.class, AppointmentDTO.class).customize(new CustomMapper<Appointment, AppointmentDTO>() {
            @Override
            public void mapAtoB(Appointment appointment, AppointmentDTO appointmentDTO, MappingContext context) {
                appointmentDTO.setAppointmentDate(appointment.getAppointmentDate().getTime());
                appointmentDTO.setDuration(appointment.getDuration());
                appointmentDTO.setDescription(appointment.getDescription());
                appointmentDTO.setStatus(appointment.getIsApproved());

                appointmentDTO.setPatientId(appointment.getUser().getId());
                appointmentDTO.setPatientFirstName(appointment.getUser().getFirstname());
                appointmentDTO.setPatientLastName(appointment.getUser().getLastname());
                appointmentDTO.setPatientMiddleName(appointment.getUser().getMiddlename());

                appointmentDTO.setDoctorId(
                        appointment.getDoctor().getId());
                appointmentDTO.setDoctorFirstName(
                        appointment.getDoctor().getFirstname());
                appointmentDTO.setDoctorLastName(
                        appointment.getDoctor().getLastname());
                appointmentDTO.setDoctorMiddleName(
                        appointment.getDoctor().getMiddlename());
                appointmentDTO.setDoctorSpecialisation(
                        appointment.getDoctor().getSpecialization().getName());

                appointmentDTO.setClinicId(
                        appointment.getDoctor().getClinic().getId());
                appointmentDTO.setClinicName(
                        appointment.getDoctor().getClinic().getClinic_name());
            }
        }).register();

        factory.classMap(Clinic.class, ClinicSearchDTO.class)
                .field("id", "id")
                .field("clinic_name", "clinic_name")
                .field("photo", "photo")
                .field("description", "description")
                .byDefault().register();

        factory.classMap(District.class, DistrictsDTO.class)
                .field("name", "name")
                .byDefault().register();


        factory.classMap(Appointment.class, AppointmentsDTO.class)
                .customize(new CustomMapper<Appointment, AppointmentsDTO>() {
                    @Override
                    public void mapAtoB(Appointment appointment, AppointmentsDTO appointmentsDTO, MappingContext context) {
                        super.mapAtoB(appointment, appointmentsDTO, context);
                        appointmentsDTO.setId(appointment.getId());
                        appointment.getAppointmentDate().setTime(
                                appointment.getAppointmentDate().getTime()
                                        - Calendar.getInstance().getTimeZone().getRawOffset());
                        appointmentsDTO.setTitle(appointment.getUser().getFirstname() + " " + appointment.getUser().getLastname());
                        if (appointment.getIsApproved() != null) {
                            if (Calendar.getInstance().getTime().compareTo(appointment.getAppointmentDate()) < 0) {
                                appointmentsDTO.setColor(appointment.getIsApproved() ? "#4CAF50" : "#E53935");
                            } else {
                                appointmentsDTO.setColor(appointment.getIsApproved() ? "#424242" : "#546E7A");
                            }
                        }
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        appointmentsDTO.setStart(df.format(appointment.getAppointmentDate()));
                    }
                }).register();

        factory.classMap(Doctor.class, DoctorsSearchDTO.class)
                .field("id", "id")
                .field("firstname", "firstname")
                .field("lastname", "lastname")
                .field("middlename", "middlename")
                .field("description", "description")
                .field("photo", "photo")
                .customize(new CustomMapper<Doctor, DoctorsSearchDTO>() {
                    @Override
                    public void mapAtoB(Doctor doctor, DoctorsSearchDTO doctorsSearchDTO, MappingContext context) {
                        doctorsSearchDTO.setClinicName(doctor.getClinic().getClinic_name());
                        doctorsSearchDTO.setSpecialisation(doctor.getSpecialization().getName());
                    }
                }).register();

        factory.classMap(Appointment.class, AppointmentsForDateTimePickerInDocDTO.class)
                .field("appointmentDate", "appointmentDate")
                .field("duration", "duration")
                .customize(new CustomMapper<Appointment, AppointmentsForDateTimePickerInDocDTO>() {
                    @Override
                    public void mapAtoB(Appointment appointment, AppointmentsForDateTimePickerInDocDTO appointmentsForDateTimePickerInDocDTO, MappingContext context) {
                        super.mapAtoB(appointment, appointmentsForDateTimePickerInDocDTO, context);
                        appointmentsForDateTimePickerInDocDTO.setUsers(appointment.getDoctor().getId());
                    }
                })
                .register();

    }
}