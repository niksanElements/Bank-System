package networking.messages.request;

import dataModel.Money;
import networking.messages.Message;
import networking.messages.Request;

import java.io.Serializable;

/**
 * A {@link Request} used by the client to request the withdrawal of a specified
 * amount of money from a specified bank account. The user provides the ID of
 * the bank account and the {@link dataModel.Money} to withdraw.
 * <p>
 * The provided {@link dataModel.Money} has to be converted to an equal amount
 * of {@link dataModel.Money} matching the currency of the source bank account
 * before being withdrawn!
 *
 * @see dataModel.Money
 * @see dataModel.CurrencyConverter
 *
 * @author iliyan-kostov <https://github.com/iliyan-kostov/>
 */
public final class WithdrawRequest extends Request implements Serializable {

    /**
     * Reference to the value of {@link Message#type} used by this class.
     */
    public static final String TYPE = "WITHDRAWREQUEST";

    /**
     * The ID of the bank to withdraw from.
     */
    private final String fromBankAccount;

    /**
     * The money to withdraw.
     */
    private final Money money;

    /**
     * Constructor.
     *
     * @param fromBankAccount the value for {@link #fromBankAccount}.
     *
     * @param money the value for {@link #money}.
     */
    public WithdrawRequest(String fromBankAccount, Money money) {
        super(WithdrawRequest.TYPE);
        this.fromBankAccount = fromBankAccount;
        this.money = money;
    }

    /**
     * Returns the value of {@link #fromBankAccount}.
     *
     * @return the value of {@link #fromBankAccount}.
     */
    public final String getFromBankAccount() {
        return this.fromBankAccount;
    }

    /**
     * Returns the value of {@link #money}.
     *
     * @return the value of {@link #money}.
     */
    public final Money getMoney() {
        return this.money;
    }

    @Override
    public String toString() {
        return (WithdrawRequest.TYPE + ":\n"
                + "-----------------\n"
                + "username: " + this.getUsername() + "\n"
                + "fromBankAcccount: " + this.getFromBankAccount() + "\n"
                + "money: " + this.getMoney() + "\n"
                + "-----------------\n");
    }
}
