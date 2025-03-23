package com.zurion.contactreg.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactModel {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String idNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String organization;
}
