package com.polarbookshop.jobservice.web;

import com.polarbookshop.jobservice.domain.Job;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class JobJsonTests {

    @Autowired
    private JacksonTester<Job> json;

    @Test
    void testSerialize() throws Exception {
        var job = new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running");
        var jsonContent = json.write(job);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.jobid")
                .isEqualTo(job.jobid());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(job.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description")
                .isEqualTo(job.description());
        assertThat(jsonContent).extractingJsonPathStringValue("@.companyname")
                .isEqualTo(job.companyname());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill1")
                .isEqualTo(job.skill1());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill2")
                .isEqualTo(job.skill2());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "jobid": 0,
                    "title": "Title",
                    "description": "Author",
                    "companyname": "Walrus Inc",
                    "skill1": "Jumping",
                    "skill2": "Running"
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Job(0, "Title", "Author", "Walrus Inc", "Jumping", "Running"));
    }

}
