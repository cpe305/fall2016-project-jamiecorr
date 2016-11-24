package edu.calpoly.cpe305.test;

import edu.calpoly.cpe305.MyOwnRuntimeException;
import org.junit.Test;

public class TestException {
  @Test(expected = MyOwnRuntimeException.class)
  public void testMyOwnRuntimeException() throws MyOwnRuntimeException {
    throw new MyOwnRuntimeException("**thowing exception**");
  }
}
