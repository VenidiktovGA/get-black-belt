package ru.venidiktov.generics.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamWithoutGeneric {
    private String name;
    private List<Participant> participants = new ArrayList<>();

    public TeamWithoutGeneric(String name) {
        this.name = name;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
        System.out.println(String.format("В команду %s был добавлен участник %s", name, participant.getName()));
    }

    /***
     * Метод позволяет играть с другой командой
     * @param teamWithoutGeneric - другая команда
     */
    public void playWith(TeamWithoutGeneric teamWithoutGeneric) {
        String[] teamNames = {name, teamWithoutGeneric.getName()};
        Random random = new Random();
        String winnerName = teamNames[random.nextInt(2)];
        System.out.println("!&?$ ПОБЕДИЛА КОМАНДА $?&!");
        System.out.println(winnerName.toUpperCase());
    }

    public String getName() {
        return name;
    }
}
