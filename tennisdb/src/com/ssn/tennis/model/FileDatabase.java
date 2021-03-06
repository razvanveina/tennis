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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.ssn.tennis.common.Utils;
import com.ssn.tennis.model.enums.TournamentType;
import com.ssn.tennis.model.format.TournamentFormats;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class FileDatabase implements Serializable, Database {
  private static final long serialVersionUID = 1L;

  private static FileDatabase instance = null;

  private ArrayList<User> users = new ArrayList<User>();
  private ArrayList<Team> teams = new ArrayList<Team>();

  private ArrayList<Tournament> tournaments = new ArrayList<>();

  public static FileDatabase getInstance() {
    if (instance == null) {
      try {
        instance = load();
        if (instance.tournaments == null) {
          instance.tournaments = new ArrayList<>();
        }
      } catch (FileNotFoundException e) {
        System.out.println("FileDatabase not found, continue...");
        instance = new FileDatabase();
        instance.init();
        save(instance);
      }
    }
    return instance;
  }

  @Override
  public void init() {
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
    if (pass != null && !pass.equals("")) {
      tempUser.setPassword(Utils.encrypt(pass));
    }
    tempUser.setAdmin(admin);
    save(instance);
  }

  public boolean changePassword(String oldUser, String oldPass, String newPass) {
    User tempUser = checkLogin(oldUser, oldPass);
    if (tempUser != null) {
      tempUser.setPassword(Utils.encrypt(newPass));
      save(instance);
      return true;
    }
    return false;
  }

  public void addTournament(String name, Date date, TournamentType type, String tournamentFormat) {
    tournaments.add(new Tournament(name, date, type, TournamentFormats.getTournamentFormatsByName(tournamentFormat)));
    save(this);
  }

  public static void save(FileDatabase db) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.ser"));
      oos.writeObject(db);
      oos.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static FileDatabase load() throws FileNotFoundException {
    try {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database.ser"));
      @SuppressWarnings("unchecked")
      FileDatabase db = (FileDatabase) ois.readObject();
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

  public boolean checkDuplicateTournament(String tournamentName) {
    for (Tournament tournament : tournaments) {
      if (tournament.getName().equals(tournamentName)) {
        return true;
      }
    }
    return false;
  }

  public int getUserRatingByName(String name) {
    int won = getMatchesWonByUsername(name);
    int lost = getMatchesLostByUsername(name);

    return (won + lost != 0) ? won * 1000 / (won + lost) : 0;
  }

  public int getMatchesWonByUsername(String name) {
    int won = 0;

    for (Tournament t : tournaments) {
      won += t.getMatchesWonByUserName(name);
    }

    return (won);
  }

  public int getMatchesLostByUsername(String name) {
    int count = 0;

    for (Tournament t : tournaments) {
      count += t.getMatchesLostByUserName(name);
    }

    return (count);
  }

  @Override
  public void startTournament(String name) {
    Tournament tournament = getTournamentByName(name);
    tournament.start(null);
  }

  public void addParticipantsToTournament(Tournament t, ArrayList<User> participants) {
    t.setParticipants(participants);
    save(this);
  }

  public Team getTeamByParticipants(User... participants) {
    ArrayList<User> part = new ArrayList<User>();
    Collections.addAll(part, participants);
    for (Team t : teams) {
      if (t.hasParticipants(part)) {
        return t;
      }
    }
    return null;
  }

  /**
   * @return the teams
   */
  public ArrayList<Team> getTeams() {
    Collections.sort(teams, new Comparator<Team>() {

      @Override
      public int compare(Team o1, Team o2) {
        int mlt1 = FileDatabase.getInstance().getMatchesLostByTeam(o1);
        int mwt1 = FileDatabase.getInstance().getMatchesWonByTeam(o1);
        int mlt2 = FileDatabase.getInstance().getMatchesLostByTeam(o2);
        int mwt2 = FileDatabase.getInstance().getMatchesWonByTeam(o2);

        int rating1 = (mwt1 + mlt1) != 0 ? 1000 * mwt1 / (mwt1 + mlt1) : 0;
        int rating2 = (mwt2 + mlt2) != 0 ? 1000 * mwt2 / (mwt2 + mlt2) : 0;
        return rating2 - rating1;
      }

    });
    return teams;
  }

  /**
   * @param team
   * @return
   */
  public int getMatchesWonByTeam(Team team) {
    int won = 0;

    for (Tournament t : tournaments) {
      won += t.getMatchesWonByTeam(team);
    }

    return (won);
  }

  /**
   * @param team
   * @return
   */
  public int getMatchesLostByTeam(Team team) {
    int count = 0;

    for (Tournament t : tournaments) {
      count += t.getMatchesLostByTeam(team);
    }

    return (count);
  }

  /**
   * @param team
   */
  public void addTeam(Team team) {
    teams.add(team);
  }

  public void editTournament(String tourName, Date newDate, TournamentType type, String format) {
    Tournament tour = getTournamentByName(tourName);
    tour.setStartDate(newDate);
    tour.setType(type);
    tour.setFormat(TournamentFormats.getTournamentFormatsByName(format));
    save(this);
  }

  public void removeTournament(Tournament tour) {
    tournaments.remove(tour);
    save(this);
  }

  public int getUserStars(User user) {
    int counter = 0;
    for (Tournament t : tournaments) {
      if (t.isFinished() && t.getWinner().hasPlayer(user.getUser())) {
        counter++;
      }
    }
    return counter;
  }

  public int getTeamStars(Team tim) {

    int counter = 0;
    for (Tournament t : tournaments) {
      if (t.isFinished() && t.getWinner().toString().equals(tim.toString())) {
        counter++;
      }
    }
    return counter;
  }

  public void cleanup() {
    //    Iterator<Tournament> iterator = tournaments.iterator();
    //    while (iterator.hasNext()) {
    //      Tournament next = iterator.next();
    //      if (next.getName().contains("+") || next.getName().contains(" ")) {
    //        iterator.remove();
    //      }
    //    }
  }

  @Override
  public void addMatchScore(Match match, int score1, int score2) {
    // TODO Auto-generated method stub

  }

  @Override
  public Team getTeamByParticipants(List<Team> teams, User[] participants) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void cleanupTournaments() {
    // TODO Auto-generated method stub

  }

  @Override
  public void duplicateTournament(String name) {
    // TODO Auto-generated method stub

  }

  @Override
  public Team getTeamById(long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Match> getMatchesInvolvingTeams(Team t1, Team t2) {
    // TODO Auto-generated method stub
    return null;
  }
}
