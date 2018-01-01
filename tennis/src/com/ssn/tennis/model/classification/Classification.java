/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model.classification;

import java.util.ArrayList;
import java.util.Collections;

import com.ssn.tennis.model.Match;
import com.ssn.tennis.model.Team;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class Classification {
  private ArrayList<ClassificationLine> cls = new ArrayList<ClassificationLine>();
  private ArrayList<Match> matches = new ArrayList<>();

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
    if (cl1 == null) {
      cls.add(cl1 = new ClassificationLine(match.getTeam1()));
    }
    ClassificationLine cl2 = getClassificationLineByTeam(match.getTeam2());
    if (cl2 == null) {
      cls.add(cl2 = new ClassificationLine(match.getTeam2()));
    }
    match.addToClassificationLines(cl1, cl2);
    matches.add(match);
  }

  public ClassificationLine getClassificationLineByTeam(Team team) {
    for (ClassificationLine cl : cls) {
      if (cl.getTeam().equals(team)) {
        return cl;
      }
    }

    return null;
  }

  public void sort() {
    Collections.sort(cls);

    int howManyTeamsWithEqualPoints = getHowManyTeamsWithEqualPoints();
    if (howManyTeamsWithEqualPoints > 1) {
      resort(howManyTeamsWithEqualPoints);
    }
  }

  public void simpleSort() {
    Collections.sort(cls);

  }

  private void resort(int howManyTeamsWithEqualPoints) {
    ArrayList<Team> teams = new ArrayList<Team>();

    for (int i = 0; i < howManyTeamsWithEqualPoints; i++) {
      teams.add(cls.get(i).getTeam());
    }

    Classification temp = new Classification();

    for (Match match : matches) {
      if (match.isBetweenTeams(teams)) {
        temp.addMatch(match);
      }
    }

    temp.simpleSort();

    ArrayList<ClassificationLine> copy = new ArrayList<>(cls);
    int counter = 0;
    for (ClassificationLine cl : temp.cls) {
      ClassificationLine classificationLineByTeam = getClassificationLineByTeam(cl.getTeam());
      copy.set(counter++, classificationLineByTeam);
    }

    cls = copy;

  }

  /**
   * @return
   */
  private int getHowManyTeamsWithEqualPoints() {
    if (cls.size() == 0) {
      return 1;
    }
    int counter = 1;
    ClassificationLine first = cls.get(0);
    for (int i = 1; i < cls.size(); i++) {
      if (first.getWon() == cls.get(i).getWon()) {
        counter++;
      }
    }
    return counter;
  }

}
