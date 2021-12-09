# pandemicSim
A pandemic simulator, made as a mock technical interview assignment from [aNewSpring](https://www.anewspring.com/).

## Description
A Java simulator modelling the spread of a virus on a grid of `n*n` persons over the course of `r` rounds.

Rules of the simulator:
- The virus spreads through contact with adjacent (including diagonals) people.
- A person with more than `i` infected adjacent contacts will become infected.
- An infected person with more than `rc` infected adjacent contacts will recover
and become uninfected.

## Use
Program arguments expected (in order):

`java pandemic n r i rc [<x,y>,<x,y>...]`

1. `n`: grid size
2. `r`: rounds of simulation
3. `i`: infection threshold
4. `rc`: recovery threshold
5. coordinates of infected persons at start (upper left corner coordinates being 1,1)

Example:
```
java pandemic 8 7 2 6 [<4,7>,<4,8>,<5,8>,<6,8>]
```
launches a simulation with: a grid size of 8, 7 rounds, 
with an infection threshold of 2 and a recovery threshold of 6,
and 4 infected persons at the listed coordinates.

## Demonstration
> run with above example parameters

![simulation demo](https://i.imgur.com/YTJAdX9.png?1)

## Resources
[Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-introduction.html)

[Algorithms for solving Conway's Game of Life, on which this exercise is based](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Algorithms)
