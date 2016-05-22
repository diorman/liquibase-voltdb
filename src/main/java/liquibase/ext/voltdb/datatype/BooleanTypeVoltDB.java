package liquibase.ext.voltdb.datatype;

import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.core.BooleanType;
import liquibase.ext.voltdb.database.VoltDBDatabase;
import liquibase.database.Database;

public class BooleanTypeVoltDB extends BooleanType {
    @Override
    public int getPriority() {
        return PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(Database database) {
        return database instanceof VoltDBDatabase;
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        if (database instanceof VoltDBDatabase) {
            return new DatabaseDataType("TINYINT");
        }

        return super.toDatabaseDataType(database);
    }

    @Override
    protected boolean isNumericBoolean(Database database) {
        return database instanceof VoltDBDatabase || super.isNumericBoolean(database);
    }
}
