package dataModel;

import java.io.Serializable;

/**
 * Represents a client that has a {@link PrimaryKey} as unique identifier.
 *
 * @author iliyan-kostov <iliyan.kostov.gml@gmail.com>
 */
public class Client extends PrimaryKey implements Serializable {

    /**
     * The client first name.
     */
    protected String firstName;

    /**
     * The client last name.
     */
    protected String lastName;

    /**
     * Constructor.
     *
     * @param primaryKey the unique identifier value.
     *
     * @param firstName the client first name.
     *
     * @param lastName the client last name.
     */
    public Client(String primaryKey, String firstName, String lastName) {
        super(primaryKey);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the client first name.
     *
     * @return the client first name.
     */
    public final String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns the client last name.
     *
     * @return the client last name.
     */
    public final String getLastName() {
        return this.lastName;
    }
}