/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Team implements Serializable {
  private static final long serialVersionUID = 1L;
  private ArrayList<User> players = new ArrayList<User>();

  public boolean hasPlayer(String name) {
    for (User u : players) {
      if (u.hasName(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return players.toString().//
      replaceAll("[\\[\\]]", "").//
      replaceAll(", ", "/");
  }

  /**
   * @param user
   */
  public void addPlayer(User user) {
    players.add(user);
    Collections.sort(players, new Comparator<User>() {
      @Override
      public int compare(User o1, User o2) {
        return o1.getUser().compareTo(o2.getUser());
      }
    });
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((players == null) ? 0 : players.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Team other = (Team) obj;
    if (players == null) {
      if (other.players != null)
        return false;
    } else if (!players.equals(other.players))
      return false;
    return true;
  }

  /**
   * @param part
   * @return
   */
  public boolean hasParticipants(ArrayList<User> part) {
    return this.players.equals(part);
  }

  public int getWon() {
    return Database.getInstance().getMatchesWonByTeam(this);
  }

  public int getLost() {
    return Database.getInstance().getMatchesLostByTeam(this);
  }
}
