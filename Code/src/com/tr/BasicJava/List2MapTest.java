package com.tr.BasicJava;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *     public List<QuestionVO> getQuestions(Set<String> questionIds) {
 *         return questionDao.findByIdIn(questionIds).stream()
 *                 .map(q -> (QuestionVO) Converter.map(q, QuestionClazz.VO_CLAZZ.getClazzByType(q.getQuestionType())))
 *                 .collect(Collectors.toList());
 *     }
 */
public class List2MapTest {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("a",1));
        list.add(new Student("b", 2));

        //to map
        Converter.map
        Stream<Student> stream = list.stream();
        stream.map(q -> (Student) Converter.map(q, StudentCla.VO_CLAZZ.getClazzByType(q.getQuestionType())))
                .collect(Collectors.toList())
    }
}
