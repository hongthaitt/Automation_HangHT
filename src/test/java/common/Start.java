//package common;
//
//import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
//import net.thucydides.core.util.EnvironmentVariables;
//import net.thucydides.core.util.SystemEnvironmentVariables;
//
//public class Start {
//    private static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
//
//    public static String getProperty(String key) {
//        try {
//            return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
//        } catch (Exception e) {
//            return "";
//        }
//    }
//}
