package net.d53dev.scalamarsrover

import org.specs2.Specification
import org.specs2.mock.Mockito
import spatial._


class RoverSpec extends Specification with Mockito { def is = s2"""

  The rover should correctly rotate
  |  single rotations               $singleRotation
  |  multiple rotations             $multiRotation

  The rover should pass the correct move objects to the terrain
  | when moving forward             $forwardCheck
  | when moving backward            $backwardCheck
                                 """

  def singleRotation = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)

    var rover = Rover(terrain, initialPos, North)
    rover.move(Left).orientation mustEqual West
    rover.move(Right).orientation mustEqual East

    rover = Rover(terrain, initialPos, East)
    rover.move(Left).orientation mustEqual North
    rover.move(Right).orientation mustEqual South

    rover = Rover(terrain, initialPos, South)
    rover.move(Left).orientation mustEqual East
    rover.move(Right).orientation mustEqual West

    rover = Rover(terrain, initialPos, West)
    rover.move(Left).orientation mustEqual South
    rover.move(Right).orientation mustEqual North

  }
  def multiRotation = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val initialOrientation = North

    val rover = Rover(terrain, initialPos, initialOrientation)

    rover.move(Left).move(Left).orientation mustEqual South
    rover.move(Left).move(Left).move(Left).orientation mustEqual East
    rover.move(Left).move(Left).move(Left).move(Left).orientation mustEqual North

    rover.move(Right).move(Right).orientation mustEqual South
    rover.move(Right).move(Right).move(Right).orientation mustEqual West
    rover.move(Right).move(Right).move(Right).move(Right).orientation mustEqual North
  }

  def forwardCheck = {
    val returnedPos = Position2D(42, 43)
    val terrain = mock[Terrain2D]
    terrain.performMove(any[Position2D], any[Move]) returns returnedPos

    val rover = Rover(terrain, Position2D(1, 1), North)

    rover.move(Forward) mustEqual returnedPos

    there was one(terrain).performMove(Position2D(1, 1), MoveForward(North))
  }
  def backwardCheck = {
    val returnedPos = Position2D(42, 43)
    val terrain = mock[Terrain2D]
    terrain.performMove(any[Position2D], any[Move]) returns returnedPos

    val rover = Rover(terrain, Position2D(1, 1), North)

    rover.move(Backward) mustEqual returnedPos

    there was one(terrain).performMove(Position2D(1, 1), MoveBackward(North))

  }
}
