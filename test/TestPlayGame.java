import com.hillsol.*;
import com.hillsol.passthreestrategies.PassThreeRandom;
import com.hillsol.passthreestrategies.PassThreeStrategy;
import com.hillsol.playhandstrategies.PlayHandStrategy;
import com.hillsol.playhandstrategies.PlaySemiRandomCard;

public class TestPlayGame {

    public static void main(String[] args) {
        PassThreeStrategy passThreeRandom = new PassThreeRandom();
        PlayHandStrategy playRandomCard = new PlaySemiRandomCard();

        Player aaron = new Player("Aaron", passThreeRandom, playRandomCard);
        Player freddie = new Player("Freddie", passThreeRandom, playRandomCard);
        Player helena = new Player("Helena", passThreeRandom, playRandomCard);
        Player christina = new Player("Christina", passThreeRandom, playRandomCard);
        Game game = new Game(aaron, freddie, helena, christina);
        game.playGame();
        game.printPlayers();
    }
}
