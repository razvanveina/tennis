/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ssn.tennis.model.Tournament;
import com.ssn.tennis.model.User;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TennisManager {
  private Session hibernateSession;

  public TennisManager(Session session) {
    this.hibernateSession = session;
  }

  public User findUserByUserName(String user) {
    Query query = hibernateSession.getNamedQuery(User.USER_BY_NAME);
    query.setParameter("user", user);
    List result = query.list();

    return (User) result.get(0);
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Tournament> findAllTournaments() {
    Query query = hibernateSession.getNamedQuery(Tournament.TOURNAMENT_ALL);
    List result = query.list();

    return (ArrayList<Tournament>) result;
  }

}
