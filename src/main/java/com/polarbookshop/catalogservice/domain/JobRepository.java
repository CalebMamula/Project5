package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

public interface JobRepository {

	Iterable<Job> findAll();
	Optional<Job> findByJobID(long jobid);
	boolean existsByJobID(long jobid);
	Job save(Job job);
	void deleteByJobID(long jobid);

}
