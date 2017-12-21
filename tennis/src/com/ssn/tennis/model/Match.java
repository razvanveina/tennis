/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.Serializable;

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

  public Match(Team team1, Team team2) {
    this.team1 = team1;
    this.team2 = team2;
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

  @Override
  public String toString() {
    return team1.toString() + " - " + team2.toString();
  }
}
