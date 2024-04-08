package com.polarbookshop.catalogservice.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.polarbookshop.catalogservice.domain.Job;
import com.polarbookshop.catalogservice.domain.JobRepository;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryJobRepository implements JobRepository {

	private static final Map<Long, Job> jobs = new ConcurrentHashMap<>();

	@Override
	public Iterable<Job> findAll() {
		return jobs.values();
	}

	@Override
	public Optional<Job> findByJobID(long jobid) {
		return existsByJobID(jobid) ? Optional.of(jobs.get(jobid)) : Optional.empty();
	}

	@Override
	public boolean existsByJobID(long jobid) {
		return jobs.get(jobid) != null;
	}

	@Override
	public Job save(Job job) {
		jobs.put(job.jobid(), job);
		return job;
	}

	@Override
	public void deleteByJobID(long jobid) {
		jobs.remove(jobid);
	}

}
