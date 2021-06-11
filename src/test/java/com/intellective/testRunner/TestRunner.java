package com.intellective.testRunner;
import org.testng.TestNG;
public class TestRunner {
    static TestNG testNg;
    public static void main(String[] args){
//        BasicConfigurator.configure();
        testNg = new TestNG();
        testNg.setTestClasses(new Class[] {testNGReport.class});
        testNg.run();
    }
}
