/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.matchdef;

import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Tournament;
import com.ssn.tennis.model.enums.MatchType;
import com.ssn.tennis.model.proxy.GroupPositionTeamProxy;
import com.ssn.tennis.model.proxy.MatchWinnerTeamProxy;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */
public class KnockoutFromGroupMatchAndKnockoutFormatDefinition extends MatchFormatDefinition {

  private static final long serialVersionUID = 1L;
  private String team1Group;
  private int team1GroupPosition;
  private int team2MatchId;
  private boolean winner;

  public KnockoutFromGroupMatchAndKnockoutFormatDefinition(int number, String team1Group, int team1GroupPosition, int team2MatchId, boolean winner) {
    super(number, MatchType.SEMIFINAL);
    this.team1Group = team1Group;
    this.team1GroupPosition = team1GroupPosition;
    this.team2MatchId = team2MatchId;
    this.winner = winner;
  }

  @Override
  public Match createMatch(int number, Tournament tournament) {
    return new Match(number, new GroupPositionTeamProxy(this.team1Group, this.team1GroupPosition, tournament), //
      new MatchWinnerTeamProxy(this.team2MatchId, winner, tournament), tournament);
  }

}
