/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.ssn.tennis.common.Utils;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Database implements Serializable {
  private static final long serialVersionUID = 1L;

  private static Database instance = null;

  private ArrayList<User> users = new ArrayList<User>();

  public static Database getInstance() {
    if (instance == null) {
      try {
        instance = load();
      } catch (FileNotFoundException e) {
        System.out.println("Database not found, continue...");
        instance = new Database();
        instance.init();
        save(instance);
      }
    }
    return instance;
  }

  private void init() {
    users.add(new User("raz", "raz", "Razvan", "Veina", true));
    users.add(new User("cni", "cni", "Nichifor", "Catalin", true));
  }

  public void addUser(User user) {
    users.add(user);
    save(this);
  }

  public void removeUser(User user) {
    users.remove(user);
    save(instance);

  }

  public User checkLogin(String user, String password) {
    for (User u : users) {
      if (u.hasName(user) && u.hasPassword(password)) {
        return u;
      }
    }
    return null;
  }

  public User getUserByUsername(String user) {
    for (User u : users) {
      if (u.hasName(user)) {
        return u;
      }
    }
    return null;
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public void editUser(String oldUser, String user, String pass, String name, String surname, boolean admin) {
    User tempUser = getUserByUsername(oldUser);
    tempUser.setUser(user);
    tempUser.setName(name);
    tempUser.setSurname(surname);
    tempUser.setPassword(pass);
    tempUser.setAdmin(admin);
    save(instance);
  }

  public void changePassword(String oldUser, String oldPass, String newPass) {
    User tempUser = checkLogin(oldUser, oldPass);
    if (tempUser != null) {
      tempUser.setPassword(Utils.encrypt(newPass));
    }
    save(instance);
  }

  private static void save(Database db) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.ser"));
      oos.writeObject(db);
      oos.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static Database load() throws FileNotFoundException {
    try {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database.ser"));
      @SuppressWarnings("unchecked")
      Database db = (Database) ois.readObject();
      ois.close();

      return db;
    } catch (FileNotFoundException e) {
      throw e;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
