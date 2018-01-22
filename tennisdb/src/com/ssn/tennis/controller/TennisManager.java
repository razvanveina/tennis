/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

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
    Query query = hibernateSession.createQuery("from User where user = :user");
    query.setParameter("user", user);
    List result = query.list();

    return (User) result.get(0);
  }

}
