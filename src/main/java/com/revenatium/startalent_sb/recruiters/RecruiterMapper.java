package com.revenatium.startalent_sb.recruiters;

import com.revenatium.startalent_sb.users.User;

public class RecruiterMapper {

    public static RecruiterResponse toResponse(Recruiter recruiter) {
        return RecruiterResponse.builder()
                .id(recruiter.getId())
                .userId(recruiter.getUser() != null ? recruiter.getUser().getId() : null)
                .department(recruiter.getDepartment())
                .position(recruiter.getPosition())
                .build();
    }

    public static Recruiter toEntity(RecruiterRequest recruiterRequest, User user) {
        Recruiter recruiter = new Recruiter();

        recruiter.setId(recruiterRequest.getId());
        recruiter.setUser(user);
        recruiter.setDepartment(recruiterRequest.getDepartment());
        recruiter.setPosition(recruiterRequest.getPosition());

        return recruiter;
    }
}
