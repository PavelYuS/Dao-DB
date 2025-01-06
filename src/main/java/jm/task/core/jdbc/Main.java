package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userDaoHibernate = new UserServiceImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Donatello", "Betto Bardi", (byte) 20);
        userDaoHibernate.saveUser("Michelangelo", "Buonarroti Simoni", (byte) 25);
        userDaoHibernate.saveUser("Leonardo", "Piero da Vinci", (byte) 31);
        userDaoHibernate.saveUser("Raphael", "Sanzio", (byte) 38);

        System.out.println(userDaoHibernate.getAllUsers());

        userDaoHibernate.removeUserById(2);

        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}

