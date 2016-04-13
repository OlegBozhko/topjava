package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
@RequestMapping(value = "/meals")
public class UserMealRestController extends AbstractUserMealController {
    @RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", super.getAll());
        return "mealList";
    }

    @RequestMapping(params = "action=delete", method = RequestMethod.GET)
    public String delete(Integer id) {
        super.delete(id);
        return "redirect:meals";
    }

    @RequestMapping(params = "action=create", method = RequestMethod.GET)
    public String create(Model model) {
        final UserMeal meal = new UserMeal(LocalDateTime.now(), "", 1000);
        model.addAttribute("meal", meal);
        LOG.info("create {} for User {}", meal, LoggedUser.id());
        return "mealEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUpdatePost(
        @RequestParam(defaultValue = "-1") Integer id,
        String dateTime, String description, Integer calories) {

        final UserMeal meal = new UserMeal(LocalDateTime.parse(dateTime), description, calories);

        if (id.equals(-1))
            super.create(meal);
        else
            super.update(meal, id);
        return "redirect:meals";
    }

    @RequestMapping(params = "action=update", method = RequestMethod.GET)
    public String update(Integer id, Model model) {
        final UserMeal meal = super.get(id);
        LOG.info("update {} for User {}", meal, LoggedUser.id());
        model.addAttribute("meal", meal);
        return "mealEdit";
    }

    @RequestMapping(params = "action=filter", method = RequestMethod.POST)
    public String getBetween(String startDate, String startTime, String endDate, String endTime, Model model) {

        LocalDate startDateParse = TimeUtil.parseLocalDate(startDate);
        LocalDate endDateParse = TimeUtil.parseLocalDate(endDate);
        LocalTime startTimeParse = TimeUtil.parseLocalTime(startTime);
        LocalTime endTimeParse = TimeUtil.parseLocalTime(endTime);

        model.addAttribute("mealList", super.getBetween(startDateParse, startTimeParse, endDateParse, endTimeParse));
        return "mealList";
    }

//    @Override
//    public UserMeal get(int id) {
//        return super.get(id);
//    }
//
//    @Override
//    public void delete(int id) {
//        super.delete(id);
//    }
//
//    @Override
//    public List<UserMealWithExceed> getAll() {
//        return super.getAll();
//    }
//
//    @Override
//    public void update(UserMeal meal, int id) {
//        super.update(meal, id);
//    }
//
//    @Override
//    public UserMeal create(UserMeal meal) {
//        return super.create(meal);
//    }
//
//    @Override
//    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
//        return super.getBetween(startDate, startTime, endDate, endTime);
//    }
}