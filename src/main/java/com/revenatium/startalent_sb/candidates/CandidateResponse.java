package com.revenatium.startalent_sb.candidates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResponse {
    private Long id;

    @JsonProperty("profileData")
    private String profileData;

    private String skills;

    private String experience;
}
