package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.USER_MEALS;

/**
 * Created by 309_newpower on 20.04.2016.
 */
public class UserMealControllerTest extends AbstractControllerTest {
    @Test
    public void testMealList() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList", hasSize(6)))
                .andExpect(model().attribute("mealList", hasItem(
                        allOf(
                                hasProperty("id", is(MEAL1_ID)),
                                hasProperty("description", is(USER_MEALS.stream().filter(userMeal -> userMeal.getId()==MEAL1_ID).findFirst().get().getDescription())),
                                hasProperty("calories", is(USER_MEALS.stream().filter(userMeal -> userMeal.getId()==MEAL1_ID).findFirst().get().getCalories()))
                ))));
    }

}