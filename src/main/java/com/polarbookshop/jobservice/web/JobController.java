package com.polarbookshop.jobservice.web;

import jakarta.validation.Valid;

import com.polarbookshop.jobservice.domain.Job;
import com.polarbookshop.jobservice.domain.JobService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public Iterable<Job> get() {
        return jobService.viewJobList();
    }

    @GetMapping("{jobid}")
    public Job getByJobID(@PathVariable long jobid) {
        return jobService.viewJobDetails(jobid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job post(@Valid @RequestBody Job job) {
        return jobService.addJobToCatalog(job);
    }

    @DeleteMapping("{jobid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long jobid) {
        jobService.removeJobFromCatalog(jobid);
    }

    @PutMapping("{jobid}")
    public Job put(@PathVariable long jobid, @Valid @RequestBody Job job) {
        return jobService.editJobDetails(jobid, job);
    }

}
