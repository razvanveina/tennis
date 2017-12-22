/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.classification;

import java.util.ArrayList;

import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Team;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Classification {
  private ArrayList<ClassificationLine> cls = new ArrayList<ClassificationLine>();

  public ArrayList<ClassificationLine> getCls() {
    return cls;
  }

  /**
   * @param team
   */
  public void addTeam(Team team) {
    cls.add(new ClassificationLine(team));
  }

  /**
   * @param match
   */
  public void addMatch(Match match) {
    ClassificationLine cl1 = getClassificationLineByTeam(match.getTeam1());
    ClassificationLine cl2 = getClassificationLineByTeam(match.getTeam2());
    match.addToClassificationLines(cl1, cl2);
  }

  public ClassificationLine getClassificationLineByTeam(Team team) {
    for (ClassificationLine cl : cls) {
      if (cl.getTeam().equals(team)) {
        return cl;
      }
    }

    return null;
  }
}
