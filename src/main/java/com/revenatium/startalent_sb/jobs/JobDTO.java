package com.revenatium.startalent_sb.jobs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revenatium.startalent_sb.accounts.Account;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDTO {

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
