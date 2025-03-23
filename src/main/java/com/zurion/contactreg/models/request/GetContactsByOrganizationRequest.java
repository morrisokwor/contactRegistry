package com.zurion.contactreg.models.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "GetContactsByOrganizationRequest", namespace = "http://interfaces.soapservice.zurion.com")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetContactsByOrganizationRequest {

    @XmlElement(required = true)
    private String organizationName;

}
