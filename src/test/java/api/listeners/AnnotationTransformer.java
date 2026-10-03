package api.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
//		 Check if the test belongs to "Regression" group
//        String[] groups = annotation.getGroups();
//        for (String group : groups) {
//            if (group.equalsIgnoreCase("Regression")) {
//                annotation.setRetryAnalyzer(Retry.class);
//            }
//        }
		
		// Apply retry only if test method name contains "API"
//        if (testMethod.getName().contains("API")) {
//            annotation.setRetryAnalyzer(Retry.class);
//        }
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
