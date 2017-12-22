/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.ssn.tennis.model.enums.TournamentType;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TournamentTest {
  @Test
  public void testGetMatchesWonByUsername() {
    Database db = Database.getInstance();
    for (int i = 0; i < 12; i++) {
      db.addUser(new User("" + i, "" + i, "" + i, "" + i, false));
    }

    db.addTournament("1", new Date(), TournamentType.DOUBLE, "6 teams");

    Tournament t = db.getTournamentByName("1");
    db.addParticipantsToTournament(t, db.getUsers());
    t.start();

    t.getMatches().get(0).setPoints1(11);
    t.getMatches().get(0).setPoints2(0);

    assertEquals(1, t.getMatchesWonByUserName("0"));
  }
}
