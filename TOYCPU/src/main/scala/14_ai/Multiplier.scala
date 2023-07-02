package ai
import chisel3._
import chisel3.util._

class Multiplier(val M: Int, 
                 val N: Int,
                 val K: Int
                 ) extends Module {
  val io = IO(new Bundle {
    val mma_start = Input(Bool())
    val vecA = Input(Vec(M * K, UInt(8.W)))
    val vecB = Input(Vec(K * N, UInt(8.W)))
    val vecC = Input(Vec(M * N, UInt(32.W)))
    val vecD =Output(Vec(M * N, UInt(32.W)))
    val mma_done = Output(Bool())
  })
  // when(io.mma_working) {
  //    for (i <- 0 until M) {
  //      for (j <- 0 until N) {
  //        sum := 0.U;
  //        for (k <- 0 until K) {
  //          sum := io.vecA(i * K + k) * io.vecB(k * N + j) + sum

  //          if(i == M-1 && j == N-1 && k == K-1) {
  //             io.mma_done := true.B
  //          }else{
  //             io.mma_done := false.B
  //          }
  //        }
  //        val index = i * N + j
  //        io.vecD(i * N + j) := io.vecC(index) + sum
  //      }
  //    }
  // }.otherwise{
  //    for (m <- 0 until M) {
  //      for (n <- 0 until N) {
  //        val index = m * N + n
  //        io.vecD(index) := 0.U
  //      }
  //    }
  // }



   // 定义状态机的状态
  val sIdle :: sCalc :: sDone :: Nil = Enum(3)
  // 定义状态寄存器
  val state = RegInit(sIdle)

  // 定义计数器和累加器
  val i = RegInit(0.U(log2Ceil(M).W))
  val j = RegInit(0.U(log2Ceil(N).W))
  val k = RegInit(0.U(log2Ceil(K).W))
  val sum = RegInit(0.U(32.W))

  // 定义输出端口的默认值
  io.vecD := VecInit(Seq.fill(M * N)(0.U))
  io.mma_done := false.B

  // 根据状态机的逻辑进行计算
  switch(state) {
    is(sIdle) {
      when(io.mma_start) {
        state := sCalc // 当输入端口mma_start为真时，进入计算状态
      }
    }
    is(sCalc) {
      
      sum := io.vecA(i * K.asUInt + k) * io.vecB(k * N.asUInt + j) + sum // 计算矩阵乘法的部分和
      when(k === (K - 1).U) { // 当k达到最大值时，更新输出端口vecD的对应元素，并将sum清零
        val index = i * N.asUInt + j
        io.vecD(index) := io.vecC(index) + sum
        sum := 0.U
      }
      when(i === (M - 1).U && j === (N - 1).U && k === (K - 1).U) { // 当i,j,k都达到最大值时，进入完成状态，并将输出端口mma_done置为真
        state := sDone
        io.mma_done := true.B
      }.otherwise { // 否则，更新计数器i,j,k的值，每个周期更新一个计数器，保证每个周期只计算一个部分和
        k := k + 1.U
        when(k === (K - 1).U) {
          j := j + 1.U
          when(j === (N - 1).U) {
            i := i + 1.U
          }
        }
      }
    }
    is(sDone) {
      state := sIdle // 当完成状态时，返回空闲状态，等待下一次输入信号
    }
  }
}
