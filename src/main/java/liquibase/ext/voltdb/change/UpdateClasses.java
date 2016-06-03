package liquibase.ext.voltdb.change;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.database.Database;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.logging.LogFactory;
import liquibase.resource.ResourceAccessor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;

public class UpdateClasses implements CustomTaskChange {
    private String jarFile;
    private String classSelector;

    public String getConfirmationMessage() {
        return "@UpdateClasses was executed successfully: " + getCallDesc();
    }

    public void setUp() throws SetupException {}

    public void setFileOpener(ResourceAccessor resourceAccessor) {}

    public ValidationErrors validate(Database database) {
        return null;
    }

    public String getJarFile() {
        return jarFile;
    }

    public void setJarFile(String jarFile) {
        this.jarFile = jarFile;
    }

    public String getClassSelector() {
        return classSelector;
    }

    public void setClassSelector(String classSelector) {
        this.classSelector = classSelector;
    }

    private String getCall() {
        return "{call @UpdateClasses(?, ?)}";
    }

    private String getCallDesc() {
        String jarFile = getJarFile() == null ? "NULL" : getJarFile();
        String classSelector = getClassSelector() == null ? "\"\"" : getClassSelector();

        return "@UpdateClasses(" + jarFile + ", " + classSelector + ")";
    }

    public void execute(Database database) throws CustomChangeException {
        JdbcConnection con = (JdbcConnection)database.getConnection();
        try {
            CallableStatement proc = con.getUnderlyingConnection().prepareCall(getCall());

            if (getJarFile() == null) {
                proc.setNull(1, Types.ARRAY);
                proc.setString(2, getClassSelector());
            } else {
                proc.setBytes(1, Files.readAllBytes(Paths.get(getJarFile())));
                proc.setString(2, "");
            }

            proc.executeQuery();
        } catch (SQLException e){
            LogFactory.getInstance().getLog().warning(e.getMessage());
            throw new CustomChangeException("Failed to exec @UpdateClasses: " + getCallDesc());
        } catch (IOException e) {
            LogFactory.getInstance().getLog().warning(e.getMessage());
            throw new CustomChangeException("Failed to exec @UpdateClasses: " + getCallDesc());
        }
    }
}
