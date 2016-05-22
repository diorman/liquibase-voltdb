package liquibase.ext.voltdb.database;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for {@link VoltDBDatabase}
 */
public class VoltDBDatabaseTest {
    protected VoltDBDatabase database;

    public VoltDBDatabaseTest() throws Exception {
        this.database = new VoltDBDatabase();
    }

    protected VoltDBDatabase getDatabase() {
        return database;
    }

    @Test
    public void getCurrentDateTimeFunction() {
        assertEquals(getDatabase().getCurrentDateTimeFunction(), "CURRENT_TIMESTAMP");
    }

    @Test
    public void getDatabaseProductName() {
        assertEquals("VoltDB", getDatabase().getDatabaseProductName());
    }

    @Test
    public void getDefaultDriver() {
        assertEquals("org.voltdb.jdbc.Driver", getDatabase().getDefaultDriver("jdbc:voltdb://localhost:21212"));

        assertNull(getDatabase().getDefaultDriver("jdbc:mysql://localhost/liquibase"));
    }

    @Test
    public void getShortName() {
        assertEquals("voltdb", getDatabase().getShortName());
    }

    @Test
    public void getDefaultPort() {
        assertEquals(new Integer(21212), getDatabase().getDefaultPort());
    }

    @Test
    public void supportsTablespaces() {
        assertFalse(getDatabase().supportsTablespaces());
    }

    @Test
    public void supportsInitiallyDeferrableColumns() {
        assertFalse(getDatabase().supportsInitiallyDeferrableColumns());
    }

    @Test
    public void supportsSequences() {
        assertFalse(getDatabase().supportsSequences());
    }

    @Test
    public void supportsAutoIncrement() {
        assertFalse(getDatabase().supportsAutoIncrement());
    }

    @Test
    public void supportsSchemas() {
        assertFalse(getDatabase().supportsSchemas());
    }

    @Test
    public void supportsDDLInTransaction(){
        assertFalse(getDatabase().supportsDDLInTransaction());
    }
}
