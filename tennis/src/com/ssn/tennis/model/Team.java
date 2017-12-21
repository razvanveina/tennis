/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.util.ArrayList;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Team {
  private ArrayList<User> players = new ArrayList<User>();

  public boolean hasPlayer(String name) {
    for (User u : players) {
      if (u.hasName(name)) {
        return true;
      }
    }
    return false;
  }
}
