package com.revenatium.startalent_sb.recruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterResponse {
    private Long id;
    private Long userId;
    private String department;
    private String position;

}
