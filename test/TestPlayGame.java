import com.hillsol.*;

public class TestPlayGame {

    public static void main(String[] args) {
        PassThreeStrategy passThreeRandom = new PassThreeRandom();
//        PlayHandStrategy p = new PlayHandStrategy() {

        Player aaron = new Player("Aaron", passThreeRandom);
        Player freddie = new Player("Freddie", passThreeRandom);
        Player helena = new Player("Helena", passThreeRandom);
        Player christina = new Player("Christina", passThreeRandom);
        Game game = new Game(aaron, freddie, helena, christina);
        game.playGame();
        game.printPlayers();
    }
}
