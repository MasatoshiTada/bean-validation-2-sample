package com.example.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatableTest {

    static Validator validator;

    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void nonRepeatableTest() {
        NonRepeatableDto dto100 = new NonRepeatableDto(100);
        NonRepeatableDto dto150 = new NonRepeatableDto(150);
        NonRepeatableDto dto200 = new NonRepeatableDto(200);
        NonRepeatableDto dto250 = new NonRepeatableDto(250);

        Set<ConstraintViolation<NonRepeatableDto>> violations100G100 = validator.validate(dto100, LessThanOrEquals100.class);
        assertEquals(0, violations100G100.size());
        Set<ConstraintViolation<NonRepeatableDto>> violations100G200 = validator.validate(dto100, LessThanOrEquals200.class);
        assertEquals(0, violations100G200.size());

        Set<ConstraintViolation<NonRepeatableDto>> violations150G100 = validator.validate(dto150, LessThanOrEquals100.class);
        assertEquals(1, violations150G100.size());
        Set<ConstraintViolation<NonRepeatableDto>> violations150G200 = validator.validate(dto150, LessThanOrEquals200.class);
        assertEquals(0, violations150G200.size());

        Set<ConstraintViolation<NonRepeatableDto>> violations200G100 = validator.validate(dto200, LessThanOrEquals100.class);
        assertEquals(1, violations200G100.size());
        Set<ConstraintViolation<NonRepeatableDto>> violations200G200 = validator.validate(dto200, LessThanOrEquals200.class);
        assertEquals(0, violations200G200.size());

        Set<ConstraintViolation<NonRepeatableDto>> violations250G100 = validator.validate(dto250, LessThanOrEquals100.class);
        assertEquals(1, violations250G100.size());
        Set<ConstraintViolation<NonRepeatableDto>> violations250G200 = validator.validate(dto250, LessThanOrEquals200.class);
        assertEquals(1, violations250G200.size());
    }

    @Test
    void repeatableTest() {
        RepeatableDto dto100 = new RepeatableDto(100);
        RepeatableDto dto150 = new RepeatableDto(150);
        RepeatableDto dto200 = new RepeatableDto(200);
        RepeatableDto dto250 = new RepeatableDto(250);

        Set<ConstraintViolation<RepeatableDto>> violations100G100 = validator.validate(dto100, LessThanOrEquals100.class);
        assertEquals(0, violations100G100.size());
        Set<ConstraintViolation<RepeatableDto>> violations100G200 = validator.validate(dto100, LessThanOrEquals200.class);
        assertEquals(0, violations100G200.size());

        Set<ConstraintViolation<RepeatableDto>> violations150G100 = validator.validate(dto150, LessThanOrEquals100.class);
        assertEquals(1, violations150G100.size());
        Set<ConstraintViolation<RepeatableDto>> violations150G200 = validator.validate(dto150, LessThanOrEquals200.class);
        assertEquals(0, violations150G200.size());

        Set<ConstraintViolation<RepeatableDto>> violations200G100 = validator.validate(dto200, LessThanOrEquals100.class);
        assertEquals(1, violations200G100.size());
        Set<ConstraintViolation<RepeatableDto>> violations200G200 = validator.validate(dto200, LessThanOrEquals200.class);
        assertEquals(0, violations200G200.size());

        Set<ConstraintViolation<RepeatableDto>> violations250G100 = validator.validate(dto250, LessThanOrEquals100.class);
        assertEquals(1, violations250G100.size());
        Set<ConstraintViolation<RepeatableDto>> violations250G200 = validator.validate(dto250, LessThanOrEquals200.class);
        assertEquals(1, violations250G200.size());
    }


    static class NonRepeatableDto {
        @Max.List({
                @Max(value = 100, groups = LessThanOrEquals100.class),
                @Max(value = 200, groups = LessThanOrEquals200.class)
        })
        private int value;

        public NonRepeatableDto(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    static class RepeatableDto {
        @Max(value = 100, groups = LessThanOrEquals100.class)
        @Max(value = 200, groups = LessThanOrEquals200.class)
        private int value;

        public RepeatableDto(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    static interface LessThanOrEquals100 {}

    static interface LessThanOrEquals200 {}
}
