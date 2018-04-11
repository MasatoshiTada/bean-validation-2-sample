package com.example.validation.typeargument;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TypeArgumentTest {

    static Validator validator;

    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void test() {
        Sample sample = new Sample();
        sample.addToOptionalString("aa");
        sample.addToList(null);
        sample.addToOptionalInt(20);

        Set<ConstraintViolation<Sample>> violations = validator.validate(sample);
        assertEquals(2, violations.size());
    }

    @Test
    public void test2() {
        Sample sample = new Sample();
        sample.addToList(null);
        sample.addToList(null);

        Set<ConstraintViolation<Sample>> violations = validator.validate(sample);
        assertEquals(2, violations.size());
    }

    static class Sample {

        private Optional<@Size(min = 3) String> optionalString;

        private List<@NotNull String> list = new ArrayList<>();

        @Max(10)
        private OptionalInt optionalInt;

        void addToOptionalString(String str) {
            optionalString = Optional.ofNullable(str);
        }

        void addToList(String str) {
            list.add(str);
        }

        void addToOptionalInt(int value) {
            optionalInt = OptionalInt.of(value);
        }

        public Optional<String> getOptionalString() {
            return optionalString;
        }

        public List<String> getList() {
            return list;
        }

        public OptionalInt getOptionalInt() {
            return optionalInt;
        }
    }
}
