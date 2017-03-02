package net.d53dev.scalamarsrover

sealed trait RoverCommand

case object Forward   extends RoverCommand
case object Backward  extends RoverCommand
case object Left      extends RoverCommand
case object Right     extends RoverCommand
