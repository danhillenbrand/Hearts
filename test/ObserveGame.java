import com.hillsol.*;
import com.hillsol.passthreestrategies.*;
import com.hillsol.playhandstrategies.*;

import java.util.Set;

public class ObserveGame {

    public static void main(String[] args) {
        int numberOfGames = 1;
        if (args.length > 0) {
            if (isNumeric(args[0])) {
                numberOfGames = Integer.parseInt(args[0]);
            }
        }
        System.out.println("============ Playing " + numberOfGames + " game(s) ==========");


        PassThreeStrategy passRandom = new PassRandom();
        PassThreeStrategy passHighest = new PassHighest();
        PassThreeStrategy passSpades = new PassSpades();
        PassThreeStrategy passHearts = new PassHearts();
        PassThreeStrategy passLowest = new PassLowest();

        PlayHandStrategy playRandomCard = new PlaySemiRandomCard();
        PlayHandStrategy playHighAlways = new PlayHighestCardAlways();
        PlayHandStrategy playLowAlways = new PlayLowestCardAlways();
        PlayHandStrategy playCeilingCard = new PlayCeilingCard();

        // todo: make sure the player positions are shuffled in multiple games
        Player aaron = new Player("Aaron", passSpades, playCeilingCard);
        Player freddie = new Player("Freddie", passHighest, playHighAlways);
        Player helena = new Player("Helena", passRandom, playRandomCard);
        Player christina = new Player("Christina", passLowest, playLowAlways);
        Game game = new Game(aaron, freddie, helena, christina);
        for (int i = 0; i < numberOfGames; i++) {
            game.reset();
            game.playGame();
            Set<Player> winners = game.getWinningPlayers();
            for (Player winner : winners) {
                winner.incrementWinCount();
            }
        }
        printStatistics(game);
    }
    public static void printStatistics(Game game) {
        for (Player player : game.getPlayers()) {
            System.out.println(player.getOverallFirstPlaceGames()
                    + " wins - " + player.getName()
                    + "; " + player.getPassThreeStrategyName()
                    + "; " + player.getPlayHandStrategyName()
            );
        }
        System.out.println("\n========== Shoot the Moon counts ==========");
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + ":  "
                    + player.getShootTheMoonCount());
        }
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
