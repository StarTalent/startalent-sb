package com.revenatium.startalent_sb.jobs;

import com.revenatium.startalent_sb.accounts.Account;

public class JobMapper {

    public static JobResponse toResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .benefits(job.getBenefits())
                .active(job.isActive())
                .accountId(job.getAccount() != null ? job.getAccount().getId() : null)
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .build();
    }

    public static Job toEntity(JobRequest jobRequest, Account account) {
        Job job = new Job();
        job.setId(jobRequest.getId());
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setActive(jobRequest.isActive());
        job.setAccount(account);
        job.setRequirements(jobRequest.getRequirements());
        job.setBenefits(jobRequest.getBenefits());
        job.setCreatedAt(jobRequest.getCreatedAt());
        job.setUpdatedAt(jobRequest.getUpdatedAt());

        return job;
    }
}
