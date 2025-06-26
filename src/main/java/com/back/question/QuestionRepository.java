package com.back.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findBySubject(String subject);
    List<Question> findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
}
