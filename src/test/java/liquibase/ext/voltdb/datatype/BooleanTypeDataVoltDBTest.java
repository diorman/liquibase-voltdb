package liquibase.ext.voltdb.datatype;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests for {@link BooleanTypeVoltDB}
 */
public class BooleanTypeDataVoltDBTest extends DataTypeVoltDBTest {
    public BooleanTypeDataVoltDBTest() {
        super(new BooleanTypeVoltDB());
    }

    @Test
    public void toDatabaseDataType() {
        assertEquals("TINYINT", getTypeSQL());
    }
}
