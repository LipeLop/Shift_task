package shift.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class FileExistsValidator implements IValueValidator<List<String>> {


    @Override
    public void validate(String name, List<String> values) throws ParameterException {
        for (String value : values) {
            File file = new File(value);
            if (!file.exists() || !file.isFile()) {
                throw new ParameterException(String.format(
                        "Файл %s не найден %n" +
                                "Текущая рабочая директория: %s%n" +
                                "Полный путь: %s",
                        value, System.getProperty("user.dir"), Paths.get(value).toAbsolutePath()
                ));
            }
        }
    }
}
