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
import java.util.function.Consumer;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
public class EAUtils {

  public static void handleLine(URL resource, Consumer<String> action) {
    try {
      URI uri = resource.toURI();
      List<String> strings = Files.readAllLines(Paths.get(uri));
      strings.stream().forEach(action::accept);

    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void executeSql(Connection conn, String sql, ResultSetConsumer action) {
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
          action.accept(results);
        }
      } catch (SQLException e) {
        // Handle exception. (i.e. some logging)
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
}
