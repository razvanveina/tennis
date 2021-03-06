/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.db;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

import com.ssn.core.persistence.WithSessionAndTransaction;
import com.ssn.tennis.controller.TennisManager;
import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Team;
import com.ssn.tennis.model.Tournament;
import com.ssn.tennis.model.User;
import com.ssn.tennis.model.enums.TournamentType;
import com.ssn.tennis.model.format.TournamentFormats;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class OracleDatabaseTest {

  @Test
  public void testTeam() {
    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        User raz = new User("raz", "qwe", "Razvan", "Veina", true);
        session.save(raz);
        User dst = new User("dst", "dst", "Dana", "Stan", true);
        session.save(dst);
        User cni = new User("cni", "cni", "Catalin", "Nichifor", true);
        session.save(cni);

        Team team1 = new Team();
        team1.addPlayer(raz);
        team1.addPlayer(dst);
        session.save(team1);

        Tournament tournament = new Tournament("1", new Date(), TournamentType.SINGLE, TournamentFormats.getTournamentFormatsByName("3 teams"));
        session.save(tournament);

        ArrayList<User> participants = new ArrayList<>();
        participants.add(raz);
        participants.add(dst);
        participants.add(cni);
        tournament.setParticipants(participants);
        tournament.start(new ArrayList<>());
        for (Match m : tournament.getMatches()) {
          session.save(m);
        }

      }

    }.run();

    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        ArrayList<Tournament> allTournaments = tm.findAllTournaments();
        assertTrue(allTournaments != null);
        assertTrue(allTournaments.size() == 1);
      }

    }.run();

  }

}
