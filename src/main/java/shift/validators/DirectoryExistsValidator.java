package shift.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.nio.file.Paths;

public class DirectoryExistsValidator implements IValueValidator<String> {
    @Override
    public void validate(String name, String value) throws ParameterException {
        File dir = new File(value);
        String currentDir = System.getProperty("user.dir");

        if (!dir.exists()) {
            throw new ParameterException(String.format(
                    "Директория '%s' не существует (параметр: %s)%n" +
                            "Текущая рабочая директория: %s%n" +
                            "Полный путь: %s",
                    value, name, currentDir, Paths.get(value).toAbsolutePath()
            ));
        }

        if (!dir.isDirectory()) {
            throw new ParameterException(String.format(
                    "Путь '%s' не является директорией (параметр: %s)%n" +
                            "Текущая рабочая директория: %s",
                    value, name, currentDir
            ));
        }
    }
}