package com.example.validation.validator;

import com.example.validation.constraint.Equals;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * 雑な実装なので真似るな危険
 */
public class EqualsValidator implements ConstraintValidator<Equals, Object> {

    private String property1;
    private String property2;

    @Override
    public void initialize(Equals constraintAnnotation) {
        this.property1 = constraintAnnotation.property1();
        this.property2 = constraintAnnotation.property2();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Class<?> objClass = obj.getClass();
        try {
            Field field1 = objClass.getDeclaredField(property1);
            Field field2 = objClass.getDeclaredField(property2);
            field1.setAccessible(true);
            field2.setAccessible(true);
            Object propertyValue1 = field1.get(obj);
            Object propertyValue2 = field2.get(obj);
            if (propertyValue1 == null || propertyValue2 == null) {
                return true;
            }
            return propertyValue1.equals(propertyValue2);
        } catch (Exception e) {
            throw new RuntimeException("リフレクション例外！！", e);
        }
    }
}
