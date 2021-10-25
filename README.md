# advent-2019

Solving the challenges in the [Advent of Code 2019](https://adventofcode.com/2019).

## slock

Some simple code stats for comparison.

Day | Source lines
----|---------------
  1 |  35
  2 |  83
  3 | 127

## Day 1

A simple warm-up with an easy summed calculation in part 1, following by a recursive version in part 2 to find the converged value of a sequence.

## Day 2

Here we implement a little microcontroller with three instructions. Similar to Day 8 in Advent 2020, I did this as a state machine using a *reduce* function to step through the memory, updating the instruction pointer (ip) on the way.

For part 2, we need to step through the code with different seed values, to find which one gives the result we need.

## Day 3

And here we go with a more serious challenge. My first inclination was to see if there was some neat algorithm for finding the intersections of two random walks in a 2D plane. Sadly, that is either not the case, or I was using the wrong terminology for a similar problem. So, we need to break each path into a sequence of horizontal or vertical line segments. 

First is to convert the directional instructions into a series of waypoints in the plane, and from there into a sequence of line segments with a start and endpoint. I then look for an intersection between every possible pair of line segments, one from each path. Detecting an intersection took me a little while: it's a tricky little calculation in two dimensions, when keeping track of four 2D endpoints. Finally, I found all the intersections and for part 1, we find the intersection point the shortest distance from the origin.

For part 2, we actually need to measure the length of the paths from the origin to the intersection points, and then find the point with the shortest total length across both paths. This requires finding each segment, adding up all the segment lengths before it, and then add the distance from the last endpoint to the intersection. This involves keeping track of a number of things but we get there in the end. Looking at the stats, this one weeded out a hefty 36% of the starters from Day 2.


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
