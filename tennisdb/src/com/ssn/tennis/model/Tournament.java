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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.ssn.core.ApplicationFactory;
import com.ssn.tennis.model.classification.Classification;
import com.ssn.tennis.model.enums.MatchType;
import com.ssn.tennis.model.enums.TournamentStatus;
import com.ssn.tennis.model.enums.TournamentType;
import com.ssn.tennis.model.format.TournamentFormat;
import com.ssn.tennis.model.format.TournamentFormats;
import com.ssn.tennis.model.matchdef.MatchFormatDefinition;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
@NamedQueries({ //
  @NamedQuery(name = Tournament.TOURNAMENT_ALL, query = "from Tournament"), //
  @NamedQuery(name = Tournament.TOURNAMENT_BY_NAME, query = "from Tournament where name=:name") })
public class Tournament implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String TOURNAMENT_ALL = "Tournament.all";
  public static final String TOURNAMENT_BY_NAME = "Tournament.by.name";

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen", name = "gen")
  private long id;

  private String name;
  private Date startDate;
  private TournamentType type = TournamentType.DOUBLE;
  private TournamentStatus status = TournamentStatus.NEW;

  @Enumerated(EnumType.STRING)
  private TournamentFormats format;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "tournament_player", //
  joinColumns = { @JoinColumn(name = "tournament_id") }, //
  inverseJoinColumns = { @JoinColumn(name = "player_id") })
  private List<User> participants = new ArrayList<User>();

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "tournament_team", //
  joinColumns = { @JoinColumn(name = "tournament_id") }, //
  inverseJoinColumns = { @JoinColumn(name = "team_id") })
  private List<Team> teams = new ArrayList<Team>();

  @OneToMany(mappedBy = "tournament")
  private List<Match> matches = new ArrayList<Match>();

  public Tournament() {

  }

  public Tournament(String name, Date date, TournamentType type, TournamentFormats format) {
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

  public List<User> getParticipants() {
    return participants;
  }

  public void setParticipants(List<User> participants) {
    this.participants = participants;
  }

  //  public void addParticipant(String userName) {
  //
  //    participants.add(FileDatabase.getInstance().getUserByUsername(userName));
  //  }

  public TournamentType getType() {
    return type;
  }

  public void setType(TournamentType type) {
    this.type = type;
  }

  public List<Team> getTeams() {
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
    return format.getTournamentFormat().getMaxTeams() * type.getPlayersPerTeam();
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
    List<User> participantsCopy = new ArrayList<>(participants);
    Collections.sort(participantsCopy, new Comparator<User>() {
      @Override
      public int compare(User o1, User o2) {
        return o1.getRating() - o2.getRating();
      }
    });

    List<User> topHalf = new ArrayList<>(participantsCopy.subList(0, participantsCopy.size() / 2));
    List<User> bottomHalf = new ArrayList<>(participantsCopy.subList(participantsCopy.size() / 2, participantsCopy.size()));
    Collections.shuffle(topHalf);
    Collections.shuffle(bottomHalf);

    participantsCopy.clear();
    participantsCopy.addAll(topHalf);
    participantsCopy.addAll(bottomHalf);

    for (int i = 0; i < participantsCopy.size() / type.getPlayersPerTeam(); i++) {
      Team team = null;
      if (type.equals(TournamentType.DOUBLE)) {
        User p1 = participantsCopy.get(i);
        User p2 = participantsCopy.get(participantsCopy.size() - i - 1);
        team = ApplicationFactory.getInstance().getDatabase().getTeamByParticipants(p1, p2);
        if (team == null) {
          team = new Team();
          team.addPlayer(p1);
          team.addPlayer(p2);
          FileDatabase.getInstance().addTeam(team);
        }
      } else {
        User p1 = participantsCopy.get(i);
        team = ApplicationFactory.getInstance().getDatabase().getTeamByParticipants(p1);
        if (team == null) {
          team = new Team();
          team.addPlayer(p1);
          ApplicationFactory.getInstance().getDatabase().addTeam(team);
        }
      }

      teams.add(team);
    }

    MatchFormatDefinition[] matchesStructure = this.getFormat().getMatchesStructure();
    for (int i = 0; i < matchesStructure.length; i++) {
      MatchFormatDefinition def = matchesStructure[i];
      Match match = def.createMatch(i + 1, this);
      matches.add(match);
    }
  }

  public List<Match> getMatches() {
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
    return format.getTournamentFormat();
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

  public void setFormat(TournamentFormats format) {
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

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  public void setMatches(ArrayList<Match> matches) {
    this.matches = matches;
  }

  public void setStatus(TournamentStatus status) {
    this.status = status;
  }

  public MatchFormatDefinition getMatchFormatDefinition(int number) {
    for (Match m : matches) {
      if (m.getNumber() == number) {
        return format.getTournamentFormat().getMatchesStructure()[number - 1];
      }
    }
    return null;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tournament other = (Tournament) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}
