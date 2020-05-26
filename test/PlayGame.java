import com.hillsol.Unskilled;
import com.hillsol.Game;
import com.hillsol.Player;

public class PlayGame {

    public static void main(String[] args) {
        Player aaron = new Unskilled("Aaron");
        Player freddie = new Unskilled("Freddie");
        Player helena = new Unskilled("Helena");
        Player christina = new Unskilled("Christina");
        Game game = new Game(aaron, freddie, helena, christina);
        game.playGame();
        game.printPlayers();
    }
}
