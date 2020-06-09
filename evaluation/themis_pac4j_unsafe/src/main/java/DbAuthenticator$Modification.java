import Pac4jConstants.PASSWORD;
import Pac4jConstants.USERNAME;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.AbstractUsernamePasswordAuthenticator;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.sql.profile.DbProfile;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DbAuthenticator$Modification extends AbstractUsernamePasswordAuthenticator {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected DBI dbi;

    protected DataSource dataSource;

    protected String attributes = "";

    protected String startQuery = "select username, password";

    protected String endQuery = " from users where username = :username";

    public void validate(UsernamePasswordCredentials arg0, WebContext arg1) throws HttpAction {
        validate_safe(arg0);
    }

    public void validate_safe(UsernamePasswordCredentials credentials) throws HttpAction {
        Handle h = null;
        try {
            h = dbi.open();
            final String username = credentials.getUsername();
            final String query;
            if (CommonHelper.isNotBlank(attributes)) {
                query = ((startQuery + ", ") + attributes) + endQuery;
            } else {
                query = startQuery + endQuery;
            }
            final List<Map<String, Object>> results = h.createQuery(query).bind(USERNAME, username).list(2);
            final String expectedPassword = getPasswordEncoder().encode(credentials.getPassword());
            if ((results == null) || results.isEmpty()) {
                throw new AccountNotFoundException("No account found for: " + username);
            } else if (results.size() > 1) {
                throw new MultipleAccountsFoundException("Too many accounts found for: " + username);
            } else {
                final Map<String, Object> result = results.get(0);
                final String returnedPassword = ((String) (result.get(PASSWORD)));
                if (CommonHelper.areNotEquals(returnedPassword, expectedPassword)) {
                    throw new BadCredentialsException("Bad credentials for: " + username);
                } else {
                    final DbProfile profile = createProfile(username, attributes.split(","), result);
                    credentials.setUserProfile(profile);
                }
            }
        } catch (final TechnicalException e) {
            logger.debug("Authentication error", e);
            throw e;
        } catch (final RuntimeException e) {
            throw new TechnicalException("Cannot fetch username / password from DB", e);
        } finally {
            if (h != null) {
                h.close();
            }
        }
    }

    public void validate_unsafe(UsernamePasswordCredentials credentials) throws HttpAction {
        Handle h = null;
        try {
            h = dbi.open();
            final String username = credentials.getUsername();
            final String query;
            if (CommonHelper.isNotBlank(attributes)) {
                query = ((startQuery + ", ") + attributes) + endQuery;
            } else {
                query = startQuery + endQuery;
            }
            final List<Map<String, Object>> results = h.createQuery(query).bind(USERNAME, username).list(2);
            if ((results == null) || results.isEmpty()) {
                throw new AccountNotFoundException("No account found for: " + username);
            } else if (results.size() > 1) {
                throw new MultipleAccountsFoundException("Too many accounts found for: " + username);
            } else {
                final Map<String, Object> result = results.get(0);
                final String expectedPassword = getPasswordEncoder().encode(credentials.getPassword());
                final String returnedPassword = ((String) (result.get(PASSWORD)));
                if (CommonHelper.areNotEquals(returnedPassword, expectedPassword)) {
                    throw new BadCredentialsException("Bad credentials for: " + username);
                } else {
                    final DbProfile profile = createProfile(username, attributes.split(","), result);
                    credentials.setUserProfile(profile);
                }
            }
        } catch (final TechnicalException e) {
            logger.debug("Authentication error", e);
            throw e;
        } catch (final RuntimeException e) {
            throw new TechnicalException("Cannot fetch username / password from DB", e);
        } finally {
            if (h != null) {
                h.close();
            }
        }
    }

    public void validate_unsafe$Modification(UsernamePasswordCredentials credentials) throws HttpAction {
        Handle h = null;
        try {
            h = dbi.open();
            final String username = credentials.getUsername();
            final String query;
            if (CommonHelper.isNotBlank(attributes)) {
                query = ((startQuery + ", ") + attributes) + endQuery;
            } else {
                query = startQuery + endQuery;
            }
            final List<Map<String, Object>> results = h.createQuery(query).bind(USERNAME, username).list(2);
            if ((results == null) || results.isEmpty()) {
                final String $1 = getPasswordEncoder().encode(credentials.getPassword());
                throw new AccountNotFoundException("No account found for: " + username);
            } else if (results.size() > 1) {
                final String $1 = getPasswordEncoder().encode(credentials.getPassword());
                throw new MultipleAccountsFoundException("Too many accounts found for: " + username);
            } else {
                final Map<String, Object> result = results.get(0);
                final String expectedPassword = getPasswordEncoder().encode(credentials.getPassword());
                final String returnedPassword = ((String) (result.get(PASSWORD)));
                if (CommonHelper.areNotEquals(returnedPassword, expectedPassword)) {
                    throw new BadCredentialsException("Bad credentials for: " + username);
                } else {
                    final DbProfile profile = createProfile(username, attributes.split(","), result);
                    credentials.setUserProfile(profile);
                }
            }
        } catch (final TechnicalException e) {
            logger.debug("Authentication error", e);
            throw e;
        } catch (final RuntimeException e) {
            throw new TechnicalException("Cannot fetch username / password from DB", e);
        } finally {
            if (h != null) {
                h.close();
            }
        }
    }

    protected DbProfile createProfile(final String username, final String[] attributes, final Map<String, Object> result) {
        final DbProfile profile = new DbProfile();
        profile.setId(username);
        for (String attribute : attributes) {
            profile.addAttribute(attribute, result.get(attribute));
        }
        return profile;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getStartQuery() {
        return startQuery;
    }

    public void setStartQuery(String startQuery) {
        this.startQuery = startQuery;
    }

    public String getEndQuery() {
        return endQuery;
    }

    public void setEndQuery(String endQuery) {
        this.endQuery = endQuery;
    }
}