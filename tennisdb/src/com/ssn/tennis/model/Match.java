/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.ssn.tennis.model.classification.ClassificationLine;
import com.ssn.tennis.model.matchdef.MatchFormatDefinition;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
public class Match implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen", name = "gen")
  private long id;

  @Column(name = "match_number")
  private int number;

  @ManyToOne
  @JoinColumn(name = "team1_id", nullable = true)
  private Team team1;

  @ManyToOne
  @JoinColumn(name = "team2_id", nullable = true)
  private Team team2;

  private int points1;
  private int points2;

  @ManyToOne
  @JoinColumn(name = "tournament_id", nullable = false)
  private Tournament tournament;

  public Match() {

  }

  public Match(int number, Team team1, Team team2, Tournament tournament) {
    this.number = number;
    this.team1 = team1;
    this.team2 = team2;
    this.tournament = tournament;
  }

  public boolean isPlayed() {
    return points1 > 10 || points2 > 10;
  }

  public boolean hasPlayer(String name) {
    return team1.hasPlayer(name) || team2.hasPlayer(name);
  }

  public boolean isWonByUser(String name) {
    return points1 > points2 && team1.hasPlayer(name) || //
      points2 > points1 && team2.hasPlayer(name);
  }

  public boolean isLostByUser(String name) {
    return !isWonByUser(name);
  }

  public MatchFormatDefinition getFormat() {
    return tournament.getMatchFormatDefinition(number);
  }

  public Team getTeam1() {
    return team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public boolean isWonByTeam1() {
    return points1 > points2;
  }

  /**
   * @param cl1
   * @param cl2
   */
  public void addToClassificationLines(ClassificationLine cl1, ClassificationLine cl2) {
    if (!isPlayed()) {
      return;
    }

    if (isWonByTeam1()) {
      cl1.incrementWins();
      cl2.incrementLosses();
    } else {
      cl1.incrementLosses();
      cl2.incrementWins();
    }

    cl1.addPoints(points1, points2);
    cl2.addPoints(points2, points1);
  }

  public int getPoints1() {
    return points1;
  }

  public void setPoints1(int points1) {
    this.points1 = points1;
  }

  public int getPoints2() {
    return points2;
  }

  public void setPoints2(int points2) {
    this.points2 = points2;
  }

  @Override
  public String toString() {
    return team1.toString() + " - " + team2.toString();
  }

  public boolean isGroupMatch(String group) {
    return getFormat().isGroupMatch(group);
  }

  /**
   * @return
   */
  public Team getWinningTeam() {
    return (isWonByTeam1() ? team1 : team2);
  }

  public Team getLosingTeam() {
    return (isWonByTeam1() ? team2 : team1);
  }

  /**
   * @param teams
   * @return
   */
  public boolean isBetweenTeams(ArrayList<Team> teams) {
    return teams.contains(team1) && teams.contains(team2);
  }

  public boolean hasTeam(Team team) {
    return team1.equals(team) || team2.equals(team);
  }

  public boolean isWonByTeam(Team team) {
    return points1 > points2 && team1.equals(team) || //
      points2 > points1 && team2.equals(team);
  }

  public boolean isLostByTeam(Team team) {
    return !isWonByTeam(team);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTeam1(Team team1) {
    this.team1 = team1;
  }

  public void setTeam2(Team team2) {
    this.team2 = team2;
  }

  /**
   * @return the number
   */
  public int getNumber() {
    return number;
  }

  /**
   * @param number the number to set
   */
  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + number;
    result = prime * result + ((tournament == null) ? 0 : tournament.hashCode());
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
    Match other = (Match) obj;
    if (number != other.number)
      return false;
    if (tournament == null) {
      if (other.tournament != null)
        return false;
    } else if (!tournament.equals(other.tournament))
      return false;
    return true;
  }

  /**
   * @return the tournament
   */
  public Tournament getTournament() {
    return tournament;
  }

  /**
   * @param tournament the tournament to set
   */
  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }

}
