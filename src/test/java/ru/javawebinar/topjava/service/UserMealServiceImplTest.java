package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by 309_newpower on 22.03.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceImplTest {
    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        UserMeal um = new UserMeal(LocalDateTime.now(), "newMeal", 555);
        UserMeal created = service.save(um, USER.getId());
        um.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(um,
                USER_MEAL_6,
                USER_MEAL_5,
                USER_MEAL_4,
                USER_MEAL_3,
                USER_MEAL_2,
                USER_MEAL_1), service.getAll(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        UserMeal um = service.get(USER_MEAL_1.getId(), ADMIN_ID);
//        UserMeal um = service.get(USER_MEAL_1.getId(), USER_ID);
        MATCHER.assertEquals(USER_MEAL_1, um);
    }

    @Test(expected = NotFoundException.class)
    public void testDelete() throws Exception {
        service.delete(1, USER.getId());
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL_6,
                USER_MEAL_5,
                USER_MEAL_4,
                USER_MEAL_3,
                USER_MEAL_2,
                USER_MEAL_1), all);
    }

    @Test
    public void testUpdate() throws Exception {

    }
}