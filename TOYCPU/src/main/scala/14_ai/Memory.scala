package ai

import chisel3._
import chisel3.util._
import common.Consts._
import chisel3.util.experimental.loadMemoryFromFile

class ImemPortIo extends Bundle {
  val addr = Input(UInt(WORD_LEN.W))
  val inst = Output(UInt(WORD_LEN.W))
}

class DmemPortIo extends Bundle {
  val addr  = Input(UInt(WORD_LEN.W))
  val rdata = Output(UInt(WORD_LEN.W))
  val wen   = Input(Bool())
  val wdata = Input(UInt(WORD_LEN.W))
}

class AmemPortIo extends Bundle {
  val addr  = Input(UInt(WORD_LEN.W))
  val rdata = Output(UInt(BYTE_LEN.W))
  val wen   = Input(Bool())
  val wdata = Input(UInt(BYTE_LEN.W))
}

class BmemPortIo extends Bundle {
  val addr  = Input(UInt(WORD_LEN.W))
  val rdata = Output(UInt(BYTE_LEN.W))
  val wen   = Input(Bool())
  val wdata = Input(UInt(BYTE_LEN.W))
}

class CmemPortIo extends Bundle {
  val addr  = Input(UInt(WORD_LEN.W))
  val rdata = Output(UInt(WORD_LEN.W))
  val wen   = Input(Bool())
  val wdata = Input(UInt(WORD_LEN.W))
}

class Memory extends Module {
  val io = IO(new Bundle {
    val imem = new ImemPortIo()
    val dmem = new DmemPortIo()
    // val amem = new AmemPortIo()
    // val bmem = new BmemPortIo()
    // val cmem = new CmemPortIo()
  })

  val mem  = SyncReadMem(4096, UInt(8.W))  
  // val abuf = SyncReadMem(64, UInt(8.W)) // 512 x 8
  // val bbuf = SyncReadMem(64, UInt(8.W))
  // val cbuf = SyncReadMem(64, UInt(32.W))

  // loadMemoryFromFile(abuf, "src/hex/abuf.hex")
  // loadMemoryFromFile(bbuf, "src/hex/bbuf.hex")
  // loadMemoryFromFile(cbuf, "src/hex/cbuf.hex")
  loadMemoryFromFile(mem, "src/hex/ai.hex")
  io.imem.inst := Cat(
    mem(io.imem.addr + 3.U(WORD_LEN.W)), 
    mem(io.imem.addr + 2.U(WORD_LEN.W)),
    mem(io.imem.addr + 1.U(WORD_LEN.W)),
    mem(io.imem.addr)
  )
  io.dmem.rdata := Cat(
    mem(io.dmem.addr + 3.U(WORD_LEN.W)),
    mem(io.dmem.addr + 2.U(WORD_LEN.W)), 
    mem(io.dmem.addr + 1.U(WORD_LEN.W)),
    mem(io.dmem.addr)
  )
  when(io.dmem.wen){
    mem(io.dmem.addr)                   := io.dmem.wdata( 7,  0)
    mem(io.dmem.addr + 1.U(WORD_LEN.W)) := io.dmem.wdata(15,  8)
    mem(io.dmem.addr + 2.U(WORD_LEN.W)) := io.dmem.wdata(23, 16)
    mem(io.dmem.addr + 3.U(WORD_LEN.W)) := io.dmem.wdata(31, 24)
  }

  // io.amem.rdata := abuf(io.amem.addr)
  // io.bmem.rdata := bbuf(io.bmem.addr)
  // io.cmem.rdata := cbuf(io.cmem.addr)
  // when(io.amem.wen){
  //   abuf(io.amem.addr) := io.amem.wdata
  // }
  // when(io.bmem.wen){
  //   bbuf(io.bmem.addr) := io.bmem.wdata
  // }
  // when(io.cmem.wen){
  //   cbuf(io.cmem.addr) := io.cmem.wdata
  // }
}
