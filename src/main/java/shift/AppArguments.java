package shift;

import com.beust.jcommander.Parameter;
import shift.validators.DirectoryExistsValidator;
import shift.validators.FileExistsValidator;
import shift.validators.MutuallyExclusive;

import java.util.List;

public class AppArguments {

    @Parameter(names = {"--files"}, description = "Список файлов", validateValueWith = FileExistsValidator.class, required = true
    , variableArity = true)
    public List<String> files;

    @Parameter(names = {"-o"}, description = "Путь для хранения результатов", validateValueWith = DirectoryExistsValidator.class)
    public String output_way = "./";

    @Parameter(names = {"-p"}, description = "Префикс для названия файлов")
    public String prefix = "";

    @Parameter(names = {"-a"}, description = "Режим добавления в существующие файлы")
    public boolean update_current_files;

    @Parameter(names = "-s", description = "Краткая статистика")
    @MutuallyExclusive({"fullStats"})
    public boolean shortStats;

    @Parameter(names = "-f", description = "Полная статистика")
    @MutuallyExclusive({"shortStats"})
    public boolean fullStats;

}
