package com.kwb.saller.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwb.saller.MyDateFormate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.DateFormat;

@Configuration
public class DateFormationConfiguration {

    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @Bean
    public MappingJackson2HttpMessageConverter MappingJsonpHttpMessageConverter() {
        ObjectMapper objectMapper = jackson2ObjectMapperBuilder.build();
        DateFormat dateFormat = objectMapper.getDateFormat();
        objectMapper.setDateFormat(new MyDateFormate(dateFormat));
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
        return mappingJackson2HttpMessageConverter;
    }
}
