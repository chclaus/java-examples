package de.chclaus.examples.functional.ea;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Christian Claus (c.claus@micromata.de)
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
}
