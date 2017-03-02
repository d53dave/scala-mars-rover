package net.d53dev.scalamarsrover
/**
  * Created by dsere on 28/02/2017.
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
  def apply[T](terrain: Terrain[T], position: T, orientation: Orientation) =
    new Rover(terrain, position, orientation)
}

