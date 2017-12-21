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

public class TournamentFormat6Teams extends TournamentFormat {

  private static final long serialVersionUID = 1L;

  @Override
  public MatchFormatDefinition[] getMatchesStructure() {

    return new MatchFormatDefinition[] { //
      new GroupMatchFormatDefinition(1, "A", 1, 2), //
      new GroupMatchFormatDefinition(2, "B", 3, 4), //
      new GroupMatchFormatDefinition(3, "A", 1, 5), //
      new GroupMatchFormatDefinition(4, "B", 3, 6), //
      new GroupMatchFormatDefinition(5, "A", 2, 5), //
      new GroupMatchFormatDefinition(6, "B", 4, 6), //
      new KnockoutFromGroupMatchFormatDefinition(7, "A", "B", 1, 2), //
      new KnockoutFromGroupMatchFormatDefinition(8, "A", "B", 1, 2), //
      new KnockoutFromKnockoutMatchFormatDefinition(9, 7, 8, true), //
    };
  }

  @Override
  public String getName() {
    return "6 teams";
  }

  @Override
  public int getMaxPlayers() {
    return 12;
  }

}