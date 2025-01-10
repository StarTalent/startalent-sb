package com.revenatium.startalent_sb.candidates;

import com.revenatium.startalent_sb.users.User;

public class CandidateMapper {
    public static CandidateResponse toResponse(Candidate candidate) {

        return CandidateResponse.builder()
            .id(candidate.getId())
            .profileData(candidate.getProfileData())
            .skills(candidate.getSkills())
            .experience(candidate.getExperience())
                .build();
    }

    public static Candidate toEntity(CandidateRequest candidateRequest, User user) {
        Candidate candidate = new Candidate();

        candidate.setUser(user);
        candidate.setProfileData(candidateRequest.getProfileData());
        candidate.setSkills(candidateRequest.getSkills());
        candidate.setExperience(candidateRequest.getExperience());

        return candidate;
    }
}
