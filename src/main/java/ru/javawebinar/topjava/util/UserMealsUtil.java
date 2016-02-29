package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
//        list.stream().forEach(System.out::println);
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList,
                                                                         LocalTime startTime,
                                                                         LocalTime endTime,
                                                                         int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> calories = new HashMap<>();
        for (UserMeal userMeal : mealList){
            LocalDate date = userMeal.getDateTime().toLocalDate();
            if (calories.containsKey(date))calories.put(date, calories.get(date)+userMeal.getCalories());
            else calories.put(date, userMeal.getCalories());
        }

//        List<UserMealWithExceed> returnList = new ArrayList<>();
//        for (UserMeal userMeal : mealList){
//            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
//            {
//                if (calories.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay)
//                    returnList.add(new UserMealWithExceed(userMeal.getDateTime(),
//                            userMeal.getDescription(), userMeal.getCalories(), true));
//                else
//                    returnList.add(new UserMealWithExceed(userMeal.getDateTime(),
//                        userMeal.getDescription(), userMeal.getCalories(), false));
//            }
//        }
        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(),startTime,endTime))
                .map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        (calories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)))
                .collect(Collectors.toList());
    }
}
