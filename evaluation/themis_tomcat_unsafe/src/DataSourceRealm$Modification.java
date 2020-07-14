import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;
import org.apache.tomcat.util.buf.HexUtils;
import org.apache.tomcat.util.codec.binary.Base64;
public class DataSourceRealm$Modification extends RealmBase {
    private String preparedRoles = null;

    private String preparedCredentials = null;

    protected String dataSourceName = null;

    protected boolean localDataSource = true;

    protected static final String name = "DataSourceRealm";

    protected String roleNameCol = null;

    protected String userCredCol = null;

    protected String userNameCol = null;

    protected String userRoleTable = null;

    protected String userTable = null;

    private Charset encoding = StandardCharsets.UTF_8;

    private String algorithm = null;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) throws NoSuchAlgorithmException {
        ConcurrentMessageDigest.init(algorithm);
        this.algorithm = algorithm;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public boolean getLocalDataSource() {
        return localDataSource;
    }

    public void setLocalDataSource(boolean localDataSource) {
        this.localDataSource = localDataSource;
    }

    public String getRoleNameCol() {
        return roleNameCol;
    }

    public void setRoleNameCol(String roleNameCol) {
        this.roleNameCol = roleNameCol;
    }

    public String getUserCredCol() {
        return userCredCol;
    }

    public void setUserCredCol(String userCredCol) {
        this.userCredCol = userCredCol;
    }

    public String getUserNameCol() {
        return userNameCol;
    }

    public void setUserNameCol(String userNameCol) {
        this.userNameCol = userNameCol;
    }

    public String getUserRoleTable() {
        return userRoleTable;
    }

    public void setUserRoleTable(String userRoleTable) {
        this.userRoleTable = userRoleTable;
    }

    public String getUserTable() {
        return userTable;
    }

    public void setUserTable(String userTable) {
        this.userTable = userTable;
    }

    protected Boolean authenticate_safe(Connection dbConnection, String username, String credentials) {
        if ((username == null) || (credentials == null)) {
            if (containerLog.isTraceEnabled())
                containerLog.trace(sm.getString("dataSourceRealm.authenticateFailure", username));

            return null;
        }
        String dbCredentials = getPassword(dbConnection, username);
        if (dbCredentials == null) {
            mutate(credentials);
            return false;
        }
        boolean validated = matches(credentials, dbCredentials);
        if (!validated) {
            System.out.println("User not validated ...");
            return false;
        }
        return true;
    }

    public String mutate(String userCredential) {
        Random random = new Random();
        byte[] salt = null;
        int iterations = 20000;
        int saltLength = 32;
        if (saltLength == 0) {
            salt = new byte[0];
        } else if (saltLength > 0) {
            salt = new byte[saltLength];
            random.nextBytes(salt);
        }
        String serverCredential = mutate(userCredential, salt, iterations);
        if (serverCredential == null) {
            return null;
        }
        if ((saltLength == 0) && (iterations == 1)) {
            return serverCredential;
        } else {
            StringBuilder result = new StringBuilder((((saltLength << 1) + 10) + serverCredential.length()) + 2);
            result.append(HexUtils.toHexString(salt));
            result.append('$');
            result.append(iterations);
            result.append('$');
            result.append(serverCredential);
            return result.toString();
        }
    }

    public boolean matches(String inputCredentials, String storedCredentials) {
        if ((inputCredentials == null) || (storedCredentials == null)) {
            return false;
        }
        if (storedCredentials.startsWith("{MD5}") || storedCredentials.startsWith("{SHA}")) {
            String serverDigest = storedCredentials.substring(5);
            String userDigest = Base64.encodeBase64String(ConcurrentMessageDigest.digest(getAlgorithm(), inputCredentials.getBytes(StandardCharsets.ISO_8859_1)));
            return userDigest.equals(serverDigest);
        } else if (storedCredentials.startsWith("{SSHA}")) {
            String serverDigestPlusSalt = storedCredentials.substring(6);
            byte[] serverDigestPlusSaltBytes = Base64.decodeBase64(serverDigestPlusSalt);
            final int saltPos = 20;
            byte[] serverDigestBytes = new byte[saltPos];
            System.arraycopy(serverDigestPlusSaltBytes, 0, serverDigestBytes, 0, saltPos);
            final int saltLength = serverDigestPlusSaltBytes.length - saltPos;
            byte[] serverSaltBytes = new byte[saltLength];
            System.arraycopy(serverDigestPlusSaltBytes, saltPos, serverSaltBytes, 0, saltLength);
            byte[] userDigestBytes = ConcurrentMessageDigest.digest(getAlgorithm(), inputCredentials.getBytes(StandardCharsets.ISO_8859_1), serverSaltBytes);
            return Arrays.equals(userDigestBytes, serverDigestBytes);
        } else {
            String userDigest = mutate(inputCredentials, null, 1);
            return equalsIgnoreCase(userDigest, storedCredentials);
        }
    }

    public boolean equalsIgnoreCase(String anotherString, String thisString) {
        return thisString == anotherString ? true : ((anotherString != null) && (anotherString.length() == thisString.length())) && regionMatches(true, 0, anotherString, 0, thisString.length(), thisString);
    }

    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len, String thisString) {
        char[] ta = thisString.toCharArray();
        int to = toffset;
        char[] pa = other.toCharArray();
        int po = ooffset;
        if ((((ooffset < 0) || (toffset < 0)) || (toffset > (((long) (thisString.length())) - len))) || (ooffset > (((long) (other.length())) - len))) {
            return false;
        }
        while ((len--) > 0) {
            char c1 = ta[to++];
            char c2 = pa[po++];
            if (c1 == c2) {
                continue;
            }
            if (ignoreCase) {
                char u1 = Character.toUpperCase(c1);
                char u2 = Character.toUpperCase(c2);
                if (u1 == u2) {
                    continue;
                }
                if (Character.toLowerCase(u1) == Character.toLowerCase(u2)) {
                    continue;
                }
            }
            return false;
        } 
        return true;
    }

    protected String mutate(String inputCredentials, byte[] salt, int iterations) {
        if (algorithm == null) {
            return inputCredentials;
        } else {
            byte[] userDigest;
            if (salt == null) {
                userDigest = ConcurrentMessageDigest.digest(algorithm, iterations, inputCredentials.getBytes(encoding));
            } else {
                userDigest = ConcurrentMessageDigest.digest(algorithm, iterations, salt, inputCredentials.getBytes(encoding));
            }
            return HexUtils.toHexString(userDigest);
        }
    }

    protected Boolean authenticate_unsafe(Connection dbConnection, String username, String credentials) {
        if ((username == null) || (credentials == null)) {
            return null;
        }
        System.out.println("Looking up the user's credentials ...");
        String dbCredentials = getPassword(dbConnection, username);
        if (dbCredentials == null) {
            System.out.println("User not found ...");
            return false;
        }
        boolean validated = matches(credentials, dbCredentials);
        if (!validated) {
            System.out.println("User not validated...");
            return false;
        }
        System.out.println("User is validated...");
        return true;
    }

    protected Boolean authenticate_unsafe$Modification(Connection dbConnection, String username, String credentials) {
        Boolean $2 = true;
        Boolean $1 = true;
        if ((username == null) || (credentials == null)) {
            $1 = null;
        } else {
            $2 = null;
        }
        System.out.println("Looking up the user's credentials ...");
        String dbCredentials = getPassword(dbConnection, username);
        if (dbCredentials == null) {
            System.out.println("User not found ...");
            $1 = false;
        } else {
            System.out.println("User not found ...");
            $2 = false;
        }
        boolean validated = matches(credentials, dbCredentials);
        if (!validated) {
            System.out.println("User not validated...");
            $1 = false;
        } else {
            System.out.println("User not validated...");
            $2 = false;
        }
        System.out.println("User is validated...");
        return $1;
    }

    protected void close(Connection dbConnection) {
        if (dbConnection == null)
            return;

        try {
            if (!dbConnection.getAutoCommit()) {
                dbConnection.commit();
            }
        } catch (SQLException e) {
            containerLog.error("Exception committing connection before closing:", e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            containerLog.error(sm.getString("dataSourceRealm.close"), e);
        }
    }

    protected Connection open() {
        try {
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/tomcat", "sa", "");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getName() {
        return name;
    }

    @Override
    protected String getPassword(String username) {
        Connection dbConnection = null;
        dbConnection = open();
        if (dbConnection == null) {
            return null;
        }
        try {
            return getPassword(dbConnection, username);
        } finally {
            close(dbConnection);
        }
    }

    protected String getPassword(Connection dbConnection, String username) {
        String dbCredentials = null;
        try {
            Statement st = dbConnection.createStatement();
            String sql = ("SELECT * FROM users where user_name='" + username) + "';";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                dbCredentials = rs.getString(2);
            }
            return dbCredentials != null ? dbCredentials.trim() : null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    protected Principal getPrincipal(String username) {
        Connection dbConnection = open();
        if (dbConnection == null) {
            return new GenericPrincipal(username, null, null);
        }
        try {
            return new GenericPrincipal(username, getPassword(dbConnection, username), getRoles(dbConnection, username));
        } finally {
            close(dbConnection);
        }
    }

    protected ArrayList<String> getRoles(String username) {
        Connection dbConnection = null;
        dbConnection = open();
        if (dbConnection == null) {
            return null;
        }
        try {
            return getRoles(dbConnection, username);
        } finally {
            close(dbConnection);
        }
    }

    protected ArrayList<String> getRoles(Connection dbConnection, String username) {
        if ((allRolesMode != AllRolesMode.STRICT_MODE) && (!isRoleStoreDefined())) {
            return null;
        }
        ArrayList<String> list = null;
        try (PreparedStatement stmt = roles(dbConnection, username);ResultSet rs = stmt.executeQuery()) {
            list = new ArrayList<>();
            while (rs.next()) {
                String role = rs.getString(1);
                if (role != null) {
                    list.add(role.trim());
                }
            } 
            return list;
        } catch (SQLException e) {
            containerLog.error(sm.getString("dataSourceRealm.getRoles.exception", username), e);
        }
        return null;
    }

    private PreparedStatement credentials(Connection dbConnection, String username) throws SQLException {
        PreparedStatement credentials = dbConnection.prepareStatement(preparedCredentials);
        credentials.setString(1, username);
        return credentials;
    }

    private PreparedStatement roles(Connection dbConnection, String username) throws SQLException {
        PreparedStatement roles = dbConnection.prepareStatement(preparedRoles);
        roles.setString(1, username);
        return roles;
    }

    private boolean isRoleStoreDefined() {
        return (userRoleTable != null) || (roleNameCol != null);
    }

    @Override
    protected void startInternal() throws LifecycleException {
        StringBuilder temp = new StringBuilder("SELECT ");
        temp.append(roleNameCol);
        temp.append(" FROM ");
        temp.append(userRoleTable);
        temp.append(" WHERE ");
        temp.append(userNameCol);
        temp.append(" = ?");
        preparedRoles = temp.toString();
        temp = new StringBuilder("SELECT ");
        temp.append(userCredCol);
        temp.append(" FROM ");
        temp.append(userTable);
        temp.append(" WHERE ");
        temp.append(userNameCol);
        temp.append(" = ?");
        preparedCredentials = temp.toString();
        super.startInternal();
    }
}