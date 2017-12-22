/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.proxy;

import com.ssn.tennis.model.Team;
import com.ssn.tennis.model.Tournament;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class GroupPositionTeamProxy extends Team {

  private static final long serialVersionUID = 1L;
  private String teamGroup;
  private int teamGroupPosition;
  private Team team;
  private Tournament tournament;

  public GroupPositionTeamProxy(String teamGroup, int teamGroupPosition, Tournament tournament) {
    this.teamGroup = teamGroup;
    this.teamGroupPosition = teamGroupPosition;
    this.tournament = tournament;
    this.team = null;
  }

  @Override
  public String toString() {
    if (team == null) {
      if (!tournament.isGroupFinished(teamGroup)) {
        return teamGroup + teamGroupPosition;
      } else {
        team = tournament.getClassification(teamGroup).getCls().get(teamGroupPosition - 1).getTeam();
      }

    }

    return team.toString();

  }
}
