package com.zurion.contactreg.controllers;

import com.zurion.contactreg.models.dto.ContactDTO;
import com.zurion.contactreg.models.request.ContactModel;
import com.zurion.contactreg.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ContactDTO addContact(@RequestBody ContactModel model) {

        return contactService.add(model);
    }

    @PutMapping
    public ContactDTO updateContact(@RequestBody ContactModel model) {
        return contactService.update(model);
    }

    @GetMapping("/{id}")
    public ContactDTO findById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @GetMapping("all")
    public List<ContactDTO> getAllContacts(@RequestParam(required = false) Integer page,
                                           @RequestParam(required = false) Integer size) {
        return contactService.findAll(page, size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search/phoneHash")
    public ContactDTO searchByPhoneHash(@RequestParam String phoneHash) {
        return contactService.searchByPhoneHash(phoneHash);
    }

    @PostMapping("/search/masked")
    public ContactDTO searchByMaskedDetails(@RequestParam String maskedName,
                                            @RequestParam String maskedPhone) {
        return contactService.searchByMaskedDetails(maskedName, maskedPhone);
    }

    @PostMapping("/search/organization")
    public List<ContactDTO> searchByOrganization(@RequestParam String organizationName) {

        return contactService.findByOrganization(organizationName);
    }

}
