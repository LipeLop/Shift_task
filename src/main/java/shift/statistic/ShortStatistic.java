package shift.statistic;

public class ShortStatistic implements Statisticable {

    private int countFloat = 0;
    private int countLong = 0;
    private int countString = 0;

    @Override
    public void addFloat(String value) {
        countFloat++;
    }

    @Override
    public void addLong(String value) {
        countLong++;
    }

    @Override
    public void addString(String value) {
        countString++;
    }

    @Override
    public String showStatistic() {
        return String.format("Количество вещественных чисел: %s%n" +
                "Количество целых чисел: %s%n" +
                "Количество строк: %s%n" +
                "Общее количетсво записанных элементов: %s",
                countFloat, countLong, countString, countFloat + countLong + countString);
    }
}
