package com.rehneo.domain;

import java.util.List;

public class People {
    private boolean dead = false;
    private final List<Room> availableRooms;
    private Room currentRoom;
    private int currentRoomIndex = 0;

    public People(List<Room> availableRooms) {
        if (availableRooms == null || availableRooms.isEmpty()) {
            throw new IllegalArgumentException("The list of available rooms is null or empty");
        }
        currentRoom = availableRooms.get(currentRoomIndex);
        currentRoom.setPeople(this);
        this.availableRooms = availableRooms;
    }

    protected void seeDanger() {
        currentRoomIndex++;
        if (availableRooms.size() <= currentRoomIndex) {
            dead = true;
        } else {
            currentRoom.setPeople(null);
            currentRoom = availableRooms.get(currentRoomIndex);
            currentRoom.setPeople(this);
        }
    }

    public boolean isDead() {
        return dead;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
