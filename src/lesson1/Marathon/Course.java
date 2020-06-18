package lesson1.Marathon;

public class Course {
    Obstacle [] course;
    public Course (Obstacle... a) {
        course = a;
    }
/*
    метод doIt () класса Course
 */
    public void doIt (Team team) {
        for (Competitor a : team.getTeamFourPerson()) {
            for (Obstacle b : course) {
                b.doIt(a);
                if (!a.isOnDistance()) break;
            }
        }
    }
}
