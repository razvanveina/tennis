/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package com.ssn.core.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author <a href="mailto:rveina@ssi-schaefer-noell.com">rveina</a>
 * @version $Revision: $, $Date: $, $Author: $
 * @param <T> 
 */
public abstract class WithSessionAndTransaction<T> {
  protected T returnValue;

  public T run() {
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();

    executeBusinessLogic(session);

    tx.commit();
    session.close();

    return returnValue;
  }

  protected abstract void executeBusinessLogic(Session session);

  protected void setReturnValue(T returnValue) {
    this.returnValue = returnValue;
  }
}
