package com.back.answer;

import com.back.BaseEntity;
import com.back.question.Question;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Question question;
}
