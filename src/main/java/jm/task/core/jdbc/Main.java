package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Donatello", "Betto Bardi", (byte) 20);
        userDao.saveUser("Michelangelo", "Buonarroti Simoni", (byte) 25);
        userDao.saveUser("Leonardo", "Piero da Vinci", (byte) 31);
        userDao.saveUser("Raphael", "Sanzio", (byte) 38);

        System.out.println(userDao.getAllUsers());
//        userDao.removeUserById(2);

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }

}

