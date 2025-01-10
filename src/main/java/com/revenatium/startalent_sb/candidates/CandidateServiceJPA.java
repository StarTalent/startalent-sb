package com.revenatium.startalent_sb.candidates;

import com.revenatium.startalent_sb.exceptions.UserNotFoundException;
import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.users.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CandidateServiceJPA implements CandidateService {

        private final CandidateRepository candidateRepository;
        private final UserRepository userRepository;

        public CandidateServiceJPA(CandidateRepository candidateRepository, UserRepository userRepository) {
            this.candidateRepository = candidateRepository;
            this.userRepository = userRepository;
        }

        @Override
        public CandidateResponse addCandidate(CandidateRequest candidateRequest) {
            User user = userRepository.findById(candidateRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
            Candidate candidate = CandidateMapper.toEntity(candidateRequest, user);
            candidateRepository.save(candidate);
            return CandidateMapper.toResponse(candidate);
        }

        @Override
        public CandidateResponse updateCandidate(CandidateRequest candidateRequest, Long id, Candidate candidateSaved) {
            Candidate candidate = new Candidate();

            candidate.setId(id);
            candidate.setUser(candidateSaved.getUser());
            candidate.setProfileData(candidateRequest.getProfileData());
            candidate.setSkills(candidateRequest.getSkills());
            candidate.setExperience(candidateRequest.getExperience());

            Candidate updatedCandidate = candidateRepository.save(candidate);

            return CandidateMapper.toResponse(updatedCandidate);
        }

        @Override
        public void deleteCandidate(Long id) {
            candidateRepository.deleteById(id);
        }

        @Override
        public Page<CandidateResponse> listAllCandidates(Pageable pageable) {
            return candidateRepository.findAll(pageable).map(CandidateMapper::toResponse);
        }
}
