/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.format;

import com.ssn.tennis.model.matchdef.GroupMatchFormatDefinition;
import com.ssn.tennis.model.matchdef.MatchFormatDefinition;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TournamentFormat5Teams extends TournamentFormat {

  private static final long serialVersionUID = 1L;

  @Override
  public MatchFormatDefinition[] getMatchesStructure() {

    return new MatchFormatDefinition[] { //
      new GroupMatchFormatDefinition(1, "A", 1, 2), //
      new GroupMatchFormatDefinition(2, "A", 3, 4), //
      new GroupMatchFormatDefinition(3, "A", 5, 1), //
      new GroupMatchFormatDefinition(4, "A", 2, 3), //
      new GroupMatchFormatDefinition(5, "A", 4, 5), //
      new GroupMatchFormatDefinition(6, "A", 1, 3), //
      new GroupMatchFormatDefinition(7, "A", 2, 4), //
      new GroupMatchFormatDefinition(8, "A", 5, 3), //
      new GroupMatchFormatDefinition(9, "A", 1, 4), //
      new GroupMatchFormatDefinition(10, "A", 2, 5), //

    };
  }

  @Override
  public String getName() {
    return "5 teams";
  }

  @Override
  public int getMaxTeams() {
    return 5;
  }

  @Override
  public String[] getGroupNames() {
    return new String[] { "A" };
  }

  @Override
  public int[] getGroupMatchesCount() {
    return new int[] { 10 };
  }

}
