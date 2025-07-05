package shift;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import shift.logic.Logic;
import shift.validators.ConflictValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Если видите непонятные символы в файлах результатов" +
                " - используйте кодировку UTF-8 в исходных файлах и все будет окей");
        AppArguments arguments = new AppArguments();
        JCommander jc = JCommander.newBuilder()
                .addObject(arguments)
                .build();

        try {
            jc.parse(args);
            ConflictValidator.validateConflictsOfArguments(arguments);
            Logic logic = new Logic(arguments);
            logic.startLogic();

        } catch (ParameterException e) {
            if (e.getMessage().startsWith("Was passed main parameter")) {
                String wrongArg = e.getMessage().split("'")[1];
                System.err.println("Ошибка: Обнаружен лишний или нераспознанный аргумент: '" + wrongArg + "'");
                System.err.println("Пожалуйста, убедитесь, что все аргументы указаны верно.");
            } else {

                System.err.println("Ошибка в аргументах: " + e.getMessage());
            }
            System.out.println("\nОписание:");
            jc.usage();

        } catch (Exception e) {
            System.err.println("Ошибка выполнения: " + e.getMessage());
        }
    }
}