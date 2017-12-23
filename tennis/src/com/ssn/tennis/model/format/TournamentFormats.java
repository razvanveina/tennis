/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.format;

import java.util.ArrayList;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class TournamentFormats {
  private ArrayList<TournamentFormat> formats = new ArrayList<>();

  public TournamentFormats() {
    formats.add(new TournamentFormat6Teams());
    formats.add(new TournamentFormat5Teams());
    formats.add(new TournamentFormat4Teams());
  }

  public TournamentFormat getTournamentFormatByName(String name) {
    for (TournamentFormat tf : formats) {
      if (tf.getName().equals(name)) {
        return tf;
      }
    }
    return null;
  }

  public ArrayList<TournamentFormat> getFormats() {
    return formats;
  }

}
