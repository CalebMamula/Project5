package com.polarbookshop.jobservice.domain;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(long jobid) {
        super("The job id " + jobid + " was not found.");
    }

}
