package me.hero.user.dao;

import me.hero.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1 = new User("gyumme", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");

        dao.deleteAll();
        assertEquals(0, dao.getCount());

        dao.add(user1);
        dao.add(user2);
        assertEquals(2, dao.getCount());
        User findUser1 = dao.get(user1.getId());
        assertEquals(user1.getName(), findUser1.getName());
        assertEquals(user1.getPassword(), findUser1.getPassword());

        User findUser2 = dao.get(user2.getId());
        assertEquals(user2.getName(), findUser2.getName());
        assertEquals(user2.getPassword(), findUser2.getPassword());
    }

    @Test
    void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1 = new User("gyumme", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");
        User user3 = new User("bumjin", "박범진", "springno3");

        dao.deleteAll();
        assertEquals(0, dao.getCount());

        dao.add(user1);
        assertEquals(1, dao.getCount());

        dao.add(user2);
        assertEquals(2, dao.getCount());

        dao.add(user3);
        assertEquals(3, dao.getCount());

    }
}