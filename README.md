# Hearts

My initial purpose for creating this repo was to build an example utilizing the Strategy pattern.
An example that made it easy to understand what the Strategy pattern is and why & how it is used.
I wanted an example that wasn't so trivial that it didn't actually do anything except serve as an example.
So I created a simple, but working model of the card game called Hearts.

The strategies are currently very simplistic, but the point is to show the mechanics of how they can be implemented.


## The Game

In its current form, the game is played by four computer players.
Future enhancements could include allowing human player(s) to compete, with or without computer players to fill out the foursome required for play.
Ultimately, I envision building a distributed version of the game, allowing users to play online from different locations.
Again, the purpose is to demonstrate the technologies in action in an understandable form. 


## The Strategies

There are two main strategies currently set up in the game:

1. Passing three cards to other players
    - At the beginning of most "hands", all players pass three cards to one of their competitors.   
    - There is an approach (strategy) for selecting the three cards, and that approach can vary depending on the player (and potentially other factors).
    - This strategy, in its current form in this repo, allows each player to select their approach to passing three cards, which will be the same strategy used throughout the game.
    - Future enhancements could include allowing players to change their strategy mid-game, based on factors such as the state of the scores of the players, or where other players are "sitting".
2. Choosing a card to play
    - For each "trick" being played, each player must choose one card to play.
    - As in the first strategy, above, the players' choice of strategy for which card they choose is constant throughout the game.
    - Future enhancements could include allowing players to change their strategy on-the-fly.