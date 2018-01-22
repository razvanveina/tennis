/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.db;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.ssn.core.persistence.WithSessionAndTransaction;
import com.ssn.tennis.controller.TennisManager;
import com.ssn.tennis.model.Team;
import com.ssn.tennis.model.User;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TeamTest {

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
      }

    }.run();

    new WithSessionAndTransaction<User>() {

      @Override
      protected void executeBusinessLogic(Session session) {
        TennisManager tm = new TennisManager(session);
        User user = tm.findUserByUserName("dst");
        assertEquals(1, user.getTeams().size());
        Team team = user.getTeams().get(0);
        assertEquals(2, team.getPlayers().size());
      }

    }.run();

  }

}
