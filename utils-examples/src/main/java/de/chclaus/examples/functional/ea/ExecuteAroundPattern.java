package de.chclaus.examples.functional.ea;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
public class ExecuteAroundPattern {

  /**
   * Version of a file output without execute-around-pattern but
   * streams and method references.
   *
   * @param resource the file resource to print.
   */
  public void handleFile(URL resource) {
    try {
      URI uri = resource.toURI();
      List<String> strings = Files.readAllLines(Paths.get(uri));
      strings.stream().forEach(System.out::println);

    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * New version with execute-around-pattern of a file output.
   *
   * @param resource the file resource to print.
   */
  public void handleFileWithEA(URL resource) {
    EAUtils.handleLine(resource, System.out::println);
  }

  /**
   * Just a further and more complex demonstration <b>without</b> the execute-around-pattern,
   * used by <a href="http://chclaus.de/2016/02/17/java8-execute-around">chclaus.de</a>
   *
   * @param conn the current sql connection.
   * @param sql the sql which should be executed.
   */
  public void executeSql(Connection conn, String sql) {
    PreparedStatement statement = null;
    try {
      statement = conn.prepareStatement(sql);
    } catch (SQLException e) {
      // Handle exception. (i.e. some logging)
    }

    try {
      ResultSet results = statement.executeQuery();
      try {
        while (results.next()) {
          // Here is the important code for us. Just this one line - the rest is boilerplate.
          System.out.println(results.getString(0) + ", " + results.getString(1));
        }
      } finally {
        results.close();
      }
    } catch (SQLException e) {
      // Handle exception. (i.e. some logging)
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e) {
        // Handle exception. (i.e. some logging)
      }
    }
  }

  /**
   * Just a further and more complex demonstration <b>of</b> the execute-around-pattern,
   * used by <a href="http://chclaus.de/2016/02/17/java8-execute-around">chclaus.de</a>
   *
   * @param conn the current sql connection.
   * @param sql the sql which should be executed.
   */
  public void executeSqlWithEA(Connection conn, String sql) {
    EAUtils.executeSql(conn, sql, rs -> {
      // Here is the important code for us. And it is just one line. :)
      System.out.println(rs.getString(0) + ", " + rs.getString(1));
    });

  }





}
