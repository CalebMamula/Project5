package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    void whenJobToCreateAlreadyExistsThenThrows() {
        var jobid = 0;
        var jobToCreate = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        when(jobRepository.existsByJobID(jobid)).thenReturn(true);
        assertThatThrownBy(() -> jobService.addJobToCatalog(jobToCreate))
                .isInstanceOf(JobAlreadyExistsException.class)
                .hasMessage("A job with JobID " + jobid + " already exists.");
    }

    @Test
    void whenJobToReadDoesNotExistThenThrows() {
        var jobid = 0;
        when(jobRepository.findByJobID(jobid)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> jobService.viewJobDetails(jobid))
                .isInstanceOf(JobNotFoundException.class)
                .hasMessage("The job with JobID " + jobid + " was not found.");
    }

}
