package com.polarbookshop.jobservice.domain;

public class JobAlreadyExistsException extends RuntimeException {

    public JobAlreadyExistsException(long jobid) {
        super("A job with job ID " + jobid + " already exists.");
    }

}
