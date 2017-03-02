package net.d53dev.scalamarsrover

import org.specs2.Specification
import spatial._

class TerrainSpec extends Specification { def is = s2"""
  Basic assumptions
  |   The terrain must be 1x1 or larger $boundsCheck

  The terrain should correctly handle moves
  |   moves forward facing north        $forwardNorth
  |   moves backward facing north       $backwardNorth
  |   moves forward facing east         $forwardEast
  |   moves backward facing east        $backwardEast
  |   moves forward facing south        $forwardSouth
  |   moves backward facing south       $backwardSouth
  |   moves forward facing west         $forwardWest
  |   moves backward facing west        $backwardWest

  |  The terrain should correclty handle moves over boundaries
  |   moves over top boundary           $overTheTop
  |   moves over left boundary          $overTheLeft
  |   moves over bottom boundary        $overTheBottom
  |   moves over right boundary         $overTheRight
                                 """

  def boundsCheck = {
    def sadCreate: Terrain2D = {
      Terrain2D(0, 0)
    }

    sadCreate must throwA[java.lang.AssertionError]

    def sadCreate2: Terrain2D = {
      Terrain2D(-10, -9)
    }

    sadCreate2 must throwA[java.lang.AssertionError]
  }

  def forwardNorth = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = North

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 3
  }

  def backwardNorth = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = North

    val newPos = terrain.performMove(initialPos, MoveBackward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 5
  }

  def forwardEast = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = East

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 3
    newPos.y mustEqual 4
  }

  def backwardEast = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = East

    val newPos = terrain.performMove(initialPos, MoveBackward(orientation))
    newPos.x mustEqual 5
    newPos.y mustEqual 4
  }

  def forwardSouth = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = South

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 5
  }

  def backwardSouth = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = South

    val newPos = terrain.performMove(initialPos, MoveBackward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 3
  }

  def forwardWest = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = West

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 3
    newPos.y mustEqual 4
  }

  def backwardWest = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = West

    val newPos = terrain.performMove(initialPos, MoveBackward(orientation))
    newPos.x mustEqual 5
    newPos.y mustEqual 4
  }

  def overTheTop = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 0)
    val orientation = North

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 9
  }

  def overTheBottom = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 9)
    val orientation = South

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 0
  }

  def overTheRight = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(9, 4)
    val orientation = East

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 0
    newPos.y mustEqual 4
  }

  def overTheLeft = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(0, 4)
    val orientation = West

    val newPos = terrain.performMove(initialPos, MoveForward(orientation))
    newPos.x mustEqual 9
    newPos.y mustEqual 4
  }
}