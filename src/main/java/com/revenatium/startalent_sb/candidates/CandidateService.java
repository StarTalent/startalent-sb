package com.revenatium.startalent_sb.candidates;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateService {
    CandidateResponse addCandidate(CandidateRequest candidateRequest);
    CandidateResponse updateCandidate(CandidateRequest candidateRequest, Long id, Candidate candidateSaved);
    void deleteCandidate(Long id);
    Page<CandidateResponse> listAllCandidates(Pageable pageable);
}
