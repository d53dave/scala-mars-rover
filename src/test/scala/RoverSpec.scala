package net.d53dev.scalamarsrover

import org.specs2.Specification
import org.specs2.mock.Mockito


class RoverSpec extends Specification with Mockito { def is = s2"""

  The rover should correctly rotate
  |  single rotations               $singleRotation
  |  multiple rotations             $multiRotation

  The rover should pass the correct move objects to the terrain
  | when moving forward             $forwardCheck
  | when moving backward            $backwardCheck
                                 """

  def singleRotation = ???
  def multiRotation = ???
  def forwardCheck = ???
  def backwardCheck = ???
}
