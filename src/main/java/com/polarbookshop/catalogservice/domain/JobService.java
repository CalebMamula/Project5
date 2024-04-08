package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Iterable<Job> viewJobList() {
        return jobRepository.findAll();
    }

    public Job viewJobDetails(long jobid) {
        return jobRepository.findByJobID(jobid)
                .orElseThrow(() -> new JobNotFoundException(jobid));
    }

    public Job addJobToCatalog(Job job) {
        if (jobRepository.existsByJobID(job.jobid())) {
            throw new JobAlreadyExistsException(job.jobid());
        }
        return jobRepository.save(job);
    }

    public void removeJobFromCatalog(long jobid) {

        jobRepository.deleteByJobID(jobid);
    }

    public Job editJobDetails(long jobid, Job job) {
		return jobRepository.findByJobID(jobid)
				.map(existingJob -> {
					var jobToUpdate = new Job(
							existingJob.jobid(),
                            job.title(),
                            job.description(),
                            job.companyname(),
                            job.skill1(),
                            job.skill2());
					return jobRepository.save(jobToUpdate);
				})
				.orElseGet(() -> addJobToCatalog(job));
    }

}
