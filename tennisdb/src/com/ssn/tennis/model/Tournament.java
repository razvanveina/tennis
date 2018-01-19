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
import java.util.List;

import com.ssn.tennis.model.classification.Classification;
import com.ssn.tennis.model.enums.MatchType;
import com.ssn.tennis.model.enums.TournamentStatus;
import com.ssn.tennis.model.enums.TournamentType;
import com.ssn.tennis.model.format.TournamentFormat;
import com.ssn.tennis.model.matchdef.MatchFormatDefinition;

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
        if (participantsNames.length() != 1) {
          participantsNames += ", " + part.getUser();
        } else {
          participantsNames += part.getUser();
        }
      }
    }

    return participantsNames + "]";
  }

  public int getMaxPlayers() {
    return format.getMaxTeams() * type.getPlayersPerTeam();
  }

  public boolean isStarted() {
    return matches.size() > 0;
  }

  public void start() {
    if (this.isStarted()) {
      return;
    }
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

    List<User> topHalf = new ArrayList<>(participants.subList(0, participants.size() / 2));
    List<User> bottomHalf = new ArrayList<>(participants.subList(participants.size() / 2, participants.size()));
    Collections.shuffle(topHalf);
    Collections.shuffle(bottomHalf);

    participants.clear();
    participants.addAll(topHalf);
    participants.addAll(bottomHalf);

    for (int i = 0; i < participants.size() / type.getPlayersPerTeam(); i++) {
      Team team = null;
      if (type.equals(TournamentType.DOUBLE)) {
        team = Database.getInstance().getTeamByParticipants(participants.get(i), participants.get(participants.size() - i - 1));
        if (team == null) {
          team = new Team();
          team.addPlayer(participants.get(i));
          team.addPlayer(participants.get(participants.size() - i - 1));
          Database.getInstance().addTeam(team);
        }
      } else {
        team = Database.getInstance().getTeamByParticipants(participants.get(i));
        if (team == null) {
          team = new Team();
          team.addPlayer(participants.get(i));
          Database.getInstance().addTeam(team);
        }
      }

      //Team team = this.type.createTeamForParticipantsAndIndex();
      teams.add(team);
    }

    MatchFormatDefinition[] matchesStructure = this.getFormat().getMatchesStructure();
    for (int i = 0; i < matchesStructure.length; i++) {
      MatchFormatDefinition def = matchesStructure[i];
      Match match = def.createMatch(this);
      //      Team team1 = teams.get(def.getTeam1() - 1);
      //      Team team2 = teams.get(def.getTeam2() - 1);
      //      Match match = new Match(team1, team2, def);
      matches.add(match);
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
    if (isFinished()) {
      return TournamentStatus.FINISHED;
    }
    if (isStarted()) {
      return TournamentStatus.STARTED;
    }
    return status;
  }

  public TournamentFormat getFormat() {
    return format;
  }

  public Classification getClassification(String group) {
    Classification cls = new Classification();

    for (Match match : matches) {
      if (match.isGroupMatch(group)) {
        cls.addMatch(match);
      }
    }

    cls.sort();

    return cls;
  }

  public boolean isGroupFinished(String group) {
    for (Match match : matches) {
      if (match.isGroupMatch(group)) {
        if (!match.isPlayed()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @param team
   * @return
   */
  public int getMatchesWonByTeam(Team team) {
    int won = 0;

    for (Match m : matches) {
      if (m.isPlayed()) {
        if (m.hasTeam(team)) {
          if (m.isWonByTeam(team)) {
            won++;
          }
        }
      }
    }

    return won;
  }

  /**
   * @param team
   * @return
   */
  public int getMatchesLostByTeam(Team team) {
    int lost = 0;

    for (Match m : matches) {
      if (m.isPlayed()) {
        if (m.hasTeam(team)) {
          if (m.isLostByTeam(team)) {
            lost++;
          }
        }
      }
    }

    return lost;
  }

  public boolean isFinished() {
    if (matches.isEmpty()) {
      return false;
    }
    for (Match m : matches) {
      if (!m.isPlayed()) {
        return false;
      }
    }
    return true;
  }

  public void setFormat(TournamentFormat format) {
    this.format = format;
  }

  public Team getWinner() {
    Team winner = null;
    if (isFinished()) {
      for (Match m : matches) {
        if (m.getFormat().getType().equals(MatchType.FINAL)) {
          winner = m.getWinningTeam();
        }
      }
      if (winner == null) {
        winner = getClassification("A").getCls().get(0).getTeam();
      }
    }
    return winner;
  }

}
