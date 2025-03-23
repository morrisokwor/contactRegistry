package com.zurion.contactreg.configs;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Set;


@Configuration
public class AppConfigs {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Set<String>, Set<String>> stringSetConverter = new AbstractConverter<Set<String>, Set<String>>() {
            @Override
            protected Set<String> convert(Set<String> source) {
                return source;
            }
        };
        modelMapper.addConverter(stringSetConverter);
        return modelMapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
