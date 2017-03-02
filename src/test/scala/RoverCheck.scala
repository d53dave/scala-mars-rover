package net.d53dev.scalamarsrover

import org.scalacheck.Gen.{choose, listOf}
import org.scalacheck.{Gen, Prop}
import org.specs2.{ScalaCheck, Specification}

/**
  * Created by dsere on 28/02/2017.
  */


class RoverCheck extends Specification with ScalaCheck { def is =
  s2"""
       The Rover should, after arbitrary moves, still be on a valid position on the terrain $t1
  """

  def t1 = Prop.forAll(genInitialState) { case (width, height, x, y, orientation) =>
    Prop.forAll(commandListGen) {
      commands =>
        val terrain = new Terrain2D(width, height)
        val startPos =  new Position2D(x, y)
        val roverAtLastPos = commands.foldLeft(Rover(terrain, startPos, orientation))(
          (rover, cmd) => rover.move(cmd)
        )

        val lastPos = roverAtLastPos.position.asInstanceOf[Position2D]

        (lastPos.x must beLessThan(width)) && (lastPos.y must beLessThan(height))
    }
  }

  val genInitialState: Gen[(Long, Long, Long, Long, Orientation)] = for {
    width  <- choose(0, 1000)
    height <- choose(0, 1000)
    startX <- choose(0, width - 1)
    startY <- choose(0, height - 1)
    startOrientation <- Gen.oneOf(North, East, South, West)
  } yield (width, height, startX, startY, startOrientation)

  val commandListGen: Gen[List[RoverCommand]] = listOf(Gen.oneOf(Backward, Forward, Left, Right))

}
