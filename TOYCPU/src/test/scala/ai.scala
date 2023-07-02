package ai 

import chisel3._
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._
// import chiseltest.internal.MaxSupportedMemorySize
import chiseltest.internal.WriteVcdAnnotation
class HexTest extends FlatSpec with ChiselScalatestTester {
  "mycpu" should "work through hex" in {
    test(new Top).withAnnotations(Seq(WriteVcdAnnotation))
    { c =>
      while (!c.io.exit.peek().litToBoolean){
        c.clock.step(1)
      }
    }
  }
}
//withAnnotations(Seq(MaxSupportedMemorySize(32768)))