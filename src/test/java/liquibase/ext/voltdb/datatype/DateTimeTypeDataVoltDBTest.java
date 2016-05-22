package liquibase.ext.voltdb.datatype;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Tests for {@link DateTimeTypeVoltDB}
 */
public class DateTimeTypeDataVoltDBTest extends DataTypeVoltDBTest {
    public DateTimeTypeDataVoltDBTest() {
        super(new DateTimeTypeVoltDB());
    }

    @Test
    public void toDatabaseDataType() {
        assertEquals("TIMESTAMP", getTypeSQL());
    }
}
