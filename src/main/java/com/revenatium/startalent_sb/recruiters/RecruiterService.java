package com.revenatium.startalent_sb.recruiters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruiterService {
    Page<RecruiterResponse> listAllRecruiter(Pageable pageable);
    RecruiterResponse createRecruiter(RecruiterRequest recruiterRequest);
    RecruiterResponse updateRecruiter(Long id, RecruiterRequest recruiterRequest);
    void deleteRecruiter(Long id);
}
