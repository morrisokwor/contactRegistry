package com.zurion.contactreg.db.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Random;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contact")
@Where(clause = "deleted_at IS NULL")
public class ContactEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String phoneNumber;
    private String email;
    private String idNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String organization;

    private String maskedName;
    private String maskedPhoneNumber;
    private String hashedPhoneNumber;


    @PrePersist
    @PreUpdate
    private void processData() {
        this.maskedName = maskName(this.fullName);
        this.maskedPhoneNumber = maskPhoneNumber(this.phoneNumber);
        this.hashedPhoneNumber = hashPhoneNumber(this.phoneNumber);
    }

    private String maskName(String name) {
        if (name == null || !name.contains(" ")) return name;
        String[] parts = name.split(" ");
        return parts[0] + " " + parts[1].charAt(0) + ".";
    }

    private String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() < 9) return phone;

        Random random = new Random();

        int startIndex = 4 + random.nextInt(phone.length() - 6);

        StringBuilder maskedPhone = new StringBuilder(phone);
        maskedPhone.setCharAt(startIndex, '*');
        maskedPhone.setCharAt(startIndex + 1, '*');

        return maskedPhone.toString();
    }


    private String hashPhoneNumber(String phone) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(phone.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing phone number", e);
        }
    }
}
