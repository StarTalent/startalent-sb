package com.revenatium.startalent_sb.jobs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;
    private final JobRepository jobRepository;

    public JobController(JobService jobService, JobRepository jobRepository) {
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public ResponseEntity<Page<JobResponse>> listAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobResponse> jobsPage = jobService.listAllJobs(pageable);
        return ResponseEntity.ok(jobsPage);
    }


    @PostMapping
    public ResponseEntity<JobResponse> addJob(@RequestBody JobRequest jobRequest) {
        JobResponse jobResponse = jobService.addJob(jobRequest);

        URI location = URI.create(String.format("/jobs/%s", jobResponse.getId()));

        return ResponseEntity.created(location).body(jobResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody JobRequest jobRequest, @PathVariable Long id) {
        Optional<Job> job = jobRepository.findById(id);

        if (job.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró el registro con el ID: " + id);
        }
        jobService.updateJob(jobRequest, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        Optional<Job> job = jobRepository.findById(id);

        if (job.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el registro con el ID: " + id);
        }

        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
