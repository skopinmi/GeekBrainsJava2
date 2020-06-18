package lesson1.Marathon;

public class Team {
    private String nameTeam;
    private Competitor [] teamFourPerson;
/*
    Конструктор только для 4-х участников
 */
    public Team (String nameTeam, Competitor a, Competitor b, Competitor c, Competitor d) {
        this.nameTeam = nameTeam;
        teamFourPerson = new Competitor[]{a, b, c, d};
    }
/*
    Вывод только прошедших дистанцию
 */
    public void infoWhoWin() {
        System.out.println("Имя команды " + nameTeam);
        System.out.println("Прошли дистанцию: ");
        for (Competitor a : teamFourPerson) {
            if (a.isOnDistance()) {
                a.info();
            }
        }
    }
/*
    вывод результатов всех членов команды
 */
    public void showResult () {
        System.out.println("Имя команды: " + nameTeam);
        for (Competitor a: teamFourPerson) {
            a.info();
        }
    }

    public Competitor[] getTeamFourPerson() {
        return teamFourPerson;
    }


/*
      что бы нельзя было менять данные в массиве объекта передавать копию как-то так?
 */
//    public Competitor[] getTeamFourPerson() {
//        Competitor [] copy = new Competitor[teamFourPerson.length];
//        for (int i = 0; i < teamFourPerson.length; i++) {
//            copy[i] = teamFourPerson[i];
//        }
//        return copy;
//    }
}
