/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.format;

import java.io.Serializable;

import com.ssn.tennis.model.matchdef.MatchFormatDefinition;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public abstract class TournamentFormat implements Serializable {
  private static final long serialVersionUID = 1L;

  public abstract MatchFormatDefinition[] getMatchesStructure();

  public abstract String getName();

  public abstract int getMaxPlayers();

  public abstract String[] getGroupNames();

  public abstract int[] getGroupMatchesCount();
}
