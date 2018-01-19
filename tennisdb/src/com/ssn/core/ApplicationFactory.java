/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.core;

import com.ssn.tennis.model.Database;
import com.ssn.tennis.model.FileDatabase;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 */

public class ApplicationFactory {
  private static ApplicationFactory instance;

  private ApplicationFactory() {

  }

  public static ApplicationFactory getInstance() {
    if (instance == null) {
      instance = new ApplicationFactory();
    }
    return instance;
  }

  public Database getDatabase() {
    return FileDatabase.getInstance();
  }
}
