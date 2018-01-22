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
    for (User u : getPlayers()) {
      if (u.hasName(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return getPlayers().toString().//
      replaceAll("[\\[\\]]", "").//
      replaceAll(", ", "/");
  }

  /**
   * @param user
   */
  public void addPlayer(User user) {
    getPlayers().add(user);
    Collections.sort(getPlayers(), new Comparator<User>() {
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
    result = prime * result + ((getPlayers() == null) ? 0 : getPlayers().hashCode());
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
    if (getPlayers() == null) {
      if (other.getPlayers() != null)
        return false;
    } else if (!getPlayers().equals(other.getPlayers()))
      return false;
    return true;
  }

  /**
   * @param part
   * @return
   */
  public boolean hasParticipants(ArrayList<User> part) {
    return this.getPlayers().equals(part);
  }

  public int getWon() {
    return Database.getInstance().getMatchesWonByTeam(this);
  }

  public int getLost() {
    return Database.getInstance().getMatchesLostByTeam(this);
  }

  /**
   * @return the players
   */
  public ArrayList<User> getPlayers() {
    return players;
  }

}
