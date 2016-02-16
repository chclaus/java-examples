package de.chclaus.examples;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Captures the System.out-Printstream and offers it's content as String.
 *
 * Inspireded by a Spring-Rule, but just the out- and not the err-Stream.
 *
 * @author Christian Claus (c.claus@micromata.de)
 * @see <a href="https://github.com/spring-projects/spring-boot/blob/master/spring-boot/src/main/java/org/springframework/boot/test/OutputCapture.java">OutputCapture</a>
 */
public class OutputStreamCapture implements TestRule {

  // Preserved original PrintStream to restore the original one after evaluation.
  private PrintStream original;

  // The captured output.
  private final ByteArrayOutputStream outputCaptureStream = new ByteArrayOutputStream();

  @Override
  public Statement apply(Statement statement, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        try {
          captureOutputStream();

          // Evaluates the test-method.
          statement.evaluate();

        } finally {
          releaseOutputStream();
        }
      }
    };
  }

  /**
   * Preserves the original PrintStream and sets the new System.out-PrintStream.
   */
  private void captureOutputStream() {
    original = System.out;
    System.setOut(new PrintStream(outputCaptureStream));
  }

  /**
   * Sets System.out to the preserved PrintStream.
   */
  private void releaseOutputStream() {
    System.setOut(original);
  }

  @Override
  public String toString() {
    try {
      outputCaptureStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return outputCaptureStream.toString();
  }
}
