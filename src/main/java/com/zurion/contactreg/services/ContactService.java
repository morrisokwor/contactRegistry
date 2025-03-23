package com.zurion.contactreg.services;

import com.zurion.contactreg.db.entities.ContactEntity;
import com.zurion.contactreg.db.repository.ContactRepository;
import com.zurion.contactreg.models.dto.ContactDTO;
import com.zurion.contactreg.models.request.ContactModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;


    public ContactDTO add(ContactModel model) {
        var contact = modelMapper.map(model, ContactEntity.class);

        return modelMapper.map(contactRepository.save(contact), ContactDTO.class);
    }

    public ContactDTO update(ContactModel model) {
        var contact = contactRepository.findById(model.getId()).orElseThrow(() ->
                new RuntimeException("Contact not found"));
        modelMapper.map(model, contact);
        return modelMapper.map(contactRepository.save(contact), ContactDTO.class);
    }

    public ContactDTO findById(Long id) {
        return modelMapper.map(contactRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Contact Not FOund")), ContactDTO.class);
    }

    public List<ContactDTO> findAll(Integer page, Integer size) {
        if (Optional.ofNullable(page).isPresent() && Optional.ofNullable(size).isPresent()) {
            return contactRepository.findAll(PageRequest.of(page, size)).getContent()
                    .stream().map(contact -> modelMapper.map(contact, ContactDTO.class))
                    .collect(Collectors.toList());
        } else {
            return contactRepository.findAll()
                    .stream().map(contact -> modelMapper.map(contact, ContactDTO.class))
                    .collect(Collectors.toList());
        }
    }

    /**
     * This is a soft Delete
     */
    public void delete(Long id) {

        var contact = contactRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Contact not found"));
        contact.setDeletedAt(LocalDateTime.now());
        contactRepository.save(contact);
    }

    public List<ContactDTO> findByOrganization(String organizationName) {
        return contactRepository.findByOrganization(organizationName).stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toList());
    }

    public ContactDTO searchByPhoneHash(String phoneHash) {
        return modelMapper.map(contactRepository.findByHashedPhoneNumber(phoneHash).orElse(null), ContactDTO.class);
    }

    public ContactDTO searchByMaskedDetails(String maskedName, String maskedPhone) {
        return modelMapper.map(contactRepository.findByMaskedNameAndMaskedPhoneNumber(maskedName, maskedPhone).orElse(null), ContactDTO.class);
    }

}
