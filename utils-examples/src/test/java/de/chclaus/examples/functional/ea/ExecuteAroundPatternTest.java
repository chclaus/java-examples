package de.chclaus.examples.functional.ea;

import de.chclaus.examples.OutputStreamCapture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.net.URL;

/**
 * Tests if the classic method {@link ExecuteAroundPattern#handleFile(URL)}, which prints the content of a file
 * to System.out, does exactly the same as the new method {@link ExecuteAroundPattern#handleFileWithEA(URL)}.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
public class ExecuteAroundPatternTest {

  @Rule
  public OutputStreamCapture capture = new OutputStreamCapture();

  private URL resource;

  @Before
  public void setUp() throws Exception {
    resource = getClass().getResource("input.txt");
  }

  /**
   * Executes {@link ExecuteAroundPattern#handleFile(URL)} which reads and print a file. Subsequently
   * there is an assertion for the correct Console-Output.
   *
   * @throws Exception
   */
  @Test
  public void testHandleFile() throws Exception {
    new ExecuteAroundPattern().handleFile(resource);
    String s = capture.toString();
    Assert.assertEquals("foo\nbar\n", s);
  }

  /**
   * Executes {@link ExecuteAroundPattern#handleFile(URL)} which reads and print a file. Subsequently
   * there is an assertion for the correct Console-Output.
   *
   * @throws Exception
   */
  @Test
  public void testHandleFileWithEA() throws Exception {
    new ExecuteAroundPattern().handleFile(resource);
    String s = capture.toString();
    Assert.assertEquals("foo\nbar\n", s);
  }
}