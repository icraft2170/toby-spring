package me.hero.user.dao;

public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new SimpleConnectionMaker();
        return new UserDao(connectionMaker);
    }
}
