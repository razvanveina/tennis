/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
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

public class KnockoutFromKnockoutMatchFormatDefinition extends MatchFormatDefinition {

  private static final long serialVersionUID = 1L;
  private int team1MatchId;
  private int team2MatchId;
  private boolean winners;

  public KnockoutFromKnockoutMatchFormatDefinition(MatchType type, int number, int team1MatchId, int team2MatchId, boolean winners) {
    super(number, type);
    this.team1MatchId = team1MatchId;
    this.team2MatchId = team2MatchId;
    this.winners = winners;
  }

  @Override
  public Match createMatch(Tournament tournament) {
    return new Match(new MatchWinnerTeamProxy(this.team1MatchId, this.winners, tournament), //
      new MatchWinnerTeamProxy(this.team2MatchId, this.winners, tournament), this);
  }

}
