package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by 309_newpower on 06.05.2016.
 */
public class MealTo implements Serializable {
    protected int id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime localDateTime;

    @NotEmpty
    @Size(min = 5, max = 64)
    protected String description;

    @Range(min = 100, max = 5000)
    @NotNull(message = " must not be empty")
    protected int calories;

    public MealTo(){
    }

    public MealTo(int id,
                  LocalDateTime localDateTime,
                  String description,
                  int calories) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = (id == null ? 0 : id);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
