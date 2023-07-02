package ai 

import chisel3._

class Top extends Module {
  val io = IO(new Bundle {
    val exit = Output(Bool())
  })
  val core = Module(new Core())
  val memory = Module(new Memory())
  core.io.imem <> memory.io.imem
  core.io.dmem <> memory.io.dmem
  // core.io.amem <> memory.io.amem
  // core.io.bmem <> memory.io.bmem
  // core.io.cmem <> memory.io.cmem
  io.exit := core.io.exit
}

object TOYCPU extends App {
  (new chisel3.stage.ChiselStage).emitVerilog (new Top, args)
}