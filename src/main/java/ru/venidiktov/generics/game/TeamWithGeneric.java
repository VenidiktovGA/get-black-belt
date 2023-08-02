package ru.venidiktov.generics.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamWithGeneric<T extends Participant> {
    private String name;
    private List<T> participants = new ArrayList<>();

    public TeamWithGeneric(String name) {
        this.name = name;
    }

    public void addParticipant(T participant) {
        participants.add(participant);
        //ЭТО ПЛОХО->Можно конечно кастить T к Participant что бы вызвать метод из Participant ((Participant)participant).getName()
        // + если указать в типе класса просто T то можно будет создать TeamWithGeneric от любого класса TeamWithGeneric<String>
        //ЭТО ПРАВИЛЬНО->Я использовал wildcard в generic класса <T extends Participant>
        System.out.println(String.format("В команду %s был добавлен участник %s", name, participant.getName()));
    }

    /***
     * Метод позволяет играть с другой командой
     * @param teamWitGeneric - другая команда
     */
    public void playWith(TeamWithGeneric<T> teamWitGeneric) {
        String[] teamNames = {name, teamWitGeneric.getName()};
        Random random = new Random();
        String winnerName = teamNames[random.nextInt(2)];
        System.out.println("!&?$ ПОБЕДИЛА КОМАНДА $?&!");
        System.out.println(winnerName.toUpperCase());
    }

    public String getName() {
        return name;
    }
}
