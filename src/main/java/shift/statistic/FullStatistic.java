package shift.statistic;

public class FullStatistic implements Statisticable {
    private int countFloat = 0;
    private int countLong = 0;
    private int countString = 0;

    float min_Float;
    float max_Float;
    float sum_Float = 0;
    float average_Float = 0;

    long min_Long;
    long max_Long;
    long sum_Long = 0;
    long average_Long = 0;

    int min_string;
    int max_string;

    @Override
    public void addFloat(String value) {
        float f = Float.parseFloat(value);
        countFloat++;
        min_Float = min_Float == 0 ? f : Math.min(min_Float, f);
        max_Float = max_Float == 0 ? f : Math.max(max_Float, f);
        sum_Float += f;
        average_Float = (sum_Float / countFloat);
    }

    @Override
    public void addLong(String value) {
        long l = Long.parseLong(value);
        countLong++;
        min_Long = min_Long == 0 ? l : Math.min(min_Long, l);
        max_Long = max_Long == 0 ? l : Math.max(max_Long, l);
        sum_Long += l;
        average_Long = (sum_Long / countLong);
    }

    @Override
    public void addString(String value) {
        countString++;
        min_string = min_string == 0 ? value.length() : Math.min(min_string, value.length());
        max_string = max_string == 0 ? value.length() : Math.max(max_string, value.length());
    }

    @Override
    public String showStatistic() {
        return String.format("Количество вещественных чисел: %s%n" +
                        "Количество целых чисел: %s%n" +
                        "Количество строк: %s%n" +
                        "Общее количество записанных элементов: %s%n" +
                        "Минимальное вещественное число: %s%n" +
                        "Максимальное вещественное число: %s%n" +
                        "Сумма вещественных чисел: %s%n" +
                        "Среднее от вещественных чисел: %s%n" +
                        "Минимальное целое число: %s%n" +
                        "Максимальное целое число: %s%n" +
                        "Сумма целых чисел: %s%n" +
                        "Среднее от целых чисел: %s%n" +
                        "Минимальная длина строки: %s%n" +
                        "Маскимальная длина строки: %s%n",
                countFloat, countLong, countString, countFloat + countLong + countString, min_Float, max_Float,
                sum_Float, average_Float, min_Long, max_Long, sum_Long, average_Long, min_string, max_string);
    }
}
