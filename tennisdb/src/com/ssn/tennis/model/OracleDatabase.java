/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ssn.core.persistence.WithSessionAndTransaction;
import com.ssn.tennis.common.Utils;
import com.ssn.tennis.controller.TennisManager;
import com.ssn.tennis.model.enums.TournamentType;
import com.ssn.tennis.model.format.TournamentFormats;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class OracleDatabase implements Database {

  private static Database instance = null;

  private OracleDatabase() {
    init();
  }

  @Override
  public void addUser(User user) {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        session.save(user);
      }

    }.run();
  }

  @Override
  public void removeUser(User user) {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        session.delete(user);
      }

    }.run();
  }

  @Override
  public User checkLogin(String user, String password) {
    return new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        Query query = session.createQuery("from User where user = :user and password = :password");
        query.setParameter("user", user);
        query.setParameter("password", Utils.encrypt(password));
        List result = query.list();

        if (result.size() > 0) {
          setReturnValue((User) result.get(0));
        }
      }

    }.run();
  }

  @Override
  public User getUserByUsername(String user) {
    return new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        setReturnValue(tm.findUserByUserName(user));
      }

    }.run();
  }

  @Override
  public Tournament getTournamentByName(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<User> getUsers() {
    return new WithSessionAndTransaction<ArrayList<User>>() {

      @SuppressWarnings("unchecked")
      @Override
      protected void executeBusinessLogic(Session session) {
        Query query = session.createQuery("from User");
        List result = query.list();
        setReturnValue(new ArrayList<User>(result));
      }

    }.run();

  }

  @Override
  public void editUser(String oldUser, String user, String pass, String name, String surname, boolean admin) {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        User userDB = tm.findUserByUserName(oldUser);
        userDB.setUser(user);
        userDB.setPassword(pass);
        userDB.setName(name);
        userDB.setSurname(surname);
        userDB.setAdmin(admin);
        session.update(userDB);
      }

    }.run();
  }

  @Override
  public boolean changePassword(String oldUser, String oldPass, String newPass) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void addTournament(String name, Date date, TournamentType type, String tournamentFormat) {
    new WithSessionAndTransaction<ArrayList<Tournament>>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        Tournament tournament = new Tournament(name, date, type, TournamentFormats.getTournamentFormatsByName(tournamentFormat));
        session.save(tournament);
      }

    }.run();
  }

  @Override
  public ArrayList<Tournament> getTournaments() {
    return new WithSessionAndTransaction<ArrayList<Tournament>>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        setReturnValue(tm.findAllTournaments());
      }

    }.run();
  }

  @Override
  public boolean checkDuplicateTournament(String name) {
    return new WithSessionAndTransaction<Boolean>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        Tournament t = tm.findTournamentByName(name);
        setReturnValue(t != null);
      }

    }.run();
  }

  @Override
  public int getUserRatingByName(String name) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getMatchesWonByUsername(String name) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getMatchesLostByUsername(String name) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void startTournament(String name) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addParticipantsToTournament(Tournament t, ArrayList<User> participants) {
    // TODO Auto-generated method stub

  }

  @Override
  public Team getTeamByParticipants(User... participants) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<Team> getTeams() {
    return new WithSessionAndTransaction<ArrayList<Team>>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        setReturnValue(tm.findAllTeams());
      }

    }.run();
  }

  @Override
  public int getMatchesWonByTeam(Team team) {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesWonByTeam(team);
    }

    return (count);
  }

  @Override
  public int getMatchesLostByTeam(Team team) {
    int count = 0;

    for (Tournament t : getTournaments()) {
      count += t.getMatchesLostByTeam(team);
    }

    return (count);
  }

  @Override
  public void addTeam(Team team) {
    new WithSessionAndTransaction<Team>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        session.save(team);
      }

    }.run();

  }

  @Override
  public void editTournament(String tourName, Date newDate, TournamentType type, String format) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeTournament(Tournament tour) {
    new WithSessionAndTransaction<Tournament>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        session.delete(tour);
      }

    }.run();
  }

  @Override
  public int getUserStars(User user) {
    int counter = 0;
    for (Tournament t : getTournaments()) {
      if (t.isFinished() && t.getWinner().hasPlayer(user.getUser())) {
        counter++;
      }
    }
    return counter;
  }

  @Override
  public int getTeamStars(Team tim) {
    int counter = 0;
    for (Tournament t : getTournaments()) {
      if (t.isFinished() && t.getWinner().toString().equals(tim.toString())) {
        counter++;
      }
    }
    return counter;
  }

  @Override
  public void init() {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        User raz = new User("raz", "qwe", "Razvan", "Veina", true);
        session.save(raz);
      }

    }.run();
  }

  public static Database getInstance() {
    if (instance == null) {
      instance = new OracleDatabase();
    }

    return instance;
  }

}
