package com.revenatium.startalent_sb.recruiters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;
    private final RecruiterRepository recruiterRepository;

    public RecruiterController(RecruiterService recruiterService, RecruiterRepository recruiterRepository) {
        this.recruiterService = recruiterService;
        this.recruiterRepository = recruiterRepository;
    }

    @GetMapping
    public ResponseEntity<Page<RecruiterResponse>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<RecruiterResponse> recruitersPage = recruiterService.listAllRecruiter(pageable);
        return ResponseEntity.ok(recruitersPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterResponse> getRecruiter(@PathVariable Long id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        if (recruiter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        RecruiterResponse recruiterResponse = RecruiterMapper.toResponse(recruiter.get());
        return ResponseEntity.ok(recruiterResponse);
    }

    @PostMapping
    public ResponseEntity<?> createRecruiter(@RequestBody RecruiterRequest recruiterRequest) {
        Optional<Recruiter> recruiter = recruiterRepository.findByUserId(recruiterRequest.getUserId());

        if (recruiter.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recruiter already exists for user with id: " + recruiterRequest.getUserId());
        }
        RecruiterResponse recruiterResponse = recruiterService.createRecruiter(recruiterRequest);

        return ResponseEntity.ok(recruiterResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecruiter(@RequestBody RecruiterRequest recruiterRequest, @PathVariable Long id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        if (recruiter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        RecruiterResponse recruiterResponse = recruiterService.updateRecruiter(id, recruiterRequest);

        return ResponseEntity.ok(recruiterResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruiter(@PathVariable Long id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        if (recruiter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        recruiterService.deleteRecruiter(id);

        return ResponseEntity.noContent().build();
    }
}
