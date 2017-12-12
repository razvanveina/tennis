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

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Database implements Serializable {
  private static final long serialVersionUID = 1L;
  private static Database instance = new Database();
  private ArrayList<User> users = new ArrayList<User>();

  private Database() {
    try {
      instance = load();
    } catch (FileNotFoundException e) {
      System.out.println("Database not found, continue...");
      users.add(new User("raz", "raz", true));
      users.add(new User("cni", "cni", true));
      try {
        save();
      } catch (IOException e1) {
        throw new RuntimeException(e);
      }
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

  }

  public static Database getInstance() {
    return instance;
  }

  private Database load() throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database.ser"));
    @SuppressWarnings("unchecked")
    Database db = (Database) ois.readObject();
    ois.close();

    return db;

  }

  public User checkLogin(String user, String password) {
    for (User u : users) {
      if (u.hasNameAndPwd(user, password)) {
        return u;
      }
    }
    return null;
  }

  private void save() throws FileNotFoundException, IOException {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.ser"));
    oos.writeObject(instance);
    oos.close();
  }
}
