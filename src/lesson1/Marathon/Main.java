package lesson1.Marathon;


public class Main {
    public static void main(String[] args) {

        Team team = new Team("Winners", new Human("Боб"), new Human("Лиз"),
                new Cat("Барсик"), new Dog("Бобик"));
        Course course = new Course(new Cross(80), new Water(5), new Wall(1), new Cross(120));
        course.doIt(team);
        team.showResult();
        team.infoWhoWin();
    }
}