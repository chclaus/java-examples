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
 * @author Christian Claus (ch.claus@me.com)
 */
public class UserImporterTest {

  @Test
  public void testImportUsersWithJackson() throws URISyntaxException, IOException {
    Path path = Paths.get("src/test/resources/users.csv");
    List<User> users = UserImporter.importUsersWithJackson(path.toUri().toURL());
    checkList(users);
  }

  @Test
  public void testImportUsersWithApacheCsv() throws URISyntaxException, IOException {
    Path path = Paths.get("src/test/resources/users.csv");
    List<User> users = UserImporter.importUsersWithApacheCsv(path.toUri().toURL());
    checkList(users);
  }

  private void checkList(List<User> users) {
    // Tests if there are really two users inside the list.
    assertEquals(users.size(), 2);

    // Tests if the attributes of the import-user are correct.
    User famousUser = users.get(0);
    assertEquals("famousUser", famousUser.getUsername());
    assertEquals("secret", famousUser.getPassword());
    assertEquals("famousUser@acme.com", famousUser.getEmail());

    User anotherUser = users.get(1);
    assertEquals("anotherUser", anotherUser.getUsername());
    assertEquals("withAnotherSecret", anotherUser.getPassword());
    assertEquals("andAnIncredibleEmailAddy@acme.com", anotherUser.getEmail());
  }
}