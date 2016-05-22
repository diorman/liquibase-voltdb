package liquibase.ext.voltdb.datatype;

import liquibase.datatype.LiquibaseDataType;
import liquibase.ext.voltdb.database.VoltDBDatabase;
import org.junit.Test;

import static org.junit.Assert.*;


public abstract class DataTypeVoltDBTest {
    private LiquibaseDataType type;
    private VoltDBDatabase db;

    public DataTypeVoltDBTest(LiquibaseDataType type) {
        this.type = type;
        this.db = new VoltDBDatabase();
    }

    protected LiquibaseDataType getType() {
        return this.type;
    }

    protected VoltDBDatabase getDB() { return db; }

    protected String getTypeSQL() {
        return getType().toDatabaseDataType(getDB()).toSql();
    }

    @Test
    public void supports() {
        assertTrue(getType().supports(getDB()));

        assertFalse(getType().supports(new liquibase.database.core.MySQLDatabase()));
    }

    @Test
    public void getPriority() {
        assertEquals(LiquibaseDataType.PRIORITY_DATABASE, getType().getPriority());
    }
}
