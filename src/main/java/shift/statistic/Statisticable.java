package shift.statistic;

public interface Statisticable {

    void addFloat(String value);
    void addLong(String value);
    void addString(String value);

    String showStatistic();
}
