package listeners;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.Listeners;

// @Listeners(listeners.TestNGListeners.class)  //The listener class
public class TestNGListenerDemo2 {

	@Test
	public void test1() {
		System.out.println("This is my test 1.");
	}
	
	@Test
	public void test2() {
		System.out.println("This is my test 2.");
	//	AssertJUnit.assertTrue(false);
	}
	
	@Test
	public void test3() {
		System.out.println("This is my test 3.");
		throw new SkipException("This test is skipped.");
	}
	
}
