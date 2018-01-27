/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ssn.tennis.model.enums.TournamentType;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public interface Database {

  public void addUser(User user);

  public void removeUser(User user);

  public User checkLogin(String user, String password);

  public User getUserByUsername(String user);

  public Tournament getTournamentByName(String name);

  public List<User> getUsers();

  public void editUser(String oldUser, String user, String pass, String name, String surname, boolean admin);

  public boolean changePassword(String oldUser, String oldPass, String newPass);

  public void addTournament(String name, Date date, TournamentType type, String tournamentFormat);

  public ArrayList<Tournament> getTournaments();

  public boolean checkDuplicateTournament(String tournamentName);

  public int getUserRatingByName(String name);

  public int getMatchesWonByUsername(String name);

  public int getMatchesLostByUsername(String name);

  public void startTournament(String name);

  public void addParticipantsToTournament(Tournament t, ArrayList<User> participants);

  public ArrayList<Team> getTeams();

  public int getMatchesWonByTeam(Team team);

  public int getMatchesLostByTeam(Team team);

  public void addTeam(Team team);

  public void editTournament(String tourName, Date newDate, TournamentType type, String format);

  public void removeTournament(Tournament tour);

  public int getUserStars(User user);

  public int getTeamStars(Team tim);

  public void addMatchScore(Match match, int score1, int score2);

  void init();

  Team getTeamByParticipants(List<Team> teams, User... participants);

  void cleanupTournaments();

  void duplicateTournament(String name);

  Team getTeamById(long id);

  List<Match> getMatchesInvolvingTeams(Team t1, Team t2);
}
