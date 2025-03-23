package com.zurion.contactreg.db.repository;

import com.zurion.contactreg.db.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    List<ContactEntity> findByOrganization(String organization);

    Optional<ContactEntity> findByHashedPhoneNumber(String phoneHash);

    Optional<ContactEntity> findByMaskedNameAndMaskedPhoneNumber(String maskedName, String maskedPhoneNumber);
}
