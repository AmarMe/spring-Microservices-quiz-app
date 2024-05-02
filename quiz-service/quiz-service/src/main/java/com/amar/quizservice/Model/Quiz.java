package com.amar.quizservice.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @SequenceGenerator(name = "quiz_seq",sequenceName = "quiz_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "quiz_seq")
    private Integer id;
    private String title;
    
    @ElementCollection
    private List<Integer> questionIds;

}
