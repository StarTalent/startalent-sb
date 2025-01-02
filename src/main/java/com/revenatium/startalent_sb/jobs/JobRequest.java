package com.revenatium.startalent_sb.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequest {
    private Long id;

    private String title;

    private String description;

    @JsonProperty("requirements")
    private String requirements;

    @JsonProperty("benefits")
    private String benefits;

    private boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long accountId;
}
