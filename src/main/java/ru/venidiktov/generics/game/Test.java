package ru.venidiktov.generics.game;

/***
 * Проверяем как работает наша игра "Что где когда" без generics и с generics
 */
public class Test {
    public static void main(String[] args) {
        Schoolboy schoolboy1 = new Schoolboy("Ivan", 13);
        Schoolboy schoolboy2 = new Schoolboy("Anna", 15);
        Schoolboy schoolboy3 = new Schoolboy("Maksim", 13);
        Schoolboy schoolboy4 = new Schoolboy("Olya", 15);
        Student student1 = new Student("Nikolay", 20);
        Student student2 = new Student("Maria", 21);
        Employee employee1 = new Employee("Oleg", 33);
        Employee employee2 = new Employee("Alla", 31);

        /**
         * Без generics
         */
        TeamWithoutGeneric schoolboyTeamWithoutGeneric = new TeamWithoutGeneric("Dragons");
        schoolboyTeamWithoutGeneric.addParticipant(schoolboy1);
        schoolboyTeamWithoutGeneric.addParticipant(schoolboy2);
        //ПРОБЛЕМА1->мы в команду школьников можем добавить студента или работника!
        schoolboyTeamWithoutGeneric.addParticipant(student1);

        TeamWithoutGeneric studentTeamWithoutGeneric = new TeamWithoutGeneric("Go");
        studentTeamWithoutGeneric.addParticipant(student1);
        studentTeamWithoutGeneric.addParticipant(employee1);

        //ПРОБЛЕМА2-> команды могут играть в разных лигах (Школьники со студентами)
        schoolboyTeamWithoutGeneric.playWith(studentTeamWithoutGeneric);

        /**
         * С generics
         */
        TeamWithGeneric<Schoolboy> schoolboyTeamWithGeneric = new TeamWithGeneric<>("Gold dragons");
        schoolboyTeamWithGeneric.addParticipant(schoolboy1);
        schoolboyTeamWithGeneric.addParticipant(schoolboy2);
        //РЕШЕНИЕ ПРОБЛЕМЫ1->Тут можно добавлять объекты только того типа который был объявлен при объявлении класса
//        schoolboyTeamWithGeneric.addParticipant(student1);

        TeamWithGeneric<Student> studentTeamWithGeneric = new TeamWithGeneric<>("S.T.A.L.K.E.R");
        studentTeamWithGeneric.addParticipant(student1);
        studentTeamWithGeneric.addParticipant(student2);

        TeamWithGeneric<Schoolboy> schoolboyTeamWithGeneric2 = new TeamWithGeneric<>("Old man");
        schoolboyTeamWithGeneric.addParticipant(schoolboy3);
        schoolboyTeamWithGeneric.addParticipant(schoolboy4);

        schoolboyTeamWithGeneric.playWith(schoolboyTeamWithGeneric2);
        //РЕШЕНИЕ ПРОБЛЕМЫ2->Тут можно передавать объекты класса TeamWithGeneric только с тем типом с которым объявлен schoolboyTeamWithGeneric
//        schoolboyTeamWithGeneric.playWith(studentTeamWithGeneric);

    }
}
