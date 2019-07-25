package listeners;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.Listeners;

// @Listeners(listeners.TestNGListeners.class)  //The listener class
public class TestNGListenerDemo {

	@Test
	public void testFirst() {
		System.out.println("This is my fist test.");
	}
	
	@Test
	public void testSecond() {
		System.out.println("This is my second test.");
		AssertJUnit.assertTrue(false);
	}
	
	@Test
	public void testThird() {
		System.out.println("This is my third test.");
		throw new SkipException("This test is skipped.");
	}
	
}
