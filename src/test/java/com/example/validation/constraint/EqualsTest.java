package com.example.validation.constraint;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EqualsTest {

    static Validator validator;

    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("同じ値を指定したらエラーが0個")
    public void test1() {
        TestDto testDto = new TestDto("hoge", "hoge");
        Set<ConstraintViolation<TestDto>> violations = validator.validate(testDto);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("違う値を指定したらエラーが1個")
    public void test2() {
        TestDto testDto = new TestDto("hoge", "hogeeeeee");
        Set<ConstraintViolation<TestDto>> violations = validator.validate(testDto);
        assertEquals(1, violations.size());
        String[] messages = violations.stream()
                .map(violation -> violation.getMessage())
                .toArray(String[]::new);
        assertEquals(1, messages.length);
        assertEquals("must be same", messages[0]);
    }

    @Equals(property1 = "text1", property2 = "text2", message = "must be same")
    static class TestDto {
        private String text1;
        private String text2;

        public TestDto(String text1, String text2) {
            this.text1 = text1;
            this.text2 = text2;
        }

        public String getText1() {
            return text1;
        }

        public String getText2() {
            return text2;
        }
    }

}
