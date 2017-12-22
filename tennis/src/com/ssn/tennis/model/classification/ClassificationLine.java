/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.classification;

import com.ssn.tennis.model.Team;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class ClassificationLine implements Comparable<ClassificationLine> {
  private Team team;
  private int won;
  private int lost;
  private int gf;
  private int ga;

  public ClassificationLine(Team team) {
    super();
    this.team = team;
  }

  @Override
  public int compareTo(ClassificationLine o) {
    if (this.won > o.won) {
      return -1;
    } else if (this.won == o.won && this.getGoalDiff() > o.getGoalDiff()) {
      return -1;
    } else if (this.won == o.won && this.getGoalDiff() == o.getGoalDiff() && this.gf > o.ga) {
      return -1;
    } else if (this.won == o.won && this.getGoalDiff() == o.getGoalDiff() && this.gf == o.ga) {
      return 0;
    }

    return 1;
  }

  private int getGoalDiff() {
    return gf - ga;
  }

  public Team getTeam() {
    return team;
  }

  /**
   * 
   */
  public void incrementWins() {
    won++;
  }

  /**
   * 
   */
  public void incrementLosses() {
    lost++;
  }

  /**
   * @param points2
   * @param points1
   */
  public void addPoints(int points1, int points2) {
    gf += points1;
    ga += points2;
  }

}
