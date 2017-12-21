/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public abstract class TournamentFormat implements Serializable {
  private static final long serialVersionUID = 1L;

  public abstract MatchFormatDefinition[] getMatchesStructure();

  public abstract String getName();

  public abstract int getMaxPlayers();
}
