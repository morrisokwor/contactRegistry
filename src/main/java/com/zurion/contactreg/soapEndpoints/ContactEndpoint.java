package com.zurion.contactreg.soapEndpoints;

import com.zurion.contactreg.models.dto.ContactDTO;
import com.zurion.contactreg.services.ContactService;
import com.zurion.soapservice.interfaces.Contact;
import com.zurion.soapservice.interfaces.GetContactsByOrganizationRequest;
import com.zurion.soapservice.interfaces.GetContactsByOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;


@Endpoint
@RequiredArgsConstructor
public class ContactEndpoint {
    private final ContactService contactService;
    private static final String NAMESPACE_URI = "http://interfaces.soapservice.zurion.com";
    private final ModelMapper modelMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetContactsByOrganizationRequest")
    @ResponsePayload
    public GetContactsByOrganizationResponse getContactsByOrganization(@RequestPayload GetContactsByOrganizationRequest request) {

        List<ContactDTO> contacts = contactService.findByOrganization(request.getOrganizationName());

        GetContactsByOrganizationResponse response = new GetContactsByOrganizationResponse();
        var res = contacts.stream().map(contactDTO -> modelMapper.map(contactDTO, Contact.class)).collect(Collectors.toList());
        response.getContact().addAll(res);
        return response;
    }
}
