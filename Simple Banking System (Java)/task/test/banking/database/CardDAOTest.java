package banking.database;

import banking.account.Account;
import banking.utility.database.DatabaseTestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CardDAOTest {
    private String databaseName;
    private Connection conn;
    private CardDAO dao;

    @Before
    public void setup() {
        this.databaseName = "testCardDAO.s3db";

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.dao = new CardDAO(conn);
        dao.ensureCardTableExists(); // ensure table exists first
        dao.clearAllCards(); // clean slate
    }

    @After
    public void tearDown() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DatabaseTestUtils.deleteDatabaseFile(databaseName);
    }


    /**
     * Ensures that the 'card' table exists in the database.
     * If it does not exist, it will be created.
     */
    @Test
    public void testCreateTable() {
        dao.ensureCardTableExists();
        boolean exists = tableExists("card", databaseName);
        assertTrue("Card table should exist", exists);
    }

    /**
     * Ensures that newly created cards are added to the database.
     */
    @Test
    public void testAddCard() {
        Account account = new Account();
        dao.addCard(account.getCardNumber(), account.getPin(), account.getBalance());

        Account retrievedAccount = dao.findByCardAndPin(account.getCardNumber(), account.getPin());
        assertNotNull("Card should be found in the database", retrievedAccount);

        assertEquals("Card numbers from account and retrieved account should match",
                account.getCardNumber(), retrievedAccount.getCardNumber());

        assertEquals("Pins from account and retrieved account should match",
                account.getPin(), retrievedAccount.getPin());
    }

    /**
     * Ensures that card details in the database are retrieved correctly.
     */
    @Test
    public void testFindByCard_validCard_returnsAccount() {
        Account account = new Account();
        dao.addCard(account.getCardNumber(), account.getPin(), account.getBalance());

        Account retrievedAccount = dao.findByCard(account.getCardNumber());
        assertNotNull("Card should be found in the database",
                retrievedAccount);

        assertEquals("Card numbers from account and retrieved account should match",
                account.getCardNumber(),
                retrievedAccount.getCardNumber());
    }

    /**
     * Ensures that the balance in the database is correctly returned
     */
    @Test
    public void testGetBalanceByCardNumber() {
        Account account = new Account();
        int testBalance = 1234;
        dao.addCard(account.getCardNumber(), account.getPin(), testBalance);

        int retrievedBalance = dao.getBalanceByCardNumber(account.getCardNumber());
        assertEquals("Retrieved balance should match the inserted balance ",
                testBalance,
                retrievedBalance);
    }

    /**
     * Ensures that the transfer from one account to another is successful
     */
    @Test
    public void testTransferFunds_successfulTransfer_returnsTrue() {
        Account debitAccount = new Account();
        Account creditAccount = new Account();

        int debitAccountInitialBalance = 10000;
        int transferBalance = 1000;

        this.dao.addCard(debitAccount.getCardNumber(), debitAccount.getPin(), debitAccountInitialBalance);
        this.dao.addCard(creditAccount.getCardNumber(), creditAccount.getPin(), creditAccount.getBalance());

        String debitCardNumber = debitAccount.getCardNumber();

        boolean isSuccessfulTransfer = this.dao.transferFunds(debitCardNumber,
                creditAccount.getCardNumber(), transferBalance);

        assertTrue("Transfer should be successful and return true",
                isSuccessfulTransfer);

        assertEquals("Debit account should be equal to the initial balance less the transfered " +
                "balance.",
                debitAccountInitialBalance - transferBalance,
                this.dao.getBalanceByCardNumber(debitCardNumber));

        assertEquals("Credit account should be equal to the transfer balance plus the initial " +
                        "balance.",
                creditAccount.getBalance() + transferBalance,
                this.dao.getBalanceByCardNumber(creditAccount.getCardNumber()));
    }

    /**
     * Ensures that cards are successfully deleted from the database.
     */
    @Test
    public void testDeleteCard_successfulDelete_cardIsNull() {
        Account accountToDelete = new Account();
        this.dao.addCard(accountToDelete.getCardNumber(), accountToDelete.getPin(),
                accountToDelete.getBalance());

        assertNotNull("The card is in the database",
                this.dao.findByCard(accountToDelete.getCardNumber()));

        this.dao.closeAccount(accountToDelete.getCardNumber());

        assertNull("Searching for the deleted card should return null",
                this.dao.findByCard(accountToDelete.getCardNumber()));
    }


    /**
     * Checks whether a given table exists in the specified SQLite database
     * using metadata from the database connection.
     *
     * @param tableName the name of the table to check
     * @param dbName the database file name
     * @return true if the table exists; false otherwise
     */
    private boolean tableExists(String tableName, String dbName) {
        String url = "jdbc:sqlite:" + dbName;
        try (Connection conn = DriverManager.getConnection(url);
             ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }





}