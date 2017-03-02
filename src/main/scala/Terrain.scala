package net.d53dev.scalamarsrover

import spatial._


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
  * The class will perform an assertion over the passed in width and heights, since having 0 or negative
  * dimensions here is not a recoverable situation.
  *
  * @param width
  * @param height
  */
class Terrain2D(width: Long, height: Long) extends Terrain[Position2D] {
  assert(width > 0 && height > 0, "Both dimensions must be > 0")

  override def performMove(fromPosition: Position2D, move: Move): Position2D = {

    val moveDelta = move match {
      case MoveBackward(o) =>  if (southOrEast(o)) -1 else  1
      case MoveForward(o)  =>  if (southOrEast(o))  1 else -1
    }


    move.orientation match {
      case North | South => Position2D(fromPosition.x, addAndWrapVertical(fromPosition.y, moveDelta))
      case East  | West  => Position2D(addAndWrapHorizontal(fromPosition.x, moveDelta), fromPosition.y)
    }
  }

  private def addAndWrapVertical(start: Long, offset: Long): Long = {
    (start + offset) % this.height
  }

  private def addAndWrapHorizontal(start: Long, offset: Long): Long = {
    (start + offset) % this.width
  }

  private def southOrEast(orientation: Orientation) = orientation == South || orientation == East
}

object Terrain2D {
  def apply(w: Long, h: Long) = new Terrain2D(w, h)
}
