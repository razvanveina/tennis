/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.matchdef;

import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Tournament;
import com.ssn.tennis.model.enums.MatchType;
import com.ssn.tennis.model.proxy.MatchWinnerTeamProxy;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class KnockoutFromKnockoutMatchAndTeamFormatDefinition extends MatchFormatDefinition {

  private int team1Match;
  private int team2;

  /**
   * @param number
   * @param type
   */
  public KnockoutFromKnockoutMatchAndTeamFormatDefinition(int number, MatchType type, int team1Match, int team2) {
    super(number, type);
    this.team1Match = team1Match;
    this.team2 = team2;
  }

  private static final long serialVersionUID = 1L;

  @Override
  public Match createMatch(Tournament tournament) {
    return new Match(new MatchWinnerTeamProxy(this.team1Match, true, tournament), //
      tournament.getTeams().get(team2), this);
  }

}
