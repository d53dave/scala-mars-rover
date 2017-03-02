import org.specs2.Specification

/**
  * Created by dsere on 02/03/2017.
  */
class TerrainSpec extends Specification { def is = s2"""
  Basic assumptions
  |   The terrain must be 1x1 or larger $boundsCheck

  The terrain should correctly handle moves
  |   moves forward facing north        $forwardNorth
  |   moves backward facing north       $backwardNorth
  |   moves forward facing east         $forwardEast
  |   moves backward facing east        $backwardEast
  |   moves forward facing south        $forwardSouth
  |   moves backward facing south       $backwardSouth
  |   moves forward facing west         $forwardWest
  |   moves backward facing west        $backwardWest
                                 """

  def boundsCheck = ???

  def forwardNorth = ???
  def backwardNorth = ???
  def forwardEast = ???
  def backwardEast = ???
  def forwardSouth = ???
  def backwardSouth = ???
  def forwardWest = ???
  def backwardWest = ???
}