package borysov.extractor.impl;

import borysov.entity.Answer;
import borysov.extractor.ExtractorQuationForm;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ExtractorQuationFormImpl implements ExtractorQuationForm {
    public List<Answer> extract(HttpServletRequest request) {
        List<Answer> answers = new ArrayList<Answer>();

        for (int i = 1; i <= 4 ; i++) {
            String answerText = request.getParameter("answer_field"+i);
            boolean isRight = (request.getParameter("answer_is_right_field"+i) != null);

            Answer answer = new Answer();
            answer.setText(answerText);
            answer.setRightAnser(isRight);
            answers.add(answer);
        }

        return answers;
    }
}