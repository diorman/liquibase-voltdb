package liquibase.ext.voltdb.datatype;

import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.core.DateTimeType;
import liquibase.ext.voltdb.database.VoltDBDatabase;
import liquibase.database.Database;

public class DateTimeTypeVoltDB extends DateTimeType {
    @Override
    public boolean supports(Database database) {
        return database instanceof VoltDBDatabase;
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        if (database instanceof VoltDBDatabase) {
            return new DatabaseDataType("TIMESTAMP");
        }

        return super.toDatabaseDataType(database);
    }
}
