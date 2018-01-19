/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.matchdef;

import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Team;
import com.ssn.tennis.model.Tournament;
import com.ssn.tennis.model.enums.MatchType;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class GroupMatchFormatDefinition extends MatchFormatDefinition {

  private static final long serialVersionUID = 1L;
  private String group;
  private int team1;
  private int team2;

  public GroupMatchFormatDefinition(int number, String group, int team1, int team2) {
    super(number, MatchType.GROUP);
    this.group = group;
    this.team1 = team1;
    this.team2 = team2;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public int getTeam1() {
    return team1;
  }

  public void setTeam1(int team1) {
    this.team1 = team1;
  }

  public int getTeam2() {
    return team2;
  }

  public void setTeam2(int team2) {
    this.team2 = team2;
  }

  @Override
  public boolean isGroupMatch(String group) {
    return group.equals(this.group);
  }

  @Override
  public String getStageInfo() {
    return "Group " + group;
  }

  @Override
  public Match createMatch(Tournament tournament) {
    Team t1 = tournament.getTeams().get(getTeam1() - 1);
    Team t2 = tournament.getTeams().get(getTeam2() - 1);
    return new Match(t1, t2, this);
  }
}
