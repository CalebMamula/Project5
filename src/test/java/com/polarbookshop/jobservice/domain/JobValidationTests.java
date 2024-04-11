package com.polarbookshop.jobservice.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JobValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var job = new Job(1234, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnNotDefinedThenValidationFails() {
        var job = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(2);
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(constraintViolationMessages)
                .contains("The job JobID must be defined.")
                .contains("The JobID format must be valid.");
    }

    @Test
    void whenJobIDDefinedButIncorrectThenValidationFails() {
        var job = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The JobID format must be valid.");
    }

    @Test
    void whenTitleIsNotDefinedThenValidationFails() {
        var job = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The job title must be defined.");
    }

    @Test
    void whenJobIsNotDefinedThenValidationFails() {
        var job = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The job author must be defined.");
    }


}
