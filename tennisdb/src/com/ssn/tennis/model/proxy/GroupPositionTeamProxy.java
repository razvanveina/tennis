/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.proxy;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssn.tennis.model.Team;
import com.ssn.tennis.model.Tournament;
import com.ssn.tennis.model.User;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
@DiscriminatorValue("grouppos")
public class GroupPositionTeamProxy extends Team {

  private static final long serialVersionUID = 1L;
  private String teamGroup;
  private int teamGroupPosition;

  @ManyToOne
  @JoinColumn(name = "team2_id", nullable = true)
  private Team team;

  @ManyToOne
  @JoinColumn(name = "tournament2_id", nullable = true)
  private Tournament tournament;

  public GroupPositionTeamProxy() {

  }

  public GroupPositionTeamProxy(String teamGroup, int teamGroupPosition, Tournament tournament) {
    this.teamGroup = teamGroup;
    this.teamGroupPosition = teamGroupPosition;
    this.tournament = tournament;
    this.team = null;
    this.setProxy(true);
  }

  @Override
  public boolean hasPlayer(String name) {
    return (team != null ? team.hasPlayer(name) : false);
  }

  @Override
  public List<User> getPlayers() {
    return (team != null ? team.getPlayers() : null);
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

  @Override
  public void regenerate() {
    team = null;
  }

  @Override
  public int getWon() {
    if (team == null) {
      return 0;
    }

    int count = 0;

    for (Tournament t : team.getTournaments()) {
      count += t.getMatchesWonByTeam(this);
    }

    return (count);

  }

  @Override
  public int getLost() {
    if (team == null) {
      return 0;
    }

    int count = 0;

    for (Tournament t : team.getTournaments()) {
      count += t.getMatchesLostByTeam(this);
    }

    return (count);
  }

}
