package com.tsystems.javaschool.medical.backend.util;

import org.springframework.context.annotation.Bean;

public class ModelMapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
