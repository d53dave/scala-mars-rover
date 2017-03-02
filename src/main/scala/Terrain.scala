package net.d53dev.scalamarsrover
/**
  * Created by dsere on 28/02/2017.
  */
trait Terrain[Pos <: Position] {
  def performMove(fromPosition: Pos, move: Move): Pos
}

class Terrain2D(width: Long, height: Long) extends Terrain[Position2D] {
  override def performMove(fromPosition: Position2D, move: Move): Position2D = ???
}
