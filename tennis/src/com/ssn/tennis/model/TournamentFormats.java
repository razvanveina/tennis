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

public class TournamentFormats {
  private ArrayList<TournamentFormat> formats = new ArrayList<>();

  public TournamentFormats() {
    formats.add(new TournamentFormat6Teams());
  }

  public TournamentFormat getTournamentFormatByName(String name) {
    for (TournamentFormat tf : formats) {
      if (tf.getName().equals(name)) {
        return tf;
      }
    }
    return null;
  }
}