package de.chclaus.examples.functional.ea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A simple consumer which throws a SQLException so that it's not necessary to
 * handle the exception in a call of {@link ExecuteAroundPattern#executeSqlWithEA(Connection, String)}.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@FunctionalInterface
public interface ResultSetConsumer {

  void accept(ResultSet rs) throws SQLException;
}
