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

public class MatchWinnerTeamProxy extends Team {
  private static final long serialVersionUID = 1L;

  private int teamMatchId;
  private boolean winners;

  private Team team;

  private Tournament tournament;

  public MatchWinnerTeamProxy(int teamMatchId, boolean winners, Tournament tournament) {
    this.teamMatchId = teamMatchId;
    this.winners = winners;
    this.tournament = tournament;
  }

  @Override
  public String toString() {
    if (team == null) {
      if (!tournament.getMatches().get(teamMatchId - 1).isPlayed()) {
        return (winners ? "W" : "L") + teamMatchId;
      } else {
        team = tournament.getMatches().get(teamMatchId - 1).getWinningTeam();
      }

    }

    return team.toString();
  }

  @Override
  public boolean hasPlayer(String name) {
    return (team != null ? team.hasPlayer(name) : false);
  }

}
