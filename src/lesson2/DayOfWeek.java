package lesson2;

public enum DayOfWeek {

    MONDAY("Понедельник", 8),
    TUESDAY("Вторник", 8),
    WEDNESDAY("Среда", 8),
    THURSDAY("Четверг", 8),
    FRIDAY("Пятница", 8),
    SATURDAY("Суббота", 0),
    SUNDAY("Восскресенье", 0);
/*
    оставил гибкий вариант рабочих кол-во часов в сутки может остличаться, как и количество рабочих дней в неделе
 */
    private String rusName;
    private int workHours;

    DayOfWeek(String rusName, int workHours) {
        this.rusName = rusName;
        this.workHours = workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public String getRusName() {
        return rusName;
    }

    public int getWorkHours() {
        return workHours;
    }
}

class MainDayOfWeekEnum {
    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.WEDNESDAY));
    }
/*
       5 дневка
 */
    static String getWorkingHours (DayOfWeek dayOfWeek) {
        int result = 0;
        if (dayOfWeek.getWorkHours() == 0) return  "Это нерабочий день!";
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.ordinal() == 5||day.ordinal() == 6) break;
            if (day.ordinal() >= dayOfWeek.ordinal()) {
                result += day.getWorkHours();
            }
        }
        return result + "";
    }
}