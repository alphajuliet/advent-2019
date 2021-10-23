# advent-2019

Solving the challenges in the [Advent of Code 2019](https://adventofcode.com/2019).

## slock

Some simple code stats.

Day | Source lines
----|---------------
  1 |  35
  2 |  83

## Day 1

A simple warm-up with an easy summed calculation in part 1, following by a recursive version in part 2 to find the converged value of a sequence.

## Day 2

Here we implement a little microcontroller with three instructions. Similar to Day 8 in Advent 2020, I did this as a state machine using a *reduce* function to step through the memory, updating the instruction pointer (ip) on the way.

For part 2, we need to step through the code with different seed values, to find which one gives the result we need.


## License

Copyright © 2021 Andrew Joyner

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
