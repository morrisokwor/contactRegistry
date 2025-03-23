package com.zurion.contactreg.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "contact") // XML root element for SOAP
@XmlAccessorType(XmlAccessType.FIELD) // Enables XML field mapping
public class ContactDTO {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String idNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String organization;

    private String maskedName;
    private String maskedPhoneNumber;
    private String hashedPhoneNumber;

}
