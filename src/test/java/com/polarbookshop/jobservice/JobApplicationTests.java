package com.polarbookshop.jobservice;

import com.polarbookshop.jobservice.domain.Job;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetRequestWithIdThenJobReturned() {
        var jobID = 0;
        var jobToCreate = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        Job expectedJob = webTestClient
                .post()
                .uri("/jobs")
                .bodyValue(jobToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Job.class).value(job -> assertThat(job).isNotNull())
                .returnResult().getResponseBody();

        webTestClient
                .get()
                .uri("/jobs/" + jobID)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Job.class).value(actualJob -> {
                    assertThat(actualJob).isNotNull();
                    assertThat(actualJob.jobid()).isEqualTo(expectedJob.jobid());
                });
    }

    @Test
    void whenPostRequestThenJobCreated() {
        var expectedJob = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");

        webTestClient
                .post()
                .uri("/jobs")
                .bodyValue(expectedJob)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Job.class).value(actualJob -> {
                    assertThat(actualJob).isNotNull();
                    assertThat(actualJob.jobid()).isEqualTo(expectedJob.jobid());
                });
    }

    @Test
    void whenPutRequestThenJobUpdated() {
        var jobID = 0;
        var jobToCreate = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        Job createdJob = webTestClient
                .post()
                .uri("/jobs")
                .bodyValue(jobToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Job.class).value(job -> assertThat(job).isNotNull())
                .returnResult().getResponseBody();
        var jobToUpdate = new Job(createdJob.jobid(), createdJob.title(), createdJob.description(), createdJob.companyname(), createdJob.skill1(), createdJob.skill2());

        webTestClient
                .put()
                .uri("/jobs/" + jobID)
                .bodyValue(jobToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Job.class).value(actualJob -> {
                    assertThat(actualJob).isNotNull();
                });
    }

    @Test
    void whenDeleteRequestThenJobDeleted() {
        var jobID = 0;
        var jobToCreate = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        webTestClient
                .post()
                .uri("/jobs")
                .bodyValue(jobToCreate)
                .exchange()
                .expectStatus().isCreated();

        webTestClient
                .delete()
                .uri("/jobs/" + jobID)
                .exchange()
                .expectStatus().isNoContent();

        webTestClient
                .get()
                .uri("/jobs/" + jobID)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(long.class).value(errorMessage ->
                    assertThat(errorMessage).isEqualTo("The job with JobID " + jobID + " was not found.")
                );
    }

}
