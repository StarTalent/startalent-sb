package com.revenatium.startalent_sb.candidates;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateRepository candidateRepository;

    public CandidateController(CandidateService candidateService, CandidateRepository candidateRepository) {
        this.candidateService = candidateService;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping
    public ResponseEntity<Page<CandidateResponse>> listAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CandidateResponse> candidatesPage = candidateService.listAllCandidates(pageable);
        return ResponseEntity.ok(candidatesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidate(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CandidateResponse candidateResponse = CandidateMapper.toResponse(candidate.get());
        return ResponseEntity.ok(candidateResponse);
    }

    @PostMapping
    public ResponseEntity<?> addCandidate(@RequestBody CandidateRequest candidateRequest) {
        Optional<Candidate> candidate = candidateRepository.findByUserId(candidateRequest.getUserId());
        if (candidate.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ya existe un candidato con el ID: " + candidateRequest.getUserId());
        }
        CandidateResponse candidateResponse = candidateService.addCandidate(candidateRequest);

        URI location = URI.create(String.format("/candidates/%s", candidateResponse.getId()));

        return ResponseEntity.created(location).body(candidateResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidate(@RequestBody CandidateRequest candidateRequest, @PathVariable Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Candidate not found" + id);
        }

        CandidateResponse candidateResponse = candidateService.updateCandidate(candidateRequest, id, candidate.get());
        return ResponseEntity.ok(candidateResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Candidate not found" + id);
        }

        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

}
