package com.revenatium.startalent_sb.jobs;

import com.revenatium.startalent_sb.accounts.Account;
import com.revenatium.startalent_sb.accounts.AccountRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Primary
public class JobServiceJPA implements JobService {

    private final JobRepository jobRepository;
    private final AccountRepository accountRepository;

    public JobServiceJPA(JobRepository jobRepository, AccountRepository accountRepository) {
        this.jobRepository = jobRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<JobResponse> listAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable)
                .map(JobMapper::toResponse);
    }

    @Override
    public JobResponse addJob(JobRequest jobRequest) {
        Account account = accountRepository.findById(jobRequest.getAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Job job = JobMapper.toEntity(jobRequest, account);
        Job savedJob = jobRepository.save(job);

        return JobMapper.toResponse(savedJob);
    }

    @Override
    public JobResponse updateJob(JobRequest jobRequest, Long id) {
        Job job = new Job();

        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setRequirements(jobRequest.getRequirements());
        job.setBenefits(jobRequest.getBenefits());
        job.setActive(jobRequest.isActive());

        Job updatedJob = jobRepository.save(job);

        return JobMapper.toResponse(updatedJob);
    }

    @Override
    public void deleteJob(Long id) {

        jobRepository.deleteById(id);
    }

}
