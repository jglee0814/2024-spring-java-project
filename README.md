# Quoridor Project
<p align="center">
  <img src="https://github.com/user-attachments/assets/c1d0b46e-8cf2-4478-b7c1-1c6cadbcff3a" width="500">
</p>

###### JAVA PROGRAMMING LAB (SWE2023)
###### INSTRUCTOR: Prof. TAMER ABUHMED
###### STUDENT NAME: Jungwoo Lee
###### STUDENT #: 2020314556

*Read this file in [한국어](https://github.com/jglee0814/2024-spring-java-project/blob/main/docs/README.KR.md)*


## Introduction
This game is inspired by the board game 'Quoridor'. It can be played by 2 to 4 players. The basic game rules are as follows: <br>
Each player has a pawn, and the goal is to move it from the starting position to the opposite side. Opponents can place fences to obstruct each other's paths.

<p align="center">
  <img src="https://github.com/user-attachments/assets/a7bb3d3a-fc8e-44cb-a368-50c610b8af0c" width="300">
</p>

In this case, the opposite position does not mean exactly the mirrored location like the relationship between P2 and P1. For P2, winning means reaching any position within the drawn square area. This rule applies to all players. <br><br>
The game ends when any player reaches the opposite side. Thus, there is only one winner.

## Detailed Rules
1. **Setup** <br>
 The board size is 7x7, and each player can place up to 4 fences. These fences can be either horizontal or vertical.
2. **Movement** <br>
  When the game starts, the Current Player indicator shows which player can take action. Each player has two options per turn. They must choose one of the two buttons and proceed with their movement; they cannot change the movement type mid-turn.
   1. Move Pawn
      * Players can move their pawn up, down, left, or right, but not diagonally.
      * If there is a fence between the current and target positions, the player cannot move in that direction.
   2. Place Fence
      * Players can place fences to obstruct opponents. Fences cannot be placed where another fence is already located.
      * Two adjacent fence pieces must be chosen, and they must be of the same type.
         <p align="center">
          <img src="https://github.com/user-attachments/assets/f29ffae1-ca45-4918-b842-038c7b4953fc" width="300">
          </p>
      * Placed fences are white.
      * Fences cannot completely block an opponent's path to their goal.
          <p align="center">
            <img src="https://github.com/user-attachments/assets/808252e5-b2b1-4fd4-998b-caaf4029a54c" width="300">
          </p>
          Since the fences block P2 and P3 to the way to their goal, fences can't be places as this.

## Game Description
1. Select Number of Players: Choose the number of players and start the game.
2. Rules: Briefly explain the game rules.
3. Exit: Exit the program.

## Menu Description
* Game Status <br>
  * Shows the remaining number of fences each player can place.
* Program <br>
  * Rules: Review the rules.
  * Back to game start window: Return to the game start screen.
  * Exit program: Exit the program.
