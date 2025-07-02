import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        banking.account.AccountTest.class,
        banking.account.AccountStoreTest.class,
        banking.card.CardNumberGeneratorTest.class,
        banking.utility.NumberGeneratorTest.class
})
public class AllTests {

}
