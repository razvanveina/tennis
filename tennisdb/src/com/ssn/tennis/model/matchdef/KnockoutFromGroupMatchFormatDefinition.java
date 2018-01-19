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

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class KnockoutFromGroupMatchFormatDefinition extends MatchFormatDefinition {

  private static final long serialVersionUID = 1L;
  private String team1Group;
  private String team2Group;
  private int team1GroupPosition;
  private int team2GroupPosition;

  public KnockoutFromGroupMatchFormatDefinition(int number, String team1Group, String team2Group, int team1GroupPosition, int team2GroupPosition) {
    super(number, MatchType.SEMIFINAL);
    this.team1Group = team1Group;
    this.team2Group = team2Group;
    this.team1GroupPosition = team1GroupPosition;
    this.team2GroupPosition = team2GroupPosition;
  }

  @Override
  public Match createMatch(Tournament tournament) {
    return new Match(new GroupPositionTeamProxy(this.team1Group, this.team1GroupPosition, tournament), //
      new GroupPositionTeamProxy(this.team2Group, this.team2GroupPosition, tournament), this);
  }

}
