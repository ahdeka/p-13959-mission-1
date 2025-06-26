package com.back;

import com.back.answer.Answer;
import com.back.answer.AnswerRepository;
import com.back.question.Question;
import com.back.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest
class BackApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("findAll")
	void t1() {
		List<Question> questionList = questionRepository.findAll();
		assertThat(questionList.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("findById")
	void t2() {
		Optional<Question> optionalQuestion = questionRepository.findById(1);

		assertThat(optionalQuestion.isPresent());
		Question question = optionalQuestion.get();
		assertThat("안녕하세요.").isEqualTo(question.getSubject());
	}

	@Test
	@DisplayName("findBySubject")
	void t3() {
		List<Question> questions = questionRepository.findBySubject("안녕하세요.");

		assertThat(questions).isNotEmpty();
		assertThat(questions.get(0).getSubject()).isEqualTo("안녕하세요.");
	}

	@Test
	@DisplayName("findBySubjectAndContent")
	void t4() {
		List<Question> questions = questionRepository.findBySubjectAndContent("안녕하세요.", "가입 인사 드립니다~");

		assertThat(questions).isNotEmpty();
		assertThat(questions.get(0).getSubject()).isEqualTo("안녕하세요.");
		assertThat(questions.get(0).getContent()).isEqualTo("가입 인사 드립니다~");
	}

	@Test
	@DisplayName("findBySubjectLike")
	void t5() {
		List<Question> questions = questionRepository.findBySubjectLike("안녕%");
		assertThat(questions).isNotEmpty();
		assertThat(questions.get(0).getSubject()).isEqualTo("안녕하세요.");
	}

	@Test
	@DisplayName("수정")
	void t6() {
		Optional<Question> optionalQuestion = questionRepository.findById(1);
		assertThat(optionalQuestion.isPresent());
		Question question = optionalQuestion.get();
		question.setSubject("수정된 제목 - 안녕하십니까");
		questionRepository.save(question);
		assertThat(question.getSubject()).isEqualTo("수정된 제목 - 안녕하십니까");
	}

	@Test
	@DisplayName("삭제")
	void t7() {
		assertThat(questionRepository.count()).isEqualTo(2);
		Optional<Question> optionalQuestion = questionRepository.findById(1);
		assertThat(optionalQuestion.isPresent());
		Question question = optionalQuestion.get();
		questionRepository.delete(question);
		assertThat(questionRepository.count()).isEqualTo(1);
	}

	@Test
	@DisplayName("답변 저장")
	void t8() {
		Optional<Question> optionalQuestion = questionRepository.findById(1);
		assertThat(optionalQuestion.isPresent());
		Question question = optionalQuestion.get();

		Answer answer = new Answer();
		answer.setContent("반갑습니다~");
		answer.setQuestion(question);
		answerRepository.save(answer);
		assertThat(answerRepository.count()).isEqualTo(1);
	}
}
