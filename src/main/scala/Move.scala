package net.d53dev.scalamarsrover
/**
  * Created by dsere on 28/02/2017.
  */
abstract class Move(orientation: Orientation)


case class MoveForward(orientation: Orientation) extends Move(orientation)
case class MoveBackward(orientation: Orientation) extends Move(orientation)

