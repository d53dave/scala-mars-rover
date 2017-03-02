package net.d53dev.scalamarsrover


/** This trait represents a Terrain that the [[Rover]] moves on.
  *
  * It has only one abstract method which models a move: it takes an initial position and an intended move.
  * Based on those, a terrain should return a new position
  *
  * @tparam Pos the type of positions (or rather coordinates) that the Terrain supports
  */
trait Terrain[Pos <: Position] {
  def performMove(fromPosition: Pos, move: Move): Pos
}

/** An implementation of the Terrain trait that models 2D geography.
  *
  * Note that this assumes the world is flat, and the topmost left corner is a [[Position2D]] at (0, 0)
  * This has the implication that anything that moves over one of the edges will disappear and reappear on the
  * opposing side of the world (i.e. it does not handle the case where moving north over the 'north pole'
  * would change the orientation to south as it should if the Terrain were to model a spherical geography
  * like, for example, a planet)
  *
  * @param width
  * @param height
  */
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

object Terrain2D {
  def apply(w: Long, h: Long) = new Terrain2D(w, h)
}
