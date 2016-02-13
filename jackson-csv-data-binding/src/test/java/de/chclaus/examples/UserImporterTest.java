package de.chclaus.examples;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test for desearializing a list of users of a csv.
 *
 * @author Christian Claus (c.claus@micromata.de)
 */
public class UserImporterTest {

  @Test
  public void testImportUsers() throws URISyntaxException, IOException {
    Path path = Paths.get("src/test/resources/users.csv");
    List<User> users = UserImporter.importUsers(path.toUri().toURL());

    // Tests if there is really just one user inside the list.
    assertEquals(users.size(), 1);

    // Tests if the attributes of the import-user are correct.
    User user = users.get(0);
    assertEquals("famousUser", user.getUsername());
    assertEquals("secret", user.getPassword());
    assertEquals("famousUser@acme.com", user.getEmail());
  }
}