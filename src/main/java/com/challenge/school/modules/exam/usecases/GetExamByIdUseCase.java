package com.challenge.school.modules.exam.usecases;

import com.challenge.school.exceptions.CustomNotFoundException;
import com.challenge.school.modules.exam.ExamMapper;
import com.challenge.school.modules.exam.ExamRepository;
import com.challenge.school.modules.exam.dto.ExamResponse;
import com.challenge.school.modules.exam.entities.Exam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetExamByIdUseCase {
    private final ExamRepository repository;
    private final ExamMapper examMapper = ExamMapper.INSTANCE;

    public ExamResponse execute(String id) {
        String message = String.format("Não foi possível encontrar exame com o id %s", id);

        Exam exam = repository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new CustomNotFoundException(message));

        return examMapper.toExamResponse(exam);
    }

    public Exam search(String id) {
        String message = String.format("Não foi possível encontrar exame com o id %s", id);

        return repository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new CustomNotFoundException(message));
    }
}
