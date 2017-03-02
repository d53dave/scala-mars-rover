package net.d53dev.scalamarsrover
/**
  * Created by dsere on 28/02/2017.
  */
class Rover(val terrain: Terrain[_], val position: Position) {
  def move(command: RoverCommand): Rover = {
    ???
  }
}

object Rover {
  def apply(terrain: Terrain[_], position: Position) = new Rover(terrain, position)
}

