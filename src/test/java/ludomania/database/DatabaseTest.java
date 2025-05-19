package ludomania.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.controllers.LudomaniaDBController;
import database.controllers.api.DatabaseController;
import database.schemas.UserEntry;
import database.schemas.WalletEntry;

public class DatabaseTest {    
    private DatabaseController usersController;
    private DatabaseController walletsController;

    @BeforeEach
    public void setUp() {
        this.usersController = new LudomaniaDBController(new UserEntry("pippo", "ciaobello99"));
        this.walletsController = new LudomaniaDBController(new WalletEntry("pippo", 0.0));
    }

    @Test
    public void testUserWrite() {
        assertTrue(this.usersController.insert());
    }

    @Test
    public void testWalletWrite() {
        assertTrue(this.walletsController.insert());
    }


    // @Test
    // public void testUserRead() {
    //     Entry usr = this.usersController.read();
    //     System.out.println(usr);
    //     // assertEquals(ROUGE_AMOUNT, rBet.getValue());
    //     // assertEquals(TrenteEtQuaranteBetType.ROUGE, rBet.getType());
    //     // assertEquals("Rouge", rBet.getType().getTypeName());

    //     // assertEquals(ENVERSE_AMOUNT, cBet.getValue());        
    //     // assertEquals(TrenteEtQuaranteBetType.ENVERSE, cBet.getType());
    //     // assertEquals("Enverse", cBet.getType().getTypeName());
    // }

    // @Test
    // public void testEvaluate() {
    //     // assertEquals(ROUGE_AMOUNT + (ROUGE_AMOUNT * rBet.getType().getPayout()), rBet.evaluate());
    //     // assertEquals(ENVERSE_AMOUNT + (ENVERSE_AMOUNT * cBet.getType().getPayout()), cBet.evaluate());
    // }
}
