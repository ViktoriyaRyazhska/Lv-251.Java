package com.softserve.edu.lv251.dto.pojos;

import com.softserve.edu.lv251.customannotations.EmailAlreadyExists;
import com.softserve.edu.lv251.customannotations.PasswordMatches;
import com.softserve.edu.lv251.customannotations.ValidEmail;
import com.softserve.edu.lv251.customannotations.ValidPassword;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Added by Pavlo Kuchereshko.
 * Used for transferring User entity into more convenient DTO object for registration purposes.
 */
@PasswordMatches
public class UserDTO {

    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String firstName;

    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String lastName;

    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    @ValidPassword
    private String password;
    private String matchingPassword;

    @NotBlank(message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    @ValidEmail
    @EmailAlreadyExists
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                " firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
