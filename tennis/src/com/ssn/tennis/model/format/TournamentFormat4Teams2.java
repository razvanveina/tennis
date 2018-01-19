/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.format;

import com.ssn.tennis.model.enums.MatchType;
import com.ssn.tennis.model.matchdef.GroupMatchFormatDefinition;
import com.ssn.tennis.model.matchdef.KnockoutFromKnockoutMatchFormatDefinition;
import com.ssn.tennis.model.matchdef.MatchFormatDefinition;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TournamentFormat4Teams2 extends TournamentFormat {

  private static final long serialVersionUID = 1L;

  @Override
  public MatchFormatDefinition[] getMatchesStructure() {

    return new MatchFormatDefinition[] { //
      new GroupMatchFormatDefinition(1, "A", 1, 2), //
      new GroupMatchFormatDefinition(2, "A", 3, 4), //
      new KnockoutFromKnockoutMatchFormatDefinition(MatchType.FINAL, 3, 1, 2, true), //

    };
  }

  @Override
  public String getName() {
    return "4 teams 3 matches";
  }

  @Override
  public int getMaxTeams() {
    return 4;
  }

  @Override
  public String[] getGroupNames() {
    return new String[] {};
  }

  @Override
  public int[] getGroupMatchesCount() {
    return new int[] { 3, 3 };
  }

}
