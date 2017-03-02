package spatial

sealed trait Orientation

case object North extends Orientation
case object East  extends Orientation
case object South extends Orientation
case object West  extends Orientation
