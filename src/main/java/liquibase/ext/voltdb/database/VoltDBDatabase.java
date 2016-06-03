package liquibase.ext.voltdb.database;

import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.DatabaseConnection;
import liquibase.exception.DatabaseException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class VoltDBDatabase extends AbstractJdbcDatabase {
    private static final String PRODUCT_NAME = "VoltDB";

    public VoltDBDatabase() {
        super.setCurrentDateTimeFunction("CURRENT_TIMESTAMP");
    }

    @Override
    protected String getDefaultDatabaseProductName() {
        return PRODUCT_NAME;
    }

    public boolean isCorrectDatabaseImplementation(DatabaseConnection conn) throws DatabaseException {
        return PRODUCT_NAME.equalsIgnoreCase(conn.getDatabaseProductName());
    }

    public String getDefaultDriver(String url) {
        if (url.startsWith("jdbc:voltdb:")) {
            return "org.voltdb.jdbc.Driver";
        }
        return null;
    }

    public String getShortName() {
        return PRODUCT_NAME.toLowerCase();
    }

    public Integer getDefaultPort() {
        return 21212;
    }

    public boolean supportsTablespaces() {
        return false;
    }

    public int getPriority() {
        return PRIORITY_DEFAULT;
    }

    public boolean supportsInitiallyDeferrableColumns() {
        return false;
    }

    @Override
    public boolean supportsSequences() {
        return false;
    }

    @Override
    public boolean supportsAutoIncrement() {
        return false;
    }

    @Override
    public boolean supportsSchemas() { return false; }

    @Override
    public boolean supportsDDLInTransaction() {
        return false;
    }

    @Override
    public int getDatabaseMajorVersion() throws DatabaseException {
        int majorVersion;

        // Hack to get rid of annoying message in org.voltdb.jdbc.JDBC4DatabaseMetaData#getDatabaseMajorVersion
        PrintStream defaultOut = System.out;

        PrintStream tmpOut = new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) throws IOException {}
        });

        System.setOut(tmpOut);

        try {
            try {
                majorVersion = super.getDatabaseMajorVersion();
            } finally {
                System.setOut(defaultOut);
            }
        } catch (DatabaseException e) {
            throw new DatabaseException(e);
        }

        return majorVersion;
    }
}
