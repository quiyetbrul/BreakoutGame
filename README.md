# Breakout Video Game



## Description

The task is to create a game similar to Breakout, an arcade game developed and published by Atari. Here are my task instructions:

- Your game should have a single screen with 8 rows of bricks, a paddle, and a ball. 
- If the ball touches the bottom of the screen, you lose a ‘life’. 
- After 5 lives, the game is over. 
- Every time the ball touches a brick, the brick disappears and the score increases. 
- The ball bounces off the paddle, the bricks, and the top, left and right edges of the screen.



## Table of Contents

* [Installation](#installation)
* [Game-Instructions](#game-instructions)
* [Features](#features)
* [Dev-Progress](#dev-progress)
* [Problems](#problems)
* [TODO](#todo)



## Installation

I wrote the game in Java ([jre-8u291](https://www.oracle.com/java/technologies/javase-jre8-downloads.html)) using [JetBrains' Intellij IDEA](https://www.jetbrains.com/idea/download/#section=mac) and jbr-11 for SDK. (I have yet to learn how to build an executable.)



## Game-Instructions

- Press spacebar to launch the ball
- Use keys A and D to move the paddle left and right
- Break the bricks with the ball
- Don't let the ball fall into the abyss!



## Features

- Game Menu (Play, How To, Quit)
- Brick breaker mechanics
- Bricks (They have colors!)
- Ball
- Paddle
- Lives
- Score



## Dev-Progress

Please find my progress from my personal [repo](https://github.com/quiyetbrul/BreakoutGame/). I created my own repo because I wanted friends to ask me questions about the code. Out of five people that I asked, only one ended up asking questions :'c



## Problems

- I have never used C++ for game development before. When doing minor research, I came about a header file that was specific to the Windows OS. Since I have a MacBook Pro, it became a major problem. I have some newbie experience in game dev, but they were written in either Java or C# with the help of Unity, so I never really had to deal with OS specificity. If I really wanted to code this in C++ with the use of the Windows header file, I need to code it in a Windows computer (VM or bootcamp my MacBook) -- though that would mean that only Windows users can work on the code. I am sure there are other alternatives for the windows header out there. I could use Qt, OpenGL, Direct2D, etc.

  - I also wanted to ask my friends to test the game for me, but I have no clue what kind of computer they own, so I ended up using C# with Unity because it is cross-platform. I’ll maybe push it to the Play and App stores for phones
    - Turns out that using game engines isn’t allowed for this project, so I ended up using Java. I learned this a few days before the deadline.

- The IDE offers code clean up feature

- - At first, I thought this was a great idea. It ended up being a culprit. In ```Handler.java```, it suggested changing two for-loops into range-based loops. I thought it wouldn't be a problem. It somehow ended up messing the game objects -- the bricks would still be present underneath/over the game over screen, which resulted into a major error.



## TODO

- Victory screen -- after the player breaks all bricks, a victory screen shows along with score
- Clean up GameMenu.java -- it is a mess!
  - I've tried creating a class member for each game object (i.e., ball, paddle, brick), but it gave me errors
- The ball sometimes gets stuck inside the paddle
- The ball direction changes after pressing the spacebar after the ball launches
  - the spacebar should only work 
- The ball's starting point isn't dynamic

