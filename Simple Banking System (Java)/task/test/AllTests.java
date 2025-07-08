import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Account
        banking.account.AccountControllerTest.class,
        banking.account.AccountTest.class,
        banking.account.AccountStoreTest.class,

        // Card
        banking.card.CardNumberGeneratorTest.class,

        // Login
        banking.login.LoginManagerTest.class,
        banking.login.LoginServiceTest.class,
        
        // Menu Login
        banking.menu.login.LoginMenuApplicationTest.class,
        banking.menu.login.LoginMenuResultTest.class,
        banking.menu.login.LoginMenuServiceTest.class,

        // Menu Main
        banking.menu.main.MainMenuApplicationTest.class,
        banking.menu.main.MainMenuResultTest.class,
        banking.menu.main.MainMenuServiceTest.class,

        // Utility
        banking.utility.InputParserTest.class,
        banking.utility.LuhnUtilsTest.class,
        banking.utility.NumberGeneratorTest.class
})
public class AllTests {

}
