package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public List<UserMealWithExceed> getAll() {
        LOG.info("getAll");
        return UserMealsUtil.getWithExceeded(service.getAll().stream()
                        .filter(um -> um.getUserId() == LoggedUser.id())
                        .collect(Collectors.toList()),
                LoggedUser.getCaloriesPerDay());
    }

//    public List<UserMealWithExceed> getByTime(List<UserMeal> userMeal, LocalTime startTime, LocalTime endTime) throws NotFoundException {
//        LOG.info("getByTime startTime " + startTime + " endDate " + endTime);
//
//        return ExceptionUtil.check(UserMealsUtil.getWithExceeded(userMeal.stream()
//                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime) && um.getUserId() == LoggedUser.id())
//                .collect(Collectors.toList()), LoggedUser.getCaloriesPerDay()), "getByTime startTime " + startTime + " endTime " + endTime);
//    }
//
//    public List<UserMealWithExceed> getByDate(LocalDate startDate, LocalDate endDate) throws NotFoundException {
//        return ExceptionUtil.check(UserMealsUtil.getWithExceeded(service.getByDate(startDate, endDate).stream()
//                .filter(um -> um.getUserId() == LoggedUser.id())
//                .collect(Collectors.toList()),
//                LoggedUser.getCaloriesPerDay()),
//                "getByDate startDate" + startDate + " endDate" + endDate);
//    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) throws NotFoundException
    {
        if (startDate == null) startDate = LocalDate.MIN;
        if (endDate == null) endDate = LocalDate.MAX;
        if (startTime == null) startTime = LocalTime.MIN;
        if (endTime == null) endTime = LocalTime.MAX;

        List<UserMeal> usersByDate = service.getByDate(startDate, endDate).stream().filter(um -> um.getUserId() == LoggedUser.id()).collect(Collectors.toList());

        final LocalTime finalStartTime = startTime;
        final LocalTime finalEndTime = endTime;

        return ExceptionUtil.check(UserMealsUtil.getWithExceeded(usersByDate, LoggedUser.getCaloriesPerDay()).stream()
            .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), finalStartTime, finalEndTime)).collect(Collectors.toList()),
                "getByDate startDate" + startDate + " endDate" + endDate);
    }

    public UserMealWithExceed get(int id) throws NotFoundException {
        LOG.info("get " + id);
        return ExceptionUtil.check(getAll().get(id), id);
    }

    public UserMeal create(UserMeal userMeal) {
        LOG.info("create " + userMeal);
        return service.save(userMeal);
    }

    public void update(UserMeal userMeal) throws NotFoundException {
        ExceptionUtil.check(service.getAll().get(userMeal.getId()).getUserId() == LoggedUser.id(), userMeal.toString());
        LOG.info("update " + userMeal);
        service.update(userMeal);
    }
}
