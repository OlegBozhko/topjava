package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    private final int id;

    private static volatile int count = 0;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.id = count++;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public int getId() {
        return id;
    }
}
