package com.revenatium.startalent_sb.jobs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByTitle(String title);

    List<Job> findByDescription(String description);

    List<Job> findByIsActive(boolean isActive);

}
