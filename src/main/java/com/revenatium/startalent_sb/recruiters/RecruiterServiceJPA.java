package com.revenatium.startalent_sb.recruiters;

import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.users.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RecruiterServiceJPA implements RecruiterService {
    private final RecruiterRepository recruiterRepository;
    private final UserRepository userRepository;

    public RecruiterServiceJPA(RecruiterRepository recruiterRepository, UserRepository userRepository) {
        this.recruiterRepository = recruiterRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<RecruiterResponse> listAllRecruiter(Pageable pageable) {
        return recruiterRepository.findAll(pageable)
                .map(RecruiterMapper::toResponse);
    }


    public RecruiterResponse createRecruiter(RecruiterRequest recruiterRequest) {
        User user = userRepository.findById(recruiterRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Recruiter recruiter = RecruiterMapper.toEntity(recruiterRequest, user);
        Recruiter savedRecruiter = recruiterRepository.save(recruiter);

        return RecruiterMapper.toResponse(savedRecruiter);
    }

    public RecruiterResponse updateRecruiter(Long id, RecruiterRequest recruiterRequest) {
        Recruiter recruiter = new Recruiter();

        recruiter.setId(id);
        recruiter.setUser(userRepository.findById(recruiterRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        recruiter.setDepartment(recruiterRequest.getDepartment());
        recruiter.setPosition(recruiterRequest.getPosition());

        Recruiter updatedRecruiter = recruiterRepository.save(recruiter);

        return RecruiterMapper.toResponse(updatedRecruiter);
    }

    public void deleteRecruiter(Long id) {

        recruiterRepository.deleteById(id);
    }
}
