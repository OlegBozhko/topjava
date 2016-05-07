package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.MealTo;

/**
 * Created by 309_newpower on 06.05.2016.
 */
public class MealUtil {
    public static UserMeal createFromTo(MealTo newMeal) {
        return new UserMeal(newMeal.getDateTime(), newMeal.getDescription(), newMeal.getCalories());
    }
}
