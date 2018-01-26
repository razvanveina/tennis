/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.format;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public enum TournamentFormats {
    TF8(new TournamentFormat8Teams()), //
    TF7(new TournamentFormat7Teams()), //
    TF6(new TournamentFormat6Teams()), // 
    TF5(new TournamentFormat5Teams()), //
    TF4(new TournamentFormat4Teams()), //
    TF4_2(new TournamentFormat4Teams2()), //
    TF3(new TournamentFormat3Teams()); //

  private TournamentFormat tf;

  private TournamentFormats(TournamentFormat tf) {
    this.tf = tf;
  }

  public static TournamentFormat getTournamentFormatByName(String name) {
    for (TournamentFormats tf1 : TournamentFormats.values()) {
      if (tf1.getTournamentFormat().getName().equals(name)) {
        return tf1.getTournamentFormat();
      }
    }
    return null;
  }

  public TournamentFormat getTournamentFormat() {
    return tf;
  }

  public static TournamentFormats getTournamentFormatsByName(String name) {
    for (TournamentFormats tf1 : TournamentFormats.values()) {
      if (tf1.getTournamentFormat().getName().equals(name)) {
        return tf1;
      }
    }
    return null;
  }

}
