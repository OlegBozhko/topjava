package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by 309_newpower on 06.04.2016.
 */
@ActiveProfiles(Profiles.JDBC)
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}
