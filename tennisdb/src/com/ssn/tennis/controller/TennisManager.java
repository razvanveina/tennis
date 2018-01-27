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

import com.ssn.tennis.common.Utils;
import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Team;
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

    if (result.size() > 0) {
      User user2 = (User) result.get(0);
      List<Tournament> tournaments = user2.getTournaments();
      for (Tournament tournament : tournaments) {
        tournament.getMatches().toString();
      }

      return user2;
    }

    return null;
  }

  public User findUserByUserNameAndPassword(String user, String pass) {
    Query query = hibernateSession.getNamedQuery(User.USER_BY_NAME_AND_PASS);
    query.setParameter("user", user);
    query.setParameter("pass", Utils.encrypt(pass));
    List result = query.list();

    if (result.size() == 0) {
      return null;
    }

    User user2 = (User) result.get(0);
    List<Tournament> tournaments = user2.getTournaments();
    for (Tournament tournament : tournaments) {
      tournament.getMatches().toString();
    }

    return user2;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Tournament> findAllTournaments() {
    Query query = hibernateSession.getNamedQuery(Tournament.TOURNAMENT_ALL);
    List result = query.list();

    return (ArrayList<Tournament>) result;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Team> findAllTeams() {
    Query query = hibernateSession.getNamedQuery(Team.QUERY_ALL);
    List result = query.list();

    return (ArrayList<Team>) result;
  }

  public Tournament findTournamentByName(String name) {
    Query query = hibernateSession.getNamedQuery(Tournament.TOURNAMENT_BY_NAME);
    query.setParameter("name", name);
    List result = query.list();

    return result.size() > 0 ? (Tournament) result.get(0) : null;
  }

  public Match readMatchById(long id) {
    Query query = hibernateSession.getNamedQuery(Match.QUERY_BY_ID);
    query.setParameter("id", id);
    List result = query.list();

    return result.size() > 0 ? (Match) result.get(0) : null;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<User> findAllUsers() {
    Query query = hibernateSession.getNamedQuery(User.USER_ALL);
    List result = query.list();

    for (User u : (List<User>) result) {
      List<Tournament> tournaments = u.getTournaments();
      for (Tournament tournament : tournaments) {
        tournament.getMatches().toString();
      }
    }

    return (ArrayList<User>) result;
  }

  public Team readTeamById(long id) {
    Query query = hibernateSession.getNamedQuery(Team.QUERY_BY_ID);
    query.setParameter("id", id);
    List result = query.list();

    return result.size() > 0 ? (Team) result.get(0) : null;
  }

}
