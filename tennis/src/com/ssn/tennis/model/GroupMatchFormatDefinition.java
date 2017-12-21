/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class GroupMatchFormatDefinition extends MatchFormatDefinition {

  private static final long serialVersionUID = 1L;
  private String group;
  private int team1;
  private int team2;

  public GroupMatchFormatDefinition(int number, String group, int team1, int team2) {
    super(number);
    this.group = group;
    this.team1 = team1;
    this.team2 = team2;
  }
}
