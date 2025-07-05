package shift.logic;

import shift.AppArguments;
import shift.statistic.FullStatistic;
import shift.statistic.ShortStatistic;
import shift.statistic.Statisticable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    private final AppArguments appArguments;
    private final Statisticable statisctic;
    private List<String> floats;
    private List<String> strings;
    private List<String> longs;

    public Logic(AppArguments appArguments) {
        this.appArguments = appArguments;
        if (appArguments.shortStats) {
            statisctic = new ShortStatistic();
        } else if (appArguments.fullStats)  {
            statisctic = new FullStatistic();
        }
        else  {
            statisctic = null;
        }

    }


    public void startLogic() throws IOException {
        for (String file : appArguments.files) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            while (br.ready()) {
                String line = br.readLine().trim();
                if (isLong(line)) {
                    longs = longs == null ? new ArrayList<>() : longs;
                    longs.add(line);
                    if (statisctic != null) statisctic.addLong(line);
                } else if (isFloat(line)) {
                    floats = floats == null ? new ArrayList<>() : floats;
                    floats.add(line);
                    if (statisctic!= null) statisctic.addFloat(line);
                } else {
                    strings = strings == null ? new ArrayList<>() : strings;
                    strings.add(line);
                    if (statisctic!= null) statisctic.addString(line);
                }
            }
            br.close();
        }
        save_result(floats, appArguments.output_way + appArguments.prefix + "floats.txt", appArguments.update_current_files);
        save_result(strings, appArguments.output_way + appArguments.prefix + "strings.txt", appArguments.update_current_files);
        save_result(longs, appArguments.output_way + appArguments.prefix + "integers.txt", appArguments.update_current_files);
        if (statisctic != null) System.out.println(statisctic.showStatistic());

    }

    private boolean isFloat(String line) {
        try {
            Float.parseFloat(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLong(String line) {
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void save_result(List<String> list, String way, boolean append) throws IOException {
        if (list == null) {
            return;
        }
        File file = new File(way);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), StandardCharsets.UTF_8));
        for (String obj : list) {
            writer.write(obj);
            writer.newLine();
        }
        System.out.println("Данные записаны в " + way);
        writer.close();
    }
}
