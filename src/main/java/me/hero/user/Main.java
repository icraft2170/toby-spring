package me.hero.user;

import me.hero.user.dao.DaoFactory;
import me.hero.user.dao.SimpleConnectionMaker;
import me.hero.user.dao.UserDao;
import me.hero.user.domain.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DaoFactory daoFactory = new DaoFactory();
        UserDao dao = daoFactory.userDao();
        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User findUser = dao.get(user.getId());
        System.out.println(findUser.getName());
    }
}
