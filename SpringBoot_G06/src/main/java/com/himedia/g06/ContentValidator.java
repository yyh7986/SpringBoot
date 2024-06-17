package com.himedia.g06;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    } // 거의 사용할 일이 없음

    @Override
    public void validate(Object target, Errors errors) {
        // Object target : 검사할 객체(contentdto)를 받는 매개변수(Object형). 전달된 객체 멤버변수가 비어있는지 검사예정
        // Errors erros : 보내온 객체에 에러내용을 담을 매개변수

        // 자료형 복원
        ContentDto dto = (ContentDto) target;

        /* 검사 방법 #1
        // 전송된 값들 추출
        String sWriter = dto.getWriter();
        String sContent = dto.getContent();

        // 각필드가 null 이거나 비어있다면
        if(sWriter == null || sWriter.isEmpty()){
            errors.rejectValue("writer", "trouble");
        }
        if(sContent == null || sContent.trim().isEmpty()){
            errors.rejectValue("content", "trouble");
        }
        */

        // 검사 방법 #2
        // 전달객체의 멤버변수를 꺼내어 보지 않고 null이거나 비어있는지 점검. 결과는 error에 저장
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty");
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }   // 거의 사용할 일이 없음
}
