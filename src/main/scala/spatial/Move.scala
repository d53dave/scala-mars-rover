package spatial

/** This class represents a move that the Rover wants to perform on it's underlying terrain
  *
  * @param orientation Representing the direction that the rover is facing when doing the move
  */
abstract class Move(val orientation: Orientation)


case class MoveForward(override val orientation: Orientation) extends Move(orientation)
case class MoveBackward(override val orientation: Orientation) extends Move(orientation)
