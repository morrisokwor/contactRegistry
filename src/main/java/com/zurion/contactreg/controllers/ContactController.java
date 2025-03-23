package com.zurion.contactreg.controllers;

import com.zurion.contactreg.models.dto.ContactDTO;
import com.zurion.contactreg.models.dto.ResponseObject;
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
    public ResponseEntity<ResponseObject> addContact(@RequestBody ContactModel model) {

        return ResponseEntity.ok(ResponseObject.builder()
                .data(contactService.add(model))
                .success(true)
                .message("Contact added successfully")
                .build());

    }

    @PutMapping
    public ResponseEntity<ResponseObject> updateContact(@RequestBody ContactModel model) {
        return ResponseEntity.ok(ResponseObject.builder()
                .data(contactService.update(model))
                .success(true)
                .message("Contact updated successfully")
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseObject.builder()
                .data(contactService.findById(id))
                .success(true)
                .message("Successfully Fetched Contact by ID")
                .build());
    }

    @GetMapping("all")
    public ResponseEntity<ResponseObject> getAllContacts(@RequestParam(required = false) Integer page,
                                                         @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(ResponseObject.builder()
                .data(contactService.findAll(page, size))
                .success(true)
                .message("Successfully fetched all contacts")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/phoneHash")
    public ResponseEntity<ResponseObject> searchByPhoneHash(@RequestParam String phoneHash) {
        return ResponseEntity.ok(ResponseObject.builder()
                .data(contactService.searchByPhoneHash(phoneHash))
                .success(true)
                .message("Successfully fetched by Phone Hash")
                .build());
    }

    @GetMapping("/search/masked")
    public ResponseEntity<ResponseObject> searchByMaskedDetails(@RequestParam String maskedName,
                                                                @RequestParam String maskedPhone) {
        return ResponseEntity.ok(ResponseObject.builder()
                .data(contactService.searchByMaskedDetails(maskedName, maskedPhone))
                .success(true)
                .message("Successfully fetched by Masked Details")
                .build());
    }

    @GetMapping("/search/organization")
    public ResponseEntity<ResponseObject> searchByOrganization(@RequestParam String organizationName) {

        return ResponseEntity.ok(ResponseObject.builder()
                .data(contactService.findByOrganization(organizationName))
                .success(true)
                .message("Successfully fetched by Organization")
                .build());
    }

}
