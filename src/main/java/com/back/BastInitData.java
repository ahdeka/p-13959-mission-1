package com.back;

import com.back.question.Question;
import com.back.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev", "test"})  // This ensures that the data initialization runs only in dev and test profiles
@RequiredArgsConstructor
public class BastInitData implements ApplicationRunner{

    private final QuestionRepository questionRepository;

    @Override
    public void run(ApplicationArguments args) {
        // This method is intentionally left empty as we are using the ApplicationRunner bean below
        if (questionRepository.count() == 0) {
            Question question1 = new Question();
            question1.setSubject("안녕하세요.");
            question1.setContent("가입 인사 드립니다~");
            questionRepository.save(question1);

            Question question2 = new Question();
            question2.setSubject("질문 드립니다.");
            question2.setContent("ORM이 무엇인가요?");
            questionRepository.save(question2);
        }
    }

}
