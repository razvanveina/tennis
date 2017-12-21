/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

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

}
