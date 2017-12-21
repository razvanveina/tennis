/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Tournament implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private Date startDate;
  private ArrayList<User> participants = new ArrayList<User>();
  private TournamentType type = TournamentType.DOUBLE;
  private ArrayList<Team> teams = new ArrayList<Team>();
  private ArrayList<Match> matches = new ArrayList<Match>();

  public Tournament(String name, Date date, TournamentType type) {
    this.name = name;
    startDate = date;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public ArrayList<User> getParticipants() {
    return participants;
  }

  public void setParticipants(ArrayList<User> participants) {
    this.participants = participants;
  }

  //  public void addParticipant(String userName) {
  //
  //    participants.add(Database.getInstance().getUserByUsername(userName));
  //  }

  public TournamentType getType() {
    return type;
  }

  public void setType(TournamentType type) {
    this.type = type;
  }

  public ArrayList<Team> getTeams() {
    return teams;
  }

  public void setTeams(ArrayList<Team> teams) {
    this.teams = teams;
  }

  public String getParticipantsAsString() {
    String participantsNames = "[";
    if (!participants.isEmpty()) {
      for (User part : participants) {

        participantsNames = participantsNames + part.getName() + ",";
      }
    }
    return participantsNames + "]";
  }

  public boolean isStarted() {
    return teams.size() > 0;
  }

  public void start() {
    buildTeams();
  }

  private void buildTeams() {
    Collections.sort(participants, new Comparator<User>() {

      @Override
      public int compare(User o1, User o2) {
        return o1.getRating() - o2.getRating();
      }
    });
  }

  public ArrayList<Match> getMatches() {
    return matches;
  }

  public int getMatchesWonByUserName(String name) {
    int won = 0;

    for (Match m : matches) {
      if (m.isPlayed()) {
        if (m.hasPlayer(name)) {
          if (m.isWonByUser(name)) {
            won++;
          }
        }
      }
    }

    return won;
  }

  public int getMatchesLostByUserName(String name) {
    int lost = 0;

    for (Match m : matches) {
      if (m.isPlayed()) {
        if (m.hasPlayer(name)) {
          if (m.isLostByUser(name)) {
            lost++;
          }
        }
      }
    }

    return lost;
  }

}
