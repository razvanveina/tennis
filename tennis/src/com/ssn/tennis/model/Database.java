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
import java.util.Date;

import com.ssn.tennis.common.Utils;
import com.ssn.tennis.model.enums.TournamentType;
import com.ssn.tennis.model.format.TournamentFormats;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Database implements Serializable {
  private static final long serialVersionUID = 1L;

  private static Database instance = null;

  private ArrayList<User> users = new ArrayList<User>();

  private ArrayList<Tournament> tournaments = new ArrayList<>();

  private transient TournamentFormats tf = new TournamentFormats();

  public static Database getInstance() {
    if (instance == null) {
      try {
        instance = load();
        if (instance.tournaments == null) {
          instance.tournaments = new ArrayList<>();
        }
        instance.tf = new TournamentFormats();
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
    users.add(new User("cni", "cni", "Catalin", "Nichifor", true));
    users.add(new User("dst", "dst", "Dana", "Stan", true));
    users.add(new User("cmt", "cmt", "Cristi", "Matei", false));

    users.add(new User("cpl", "cpl", "Cosmin", "Plugar", false));
    users.add(new User("cba", "cba", "Cristi", "Baciu", false));
    users.add(new User("asa", "asa", "Andreea", "Sas", false));
    users.add(new User("apa", "apa", "Andreea", "Pantea", false));

    users.add(new User("ave", "ave", "Andor", "Vetesi", false));
    users.add(new User("rva", "rva", "Robert", "Valea", false));
    users.add(new User("ema", "ema", "Elena", "Malaescu", false));
    users.add(new User("rvo", "rvo", "Razvan", "Voicu", false));

    users.add(new User("cvo", "cvo", "Cristina", "Volinteriu", false));
    users.add(new User("cco", "cco", "Marius", "Cosma", false));
    users.add(new User("acn", "acn", "Andrei", "Contoman", false));
    users.add(new User("sbr", "sbr", "Sorin", "Brazdau", false));
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

  public Tournament getTournamentByName(String name) {
    for (Tournament tour : tournaments) {
      if (tour.getName().equals(name)) {
        return tour;
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

  public void addTournament(String name, Date date, TournamentType type, String tournamentFormat) {
    tournaments.add(new Tournament(name, date, type, tf.getTournamentFormatByName(tournamentFormat)));
    save(this);
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

  public ArrayList<Tournament> getTournaments() {
    return tournaments;
  }

  public boolean checkDuplicateTournament(String tournamentName, Date date) {
    for (Tournament tournament : tournaments) {
      if (tournament.getName().equals(tournamentName) || tournament.getStartDate().equals(date)) {
        return true;
      }
    }
    return false;
  }

  public int getUserRatingByName(String name) {
    int won = 0;
    int lost = 0;

    for (Tournament t : tournaments) {
      won += t.getMatchesWonByUserName(name);
      lost += t.getMatchesLostByUserName(name);
    }

    return (won + lost != 0) ? won * 1000 / (won + lost) : 0;
  }

  public void startTournament(String name) {
    Tournament tournament = getTournamentByName(name);
    tournament.start();
  }

  public void addParticipantsToTournament(Tournament t, ArrayList<User> participants) {
    t.setParticipants(participants);
    save(this);
  }
}
