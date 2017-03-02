package net.d53dev.scalamarsrover

import org.specs2.Specification

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

    val newPos = terrain.performMove(initialPos, new MoveForward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 3
  }

  def backwardNorth = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = North

    val newPos = terrain.performMove(initialPos, new MoveBackward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 5
  }

  def forwardEast = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = East

    val newPos = terrain.performMove(initialPos, new MoveForward(orientation))
    newPos.x mustEqual 3
    newPos.y mustEqual 4
  }

  def backwardEast = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = East

    val newPos = terrain.performMove(initialPos, new MoveBackward(orientation))
    newPos.x mustEqual 5
    newPos.y mustEqual 4
  }

  def forwardSouth = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = South

    val newPos = terrain.performMove(initialPos, new MoveForward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 5
  }

  def backwardSouth = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = South

    val newPos = terrain.performMove(initialPos, new MoveBackward(orientation))
    newPos.x mustEqual 4
    newPos.y mustEqual 3
  }

  def forwardWest = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = West

    val newPos = terrain.performMove(initialPos, new MoveForward(orientation))
    newPos.x mustEqual 3
    newPos.y mustEqual 4
  }

  def backwardWest = {
    val terrain = Terrain2D(10, 10)
    val initialPos = Position2D(4, 4)
    val orientation = West

    val newPos = terrain.performMove(initialPos, new MoveBackward(orientation))
    newPos.x mustEqual 5
    newPos.y mustEqual 4
  }
}