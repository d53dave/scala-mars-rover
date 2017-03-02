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

  def t1 = Prop.forAll(genTerrainSize) { case (width, height) =>
    Prop.forAll(commandListGen) {
      commands =>
        val roverAtLastPos = commands.foldLeft(Rover(new Terrain2D(width, height), new Position2D(0, 0), North))(
          (rover, cmd) => rover.move(cmd)
        )

        val lastPos = roverAtLastPos.position.asInstanceOf[Position2D]

        (lastPos.x must beLessThan(width)) && (lastPos.y must beLessThan(height))
    }
  }

  val genTerrainSize: Gen[(Long, Long)] = for {
    a <- choose(0, 1000)
    b <- choose(0, 1000)
  } yield (a, b)

  val commandListGen: Gen[List[RoverCommand]] = listOf(oneOf(Backward, Forward, Left, Right))

}
