import com.hillsol.*;

import java.util.Set;

public class ObserveGame {


    public static void main(String[] args) {
        int numberOfGames = 1;
        if (args.length > 0) {
            if (isNumeric(args[0])) {
                numberOfGames = Integer.valueOf(args[0]);
            }
        }
        System.out.println("============ Playing " + numberOfGames + " game(s) ==========");


        PassThreeStrategy passThreeRandom = new PassThreeRandom();
        PlayHandStrategy playRandomCard = new PlaySemiRandomCard();
        PlayHandStrategy playHighAlways = new PlayHighestCardAlways();

        Player aaron = new Player("Aaron", passThreeRandom, playRandomCard);
        Player freddie = new Player("Freddie", passThreeRandom, playRandomCard);
        Player helena = new Player("Helena", passThreeRandom, playHighAlways);
        Player christina = new Player("Christina", passThreeRandom, playRandomCard);
        Game game = new Game(aaron, freddie, helena, christina);
//        game.printPlayers();
        for (int i = 0; i < numberOfGames; i++) {
            game.reset();
            game.playGame();
//            game.printPlayers();
            Set<Player> winners = game.getWinningPlayers();
            for (Player winner : winners) {
                winner.addWin();
            }
        }
        game.printStatistics();
    }

    // Copied from Apache Commons:
    public static boolean isNumeric(final CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
