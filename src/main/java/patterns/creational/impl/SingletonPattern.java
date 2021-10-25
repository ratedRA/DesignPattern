package patterns.creational.impl;

import patterns.creational.CreationalPattern;

/**
 * The singleton pattern is a type of creational pattern that restricts the instantiation of a class to a single object.
 * This allows the class to create an instance of the class the first time it is instantiated.
 * However, on the next try, the existing instance of the class is returned. No new instance is created.
 *
 * #USAGES -
 * The singleton pattern is mostly used in cases where you want a single object to coordinate actions across a system.
 * Singletons are mostly used by:
 *
 * Services: services can be singleton since they store the state, configuration, and provide access to resources.
 * Therefore, it makes sense to have a single instance of a service in an application.
 *
 * Databases: when it comes to database connections, databases such as MongoDB utilize the singleton pattern.
 *
 * Configurations: if there is an object with a specific configuration,
 * you don’t need a new instance every time that configuration object is needed.
 */
public class SingletonPattern implements CreationalPattern {

    public static void main(String[] args) {
        DbCreds dbCreds = DbCreds.getInstance("root", "rootPassword");
        System.out.println(dbCreds.toString());

        DbCreds dbCreds12 = DbCreds.getInstance("root12", "rootPassword12");
        System.out.println(dbCreds.toString());
    }

    private static class DbCreds {

        private static DbCreds instance = null;

        private String dbUsername;
        private String dbPassword;

        private static DbCreds getInstance(String dbUsername, String dbPassword){
            if(instance == null){
                instance = new DbCreds();
                instance.setDbUsername(dbUsername);
                instance.setDbPassword(dbPassword);
            }
            return instance;
        }

        public String getDbUsername() {
            return dbUsername;
        }

        public void setDbUsername(String dbUsername) {
            this.dbUsername = dbUsername;
        }

        public String getDbPassword() {
            return dbPassword;
        }

        public void setDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
        }

        @Override
        public String toString() {
            return "DbCreds{" +
                    "dbUsername='" + dbUsername + '\'' +
                    ", dbPassword='" + dbPassword + '\'' +
                    '}';
        }
    }
}
