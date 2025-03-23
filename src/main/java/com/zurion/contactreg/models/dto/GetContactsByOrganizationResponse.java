package com.zurion.contactreg.models.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "GetContactsByOrganizationResponse", namespace = "http://interfaces.soapservice.zurion.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetContactsByOrganizationResponse {

    @XmlElement(name = "contact")
    private List<ContactDTO> contacts;

}
