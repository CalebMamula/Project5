package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.JobNotFoundException;
import com.polarbookshop.catalogservice.domain.JobService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Test
    void whenGetJobNotExistingThenShouldReturn404() throws Exception {
        long jobid = 1234;
        given(jobService.viewJobDetails(jobid)).willThrow(JobNotFoundException.class);
        mockMvc
                .perform(get("/jobs/" + jobid))
                .andExpect(status().isNotFound());
    }

}
