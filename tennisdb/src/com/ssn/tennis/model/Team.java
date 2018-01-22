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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

@Entity
public class Team implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
  @SequenceGenerator(initialValue = 1, sequenceName = "seq_gen", name = "gen")
  private long id;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(name = "team_player", //
  joinColumns = { @JoinColumn(name = "team_id") }, //
  inverseJoinColumns = { @JoinColumn(name = "player_id") })
  private List<User> players = new ArrayList<User>();

  @ManyToMany(mappedBy = "teams")
  private List<Tournament> tournaments = new ArrayList<Tournament>();

  public Team() {

  }

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

  /**
   * @param part
   * @return
   */
  public boolean hasParticipants(ArrayList<User> part) {
    return this.players.equals(part);
  }

  public int getWon() {
    return FileDatabase.getInstance().getMatchesWonByTeam(this);
  }

  public int getLost() {
    return FileDatabase.getInstance().getMatchesLostByTeam(this);
  }

  public List<User> getPlayers() {
    return players;
  }

  public void setPlayers(ArrayList<User> players) {
    this.players = players;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the tournaments
   */
  public List<Tournament> getTournaments() {
    return tournaments;
  }

  /**
   * @param tournaments the tournaments to set
   */
  public void setTournaments(List<Tournament> tournaments) {
    this.tournaments = tournaments;
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

}
