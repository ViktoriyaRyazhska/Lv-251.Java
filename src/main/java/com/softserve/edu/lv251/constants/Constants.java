package com.softserve.edu.lv251.constants;

/**
 * Created by Marian Brynetskyi on 14.08.2017.
 */
public interface Constants {

    String BASE_PACKAGE = "com.softserve.edu.lv251";
    String MESSAGES_SOURCE = "classpath:messages";
    //for localhost use HOME = "http://localhost:8080";
    String HOME = "https://clinics-ua.herokuapp.com";

    interface Database {
        String PROPERTY_SOURCE = "classpath:db.properties";
    }

    interface Mail {
        String MAIL = "lv251clinics@gmail.com";
        String PASSWORD = "clinics251lv";
        String HOST = "smtp.gmail.com";
        int PORT = 587;
    }

    interface Controller {
        String DATE_FLAG = "flag";
        String LOGIN_FLAG = "flag";
        String DOCTOR_ID = "doctorId";
        String NUMBER_CHAIN = "numberChain";
        String MESSAGE = "message";
        String CLASS_CSS = "classCss";
        String DOC_APPS = "docApps";
        String PHOTO = "photo";
        String PERSONAL_INFO_DTO = "personalInfoDTO";
        String DOCTORS = "doctors";
        String MODERATOR = "moderator";
        String USER_FORM = "userForm";
        String DOCTOR_FORM = "doctorForm";
        String EMAIL = "email";
        String CURRENT = "current";
        String USERS_TO_DOCTOR = "usersToDoctor";
        String PASSWORD_DTO = "passwordDTO";
        String GET_DOCTORS = "getDoctors";
        String PHOTO_FORM = "photoForm";
        String CLINIC_DTO = "clinicDTO";
        String MESSAGES = "messages";
        String SUCCESS = "success";
        String FAILED = "failed";
    }

    interface Respond {
        int MAX_RAITING = 5;
        int MIN_RAITING = 0;
    }

    interface View {
        String REGISTRATION = "registration";
        String REGISTRATION_DOCTOR = "registrationDoctor";
        String ERROR_403 = "403";
        String ERROR_PAGE = "errorPage";
        String REDIRECT = "redirect:/";
        String REDIRECT_USER_CABINET = "redirect:/user/cabinet";
        String REDIRECT_ADMIN_CABINET = "redirect:/admin/cabinet";
        String REDIRECT_MODERATOR_CABINET = "redirect:/moderator/cabinet";
        String REDIRECT_MODERATOR_CABINET_DOCTOR = "redirect:/moderator/cabinet/doctors";
        String HOME = "home";
        String SUCCESS_REGISTRATION = "successRegistration";
        String AFTER_REGISTRATION = "afterRegistration";
        String USER_CABINET = "userCabinet";
        String USER_CABINET_MEDICAL_CARD = "userCabinetMedicalCard";
        String USER_CABINET_DOCTORS = "userCabinetDoctors";
        String MAP = "map";
        String CLINIC_DETAILS = "clinic_details";
        String CONTACT = "contact";
        String DOCTOR_SCHEDULE = "doctor_schedule";
        String DOCTOR_CABINET_PATIENTS2 = "doctor_cabinet_patients2";
        String MODERATOR_CABINET = "moderatorCabinet";
        String MODERATOR_CABINET_DOCTORS = "moderatorCabinetDoctors";
        String MODERATOR_ADD_DOCTOR = "moderatorAddDoctor";
        String MODERATOR_MAKE_DOCTOR = "moderatorMakeDoctor";
        String ALL_DOCTORS = "allDoctors";
        String DOCTOR_DETAILS = "doctor_details";
        String ADMIN_CABINET = "adminCabinet";
        String USER_CABINET_TESTS = "userCabinetTests";
        String DOCTOR_PATIENT = "doctorPatientTests";

    }
}