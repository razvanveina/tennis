/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Tournament {
  private String name;
  private Date startDate;
  private ArrayList<User> participants = new ArrayList<User>();
  private TournamentType type = TournamentType.DOUBLE;
  private ArrayList<Team> teams = new ArrayList<Team>();

  public Tournament(String name, Date date, TournamentType type) {
    this.name = name;
    startDate = date;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public ArrayList<User> getParticipants() {
    return participants;
  }

  public void setParticipants(ArrayList<User> participants) {
    this.participants = participants;
  }

  public TournamentType getType() {
    return type;
  }

  public void setType(TournamentType type) {
    this.type = type;
  }

  public ArrayList<Team> getTeams() {
    return teams;
  }

  public void setTeams(ArrayList<Team> teams) {
    this.teams = teams;
  }

}
