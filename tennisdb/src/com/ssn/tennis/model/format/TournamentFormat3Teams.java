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

public class TournamentFormat3Teams extends TournamentFormat {

  private static final long serialVersionUID = 1L;

  @Override
  public MatchFormatDefinition[] getMatchesStructure() {

    return new MatchFormatDefinition[] { //
      new GroupMatchFormatDefinition(1, "A", 1, 2), //
      new GroupMatchFormatDefinition(2, "A", 3, 1), //
      new GroupMatchFormatDefinition(3, "A", 2, 3), //

    };
  }

  @Override
  public String getName() {
    return "3 teams";
  }

  @Override
  public int getMaxTeams() {
    return 3;
  }

  @Override
  public String[] getGroupNames() {
    return new String[] { "A" };
  }

  @Override
  public int[] getGroupMatchesCount() {
    return new int[] { 3 };
  }

}
