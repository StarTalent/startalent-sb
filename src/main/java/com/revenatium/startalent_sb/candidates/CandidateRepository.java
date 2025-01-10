package com.revenatium.startalent_sb.candidates;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByProfileData(String profileData);

    List<Candidate> findBySkills(String skills);

    Optional<Candidate> findByUserId(Long id);
}
