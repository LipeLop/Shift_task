package shift.validators;

import com.beust.jcommander.ParameterException;
import shift.AppArguments;

import java.lang.reflect.Field;


import com.beust.jcommander.Parameter;

public class ConflictValidator {

    public static void validateConflictsOfArguments(AppArguments arguments) throws ParameterException {
        try {
            Field[] fields = AppArguments.class.getDeclaredFields();
            for (Field field : fields) {
                MutuallyExclusive annotation = field.getAnnotation(MutuallyExclusive.class);
                if (annotation == null) {
                    continue;
                }

                field.setAccessible(true);
                Object value = field.get(arguments);
                if (value instanceof Boolean && (Boolean) value) {
                    for (String conflictFieldName : annotation.value()) {
                        Field conflictField = AppArguments.class.getDeclaredField(conflictFieldName);
                        conflictField.setAccessible(true);
                        Object conflictValue = conflictField.get(arguments);
                        if (conflictValue instanceof Boolean && (Boolean) conflictValue) {
                            String originalParamName = getDisplayName(field);
                            String conflictingParamName = getDisplayName(conflictField);
                            throw new ParameterException(
                                    String.format("Параметры %s и %s нельзя использовать вместе", originalParamName, conflictingParamName)
                            );
                        }
                    }
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Ошибка конфигурации валидатора: " + e.getMessage(), e);
        }
    }

    private static String getDisplayName(Field field) {
        Parameter paramAnnotation = field.getAnnotation(Parameter.class);
        if (paramAnnotation != null && paramAnnotation.names().length > 0) {
            return paramAnnotation.names()[0];
        }
        return field.getName();
    }
}
