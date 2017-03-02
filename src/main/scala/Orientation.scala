package net.d53dev.scalamarsrover
/**
  * Created by dsere on 28/02/2017.
  */
sealed trait Orientation

case object North extends Orientation
case object East  extends Orientation
case object South extends Orientation
case object West  extends Orientation
