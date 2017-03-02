package net.d53dev.scalamarsrover

/**
  * Created by dsere on 28/02/2017.
  */

trait Terrain[Pos <: Position] {
  def performMove(fromPosition: Pos, move: Move): Pos
}

class Terrain2D(width: Long, height: Long) extends Terrain[Position2D] {
  assert(width > 0 && height > 0, "Both dimensions must be > 0")

  override def performMove(fromPosition: Position2D, move: Move): Position2D = {
    val moveDirectionVal = move match {
      case MoveBackward(_) => -1
      case MoveForward(_)  =>  1
    }
    move.orientation match {
      case North | South => Position2D(fromPosition.x, addAndWrapVertical(fromPosition.y, moveDirectionVal))
      case East  | West  => Position2D(addAndWrapHorizontal(fromPosition.y, moveDirectionVal), fromPosition.y)
    }
  }

  private def addAndWrapVertical(start: Long, offset: Long): Long = {
    (start + offset) % this.height
  }

  private def addAndWrapHorizontal(start: Long, offset: Long): Long = {
    (start + offset) % this.width
  }
}
