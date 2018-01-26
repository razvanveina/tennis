/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.proxy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssn.tennis.model.Team;
import com.ssn.tennis.model.Tournament;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
@DiscriminatorValue("matchwin")
public class MatchWinnerTeamProxy extends Team {
  private static final long serialVersionUID = 1L;

  public MatchWinnerTeamProxy() {

  }

  private int teamMatchId;
  private boolean winners;

  @ManyToOne
  @JoinColumn(name = "team_id", nullable = true)
  private Team team;

  @ManyToOne
  @JoinColumn(name = "tournament_id", nullable = true)
  private Tournament tournament;

  public MatchWinnerTeamProxy(int teamMatchId, boolean winners, Tournament tournament) {
    this.teamMatchId = teamMatchId;
    this.winners = winners;
    this.tournament = tournament;
    this.setProxy(true);
  }

  @Override
  public String toString() {
    if (team == null) {
      if (!tournament.getMatches().get(teamMatchId - 1).isPlayed()) {
        return (winners ? "W" : "L") + teamMatchId;
      } else {
        if (winners) {
          team = tournament.getMatches().get(teamMatchId - 1).getWinningTeam();
        } else {
          team = tournament.getMatches().get(teamMatchId - 1).getLosingTeam();
        }
      }

    }

    return team.toString();
  }

  @Override
  public boolean hasPlayer(String name) {
    return (team != null ? team.hasPlayer(name) : false);
  }

  @Override
  public boolean equals(Object obj) {
    Team t = (Team) obj;
    if (team == null) {
      return false;
    }
    return team.equals(t);
  }

  @Override
  public Team getTeam() {
    return this.team;
  }
}
