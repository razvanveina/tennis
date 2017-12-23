/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.Serializable;

import com.ssn.tennis.model.classification.ClassificationLine;
import com.ssn.tennis.model.matchdef.GroupMatchFormatDefinition;
import com.ssn.tennis.model.matchdef.MatchFormatDefinition;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Match implements Serializable {
  private static final long serialVersionUID = 1L;

  Team team1;
  Team team2;

  int points1;
  int points2;

  private MatchFormatDefinition format;

  public Match(Team team1, Team team2, MatchFormatDefinition def) {
    this.team1 = team1;
    this.team2 = team2;
    this.format = def;
  }

  public boolean isPlayed() {
    return points1 > 0 || points2 > 0;
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
    return format;
  }

  public void setFormat(GroupMatchFormatDefinition format) {
    this.format = format;
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
    return format.isGroupMatch(group);
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
}
