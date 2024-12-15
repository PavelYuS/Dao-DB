package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDaoHiber = new UserDaoHibernateImpl();

//        userDao.createUsersTable();

        userDaoHiber.createUsersTable();


//        userDao.saveUser("Donatello", "Betto Bardi", (byte) 20);
//        userDao.saveUser("Michelangelo", "Buonarroti Simoni", (byte) 25);
//        userDao.saveUser("Leonardo", "Piero da Vinci", (byte) 31);
//        userDao.saveUser("Raphael", "Sanzio", (byte) 38);


        userDaoHiber.saveUser("Donatello", "Betto Bardi", (byte) 20);
        userDaoHiber.saveUser("Michelangelo", "Buonarroti Simoni", (byte) 25);
        userDaoHiber.saveUser("Leonardo", "Piero da Vinci", (byte) 31);
        userDaoHiber.saveUser("Raphael", "Sanzio", (byte) 38);

//        System.out.println(userDao.getAllUsers());

        System.out.println(userDaoHiber.getAllUsers());

//        userDao.removeUserById(2);

        userDaoHiber.removeUserById(2);

//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();

        userDaoHiber.cleanUsersTable();
        userDaoHiber.dropUsersTable();
    }

}

