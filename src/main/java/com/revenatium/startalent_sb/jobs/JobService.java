package com.revenatium.startalent_sb.jobs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface JobService {
    Page<JobResponse> listAllJobs(Pageable pageable);
    JobResponse addJob(JobRequest jobRequest);
    JobResponse updateJob(JobRequest jobRequest, Long id);
    void deleteJob(Long id);

}
