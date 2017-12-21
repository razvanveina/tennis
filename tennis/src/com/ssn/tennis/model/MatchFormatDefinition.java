/*
 * Copyright (c) 2017 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.tennis.model;

import java.io.Serializable;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class MatchFormatDefinition implements Serializable {
  private static final long serialVersionUID = 1L;
  protected int number;

  public MatchFormatDefinition(int number) {
    super();
    this.number = number;
  }

}