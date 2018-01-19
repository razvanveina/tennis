/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.enums;

import java.io.Serializable;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public enum TournamentType implements Serializable {
    SINGLE(1), DOUBLE(2);

  private int playersPerTeam;

  TournamentType(int playersPerTeam) {
    this.playersPerTeam = playersPerTeam;
  }

  public int getPlayersPerTeam() {
    return playersPerTeam;
  }

}
