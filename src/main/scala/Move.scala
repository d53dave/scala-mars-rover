package net.d53dev.scalamarsrover
/**
  * Created by dsere on 28/02/2017.
  */
abstract class Move(val orientation: Orientation)


case class MoveForward(override val orientation: Orientation) extends Move(orientation)
case class MoveBackward(override val orientation: Orientation) extends Move(orientation)

object Move {
  def apply[MoveForward](orientation: Orientation): MoveForward = new MoveForward(orientation)
  def apply[MoveBackward](orientation: Orientation): MoveBackward = new MoveBackward(orientation)
}
