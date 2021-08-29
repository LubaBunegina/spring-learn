package ru.diasoft.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnswerLearn {
    private int numberAnswer;
    private String textAnswer;

    public Boolean isRightAnswer(List<AnswerLearn> rightAnswersList) {
        for(AnswerLearn rightAnswer : rightAnswersList){
            if(this.equals(rightAnswer)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        AnswerLearn objAnsw = (AnswerLearn) obj;

        return this.numberAnswer == objAnsw.numberAnswer &&
               this.textAnswer.equals(objAnsw.textAnswer);

    }
}
