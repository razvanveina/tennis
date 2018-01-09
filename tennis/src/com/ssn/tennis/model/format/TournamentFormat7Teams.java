/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.format;

import com.ssn.tennis.model.enums.MatchType;
import com.ssn.tennis.model.matchdef.KnockoutFromKnockoutMatchAndTeamFormatDefinition;
import com.ssn.tennis.model.matchdef.KnockoutFromKnockoutMatchFormatDefinition;
import com.ssn.tennis.model.matchdef.MatchFormatDefinition;
import com.ssn.tennis.model.matchdef.SimpleMatchFormatDefinition;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TournamentFormat7Teams extends TournamentFormat {

  private static final long serialVersionUID = 1L;

  @Override
  public MatchFormatDefinition[] getMatchesStructure() {

    return new MatchFormatDefinition[] { //
      new SimpleMatchFormatDefinition(1, 1, 2), //
      new SimpleMatchFormatDefinition(2, 3, 4), //
      new SimpleMatchFormatDefinition(3, 5, 6), //
      new KnockoutFromKnockoutMatchFormatDefinition(MatchType.SEMIFINAL, 4, 1, 2, true), //
      new KnockoutFromKnockoutMatchAndTeamFormatDefinition(5, MatchType.SEMIFINAL, 3, 6), //

      new KnockoutFromKnockoutMatchFormatDefinition(MatchType.FINAL, 6, 4, 5, true), //
    };
  }

  @Override
  public String getName() {
    return "7 teams";
  }

  @Override
  public int getMaxTeams() {
    return 7;
  }

  @Override
  public String[] getGroupNames() {
    return new String[] {};
  }

  @Override
  public int[] getGroupMatchesCount() {
    return new int[] {};
  }

}
