# Possible solutions for day 10 part 2

This document lists the solutions that I have tried or will try to complete this challenge.

## 1. Sort buttons and use the greedy algorithm

The first thing I tried was to sort the buttons by the number of elements that they affect. starting with the buttons that have the biggest effect. This does not always work, as shown in below.

For example, with the following machine description:

- Buttons: (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4)
- Joltage requirements: {7,5,12,7,2}

We can first sort the buttons into (0,2,3,4) (1,2,3,4) (0,1,2) (2,3) (0,4)

Then perform the following steps:

1. Apply the first button as many times as possible, which drops the joltage requirements down to {5,5,10,5,0}
2. Apply the second button as many times as possible, which is 0
3. Apply the third button as many times as possible, which drops the joltage requirements down to {0,0,5,5,0}
4. Apply the fourth button as many times as possible, which drops the joltage requirements down to {0,0,0,0,0}, completing the machine

### 1.1 The problem

Unfortunately, this does not always lead to a solution. Consider if the buttons were sorted as (1,2,3,4) (0,2,3,4) (0,1,2) (2,3) (0,4).

| Button    | Joltage requirements after pressing |
| --------- | ----------------------------------- |
| (1,2,3,4) | {7,3,10,5,0}                        |
| (0,2,3,4) | no change                           |
| (0,1,2)   | {4,0,7,5,0}                         |
| (2,3)     | {4,0,2,0,0}                         |
| (0,4)     | no change                           |

This does not fully solve the machine.

## 2. Solve as a system of linear equations

This problem can be thought of as a system of linear equations.

Using the same machine description as section 1:

- Buttons: (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4)
- Joltage requirements: {7,5,12,7,2}

### 2.1 Get a solution set

Yields a linear equation where x<sub>n</sub> is the number of times button `n` (0-indexed) is pressed and a...e represents the buttons:

- x<sub>0</sub>a + x<sub>2</sub>c + x<sub>3</sub>d = 7
- x<sub>3</sub>d + x<sub>4</sub>e = 5
- x<sub>0</sub>a + x<sub>1</sub>b + x<sub>3</sub>d + x<sub>4</sub>e = 12
- x<sub>0</sub>a + x<sub>1</sub>b + x<sub>4</sub>e = 7
- x<sub>0</sub>a + x<sub>2</sub>c + x<sub>4</sub>e = 2

This can be represented by the following augmented matrix:

| b0 | b1 | b2 | b3 | b4 | req |
| -: | -: | -: | -: | -: | --: |
|  1 |  0 |  1 |  1 |  0 |   7 |
|  0 |  0 |  0 |  1 |  1 |   5 |
|  1 |  1 |  0 |  1 |  1 |  12 |
|  1 |  1 |  0 |  0 |  1 |   7 |
|  1 |  0 |  1 |  0 |  1 |   2 |

In reduced row echelon form:

| b0 | b1 | b2 | b3 | b4 | req |
| -: | -: | -: | -: | -: | --: |
|  1 |  0 |  1 |  0 |  0 |   2 |
|  0 |  1 | -1 |  0 |  0 |   5 |
|  0 |  0 |  0 |  1 |  0 |   5 |
|  0 |  0 |  0 |  0 |  1 |   0 |
|  0 |  0 |  0 |  0 |  0 |   0 |

This means that

- (0,2,3,4) presses + (0,4) presses = 2
- (2,3) presses - (0,4) presses = 5
- (0,1,2) presses = 5
- (1,2,3,4) presses = 0

### Getting the optimal solution

This solution can then be optimized to minimize total presses by [Linear Programming](https://en.wikipedia.org/wiki/Linear_programming).
The fact that the the solution always lies on a vertex may be useful for narrowing down the solution (see [Optimal vertices (and rays) of polyhedra](https://en.wikipedia.org/wiki/Linear_programming#Optimal_vertices_(and_rays)_of_polyhedra)).

Because I don't care that much about optimization at this stage, my initial solution will be to iterate over each vertex in the solution set and find the best one. But what is a vertex? A vertex might be a solution where presses of a particular button are maximized in each row of the result set.
