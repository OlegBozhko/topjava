package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import java.time.LocalDateTime;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);
    public static final UserMeal USER_MEAL_1 = new UserMeal(START_SEQ+2, LocalDateTime.parse("2015-05-30T10:00:00"), "Завтрак", 500);
    public static final UserMeal USER_MEAL_2 = new UserMeal(START_SEQ+3, LocalDateTime.parse("2015-05-30T13:00:00"), "Обед", 1000);
    public static final UserMeal USER_MEAL_3 = new UserMeal(START_SEQ+4, LocalDateTime.parse("2015-05-30T20:00:00"), "Ужин", 500);
    public static final UserMeal USER_MEAL_4 = new UserMeal(START_SEQ+5, LocalDateTime.parse("2015-05-31T10:00:00"), "Завтрак", 500);
    public static final UserMeal USER_MEAL_5 = new UserMeal(START_SEQ+6, LocalDateTime.parse("2015-05-31T13:00:00"), "Обед", 1000);
    public static final UserMeal USER_MEAL_6 = new UserMeal(START_SEQ+7, LocalDateTime.parse("2015-05-31T20:00:00"), "Ужин", 510);
    public static final UserMeal ADMIN_MEAL_1 = new UserMeal(START_SEQ+8, LocalDateTime.parse("2015-06-01T14:00:00"), "Админ ланч", 510);
    public static final UserMeal ADMIN_MEAL_2 = new UserMeal(START_SEQ+9, LocalDateTime.parse("2015-06-04T21:00:00"), "Админ ланч", 1500);
}
