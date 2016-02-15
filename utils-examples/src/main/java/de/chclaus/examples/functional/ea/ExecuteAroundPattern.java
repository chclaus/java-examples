package de.chclaus.examples.functional.ea;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Christian Claus (c.claus@micromata.de)
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




}
