package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.*;

public record Job (


    @Positive(message = "The Job ID must be positive")
    @Digits(integer = 6, fraction = 0, message = "The job ID must be 6 or less digits")

    long jobid,

    @NotBlank(message = "The job title must be defined.")
    String title,

    @NotBlank(message = "The description must be defined.")
    String description,
    @NotBlank(message = "The company name must be defined.")
    String companyname,

    @NotBlank(message = "The skill must be defined.")
    String skill1,
    @NotBlank(message = "The skill must be defined.")
    String skill2



    
){}
