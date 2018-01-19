/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TeamTest {
  @Test
  public void testHasPlayer() {
    Team team = new Team();
    team.addPlayer(new User("0", "0", "0", "0", false));
    team.addPlayer(new User("1", "0", "0", "0", false));
    assertEquals(true, team.hasPlayer("0"));
  }
}
