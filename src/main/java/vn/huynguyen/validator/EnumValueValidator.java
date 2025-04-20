package vn.huynguyen.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {
    private List<String> acceptedValues;
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValue enumValue) {
        this.enumClass = enumValue.enumClass();
        acceptedValues = Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof Enum) {
            return true; // Nếu đã là enum, tự động hợp lệ
        }

        if (value instanceof CharSequence) {
            return acceptedValues.contains(value.toString().toUpperCase());
        }

        return false;
    }
}
