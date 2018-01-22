/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.matchdef;

import java.io.Serializable;

import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Tournament;
import com.ssn.tennis.model.enums.MatchType;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */
public abstract class MatchFormatDefinition implements Serializable {
  private static final long serialVersionUID = 1L;
  protected long id;

  protected int number;
  private MatchType type;

  public MatchFormatDefinition(int number, MatchType type) {
    super();
    this.number = number;
    this.type = type;
  }

  public MatchType getType() {
    return type;
  }

  public int getNumber() {
    return number;
  }

  public boolean isGroupMatch(String group) {
    return false;
  }

  public String getStageInfo() {
    return type.name();
  }

  public abstract Match createMatch(int number, Tournament tournament);

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }
}
