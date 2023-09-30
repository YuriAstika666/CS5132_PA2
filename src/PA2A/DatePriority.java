package PA2A;

public class DatePriority implements Comparable<DatePriority> {
    private int year;
    private int month;
    private int day;

    public DatePriority(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear(){return year;}
    public int getMonth(){return month;}
    public int getDay(){return day;}

    @Override
    public int compareTo(DatePriority date){
        if (date.year == year){
            if (date.month == month){
                return Integer.compare(day,date.day);
            } return Integer.compare(month, date.month);
        } return Integer.compare(year, date.year);
    }
}
