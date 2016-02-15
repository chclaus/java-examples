package de.chclaus.examples.functional.ea;

import org.junit.Test;

/**
 * @author Christian Claus (c.claus@micromata.de)
 */
public class ExecuteAroundPatternTest {

  @Test
  public void testHandleFile() throws Exception {
    new ExecuteAroundPattern().handleFile(getClass().getResource("input.txt"));
  }

  @Test
  public void testHandleFileWithEA() throws Exception {
    new ExecuteAroundPattern().handleFile(getClass().getResource("input.txt"));
  }
}