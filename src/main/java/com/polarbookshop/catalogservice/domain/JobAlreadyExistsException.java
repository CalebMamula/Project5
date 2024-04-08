package com.polarbookshop.catalogservice.domain;

public class JobAlreadyExistsException extends RuntimeException {

    public JobAlreadyExistsException(long jobid) {
        super("A job with job ID " + jobid + " already exists.");
    }

}
