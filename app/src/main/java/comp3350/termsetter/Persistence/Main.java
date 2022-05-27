package comp3350.termsetter.Persistence;

public class Main {
    private static String dbName="termsetterDB";
    private static boolean dbImported = false;

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

    public static void confirmImport() { dbImported = true; }

    public static boolean databaseIsImported() { return dbImported; }
}
