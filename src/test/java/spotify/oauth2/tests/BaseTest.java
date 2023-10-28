package spotify.oauth2.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	@BeforeMethod
	public void beforeMethod(Method m)
	{
		System.out.println("The Method is :" + m.getName());
		System.out.println("The Thread ID is:" +Thread.currentThread().getId());
	}

}
