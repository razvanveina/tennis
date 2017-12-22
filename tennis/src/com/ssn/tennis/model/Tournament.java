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

import com.ssn.tennis.model.classification.Classification;

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
  private TournamentStatus status = TournamentStatus.NEW;
  private TournamentFormat format;

  public Tournament(String name, Date date, TournamentType type, TournamentFormat format) {
    this.name = name;
    startDate = date;
    this.type = type;
    this.format = format;
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
    status = TournamentStatus.STARTED;
  }

  private void buildTeams() {
    Collections.sort(participants, new Comparator<User>() {
      @Override
      public int compare(User o1, User o2) {
        return o1.getRating() - o2.getRating();
      }
    });

    for (int i = 0; i < participants.size() / 2; i++) {
      Team team = new Team();
      team.addPlayer(participants.get(i));
      team.addPlayer(participants.get(participants.size() - i - 1));
      teams.add(team);
    }

    MatchFormatDefinition[] matchesStructure = this.getFormat().getMatchesStructure();
    for (int i = 0; i < matchesStructure.length; i++) {
      if (matchesStructure[i] instanceof GroupMatchFormatDefinition) {
        GroupMatchFormatDefinition def = (GroupMatchFormatDefinition) matchesStructure[i];
        Team team1 = teams.get(def.getTeam1() - 1);
        Team team2 = teams.get(def.getTeam2() - 1);
        Match match = new Match(team1, team2, def);
        matches.add(match);
      }
    }
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

  public TournamentStatus getStatus() {
    return status;
  }

  public TournamentFormat getFormat() {
    return format;
  }

  public Classification getClassification(String group) {
    Classification cls = new Classification();

    for (Team team : teams) {
      cls.addTeam(team);
    }

    for (Match match : matches) {
      cls.addMatch(match);
    }

    return cls;
  }

}
