/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.util.ArrayList;
import java.util.Date;

import com.ssn.tennis.model.enums.TournamentType;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class OracleDatabase implements DatabaseInterface {

  @Override
  public void addUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public User checkLogin(String user, String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User getUserByUsername(String user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Tournament getTournamentByName(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<User> getUsers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void editUser(String oldUser, String user, String pass, String name, String surname, boolean admin) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean changePassword(String oldUser, String oldPass, String newPass) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void addTournament(String name, Date date, TournamentType type, String tournamentFormat) {
    // TODO Auto-generated method stub

  }

  @Override
  public ArrayList<Tournament> getTournaments() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean checkDuplicateTournament(String tournamentName) {
    // TODO Auto-generated method stub
    return false;
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getMatchesWonByTeam(Team team) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getMatchesLostByTeam(Team team) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void addTeam(Team team) {
    // TODO Auto-generated method stub

  }

  @Override
  public void editTournament(String tourName, Date newDate, TournamentType type, String format) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeTournament(Tournament tour) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getUserStars(User user) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getTeamStars(Team tim) {
    // TODO Auto-generated method stub
    return 0;
  }

}
