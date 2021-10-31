# advent-2019

Solving the challenges in the [Advent of Code 2019](https://adventofcode.com/2019).

## slock

Some simple code stats for comparison.

Day | Source lines
----|---------------
  1 |  35
  2 |  83
  3 | 127
  4 |  54
  5 | 148

## Day 1

A simple warm-up with an easy summed calculation in part 1, following by a recursive version in part 2 to find the converged value of a sequence.

## Day 2

Here we implement a little microcontroller with three instructions. Similar to Day 8 in Advent 2020, I did this as a state machine using a *reduce* function to step through the memory, updating the instruction pointer (ip) on the way.

For part 2, we need to step through the code with different seed values, to find which one gives the result we need.

## Day 3

And here we go with a more serious challenge. My first inclination was to see if there was some neat algorithm for finding the intersections of two random walks in a 2D plane. Sadly, that is either not the case, or I was using the wrong terminology for a similar problem. So, we need to break each path into a sequence of horizontal or vertical line segments. 

First is to convert the directional instructions into a series of waypoints in the plane, and from there into a sequence of line segments with a start and endpoint. I then look for an intersection between every possible pair of line segments, one from each path. Detecting an intersection took me a little while: it's a tricky little calculation in two dimensions, when keeping track of four 2D endpoints. Finally, I found all the intersections and for part 1, we find the intersection point the shortest distance from the origin.

For part 2, we actually need to measure the length of the paths from the origin to the intersection points, and then find the point with the shortest total length across both paths. This requires finding each segment, adding up all the segment lengths before it, and then add the distance from the last endpoint to the intersection. This involves keeping track of a number of things but we get there in the end. Looking at the stats, this one weeded out a hefty 36% of the starters from Day 2.

## Day 4

Part 1 of this one is fine. We just apply a couple of simple tests to all the numbers in the range, via filter and count. Part 2 adds another rule that I didn't understand at first, so had to look around for an explanation. I was going to use regexs but realised that trying to match exactly 2 given digits is quite challenging, so ended up using a frequency analysis.

## Day 5

Our little computer gets tougher with the addition of some more instructions: two in part 1, and another four in part 2, plus the implementation of immediate parameters on top of the usual addressing. This required a bit of careful coding (and some cursing when not being careful enough) to add more state, handle modes (except for address destination parameters) to get everything lined up and working. The code isn't as tidy as I would like, so I might need to do some refactoring if we need to add to this machine, as I expect will be the request in later days.

## Day 6

This is an opportunity for use a graph, and I've gone for Ubergraph, as usual. The orbital relationships can be treated as edges on a graph. For part 1, we create a graph of directed edges x -> y for where y orbits x. We find the root node as the one without any incoming edges, and then add up the shortest paths from the root to every node.

For part 2, we create an undirected graph, and then just look for the shortest path between us and Santa. Ubergraph handles all this easily.

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
