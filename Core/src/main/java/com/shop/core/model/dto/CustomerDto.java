package com.shop.core.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CustomerDto implements BaseDto, Serializable {

    @Size(max = 10, min = 10, message = "National Id should be 10 digits.")
    @NotBlank(message = "National Id is mandatory.")
    private String nationalId;
    @NotBlank(message = "First Name is mandatory.")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory.")
    private String lastName;
    @Past(message = "Birth date is wrong.")
    private LocalDate birthDate;
    @NotBlank(message = "Certificate No is mandatory.")
    private String birthCertificateNo;
    @NotBlank(message = "Birth place is mandatory.")
    private String birthPlace;
    private ContactDto contactInfo;

    @Override
    public String toString() {
        return "{" +
                "\"nationalId\":'" + nationalId + '\'' +
                ", \"firstName\":'" + firstName + '\'' +
                ", \"lastName\":'" + lastName + '\'' +
                ", \"birthDate\":" + birthDate +
                ", \"birthCertificateNo\":'" + birthCertificateNo + '\'' +
                ", \"birthPlace\":'" + birthPlace + '\'' +
                ", \"contactInfo\":" + contactInfo +
                '}';
    }
}
