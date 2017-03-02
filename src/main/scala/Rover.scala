package net.d53dev.scalamarsrover

/** This class represents the main entity of this exercise: the Rover.
  * It is immutable: moving the rover returns a new instance.
  *
  * The rover does not know how to manipulate coordinates, which it delegates to the
  * underlying Terrain instance. It communicates an intended move to the Terrain,
  * which returns the position after the move is completed
  *
  * @param terrain The terrain on which the rover moves. This accepts
  * @param position The current position of the rover
  * @param orientation The orientation that the rover is facing towards
  * @tparam T The Rover is parametrized over the type of positions (i.e. Position2D, 3D, etc.) it supports.
  */
class Rover[T <: Position](val terrain: Terrain[T], val position: T, val orientation: Orientation) {
  def move(command: RoverCommand): Rover[T] = {
    command match {

      /* Translating */
      case Backward => {
        val move = MoveBackward(this.orientation)
        val newPos = terrain.performMove(this.position, move)
        Rover(terrain, newPos, this.orientation)
      }
      case Forward  => {
        val move = MoveForward(this.orientation)
        Rover(terrain, terrain.performMove(this.position, move), this.orientation)
      }

      /* Rotating */
      case Left     => Rover[T](terrain, position, rotateLeft)
      case Right    => Rover[T](terrain, position, rotateRight)
    }
  }

  private def rotateLeft = {
    this.orientation match {
      case North => West
      case East  => East
      case South => South
      case West  => North
    }
  }

  private def rotateRight = {
    this.orientation match {
      case North => East
      case East  => South
      case South => West
      case West  => North
    }
  }
}

object Rover {
  def apply[T <: Position](terrain: Terrain[T], position: T, orientation: Orientation) =
    new Rover[T](terrain, position, orientation)
}

