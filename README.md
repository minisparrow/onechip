
Work flow for run simple simulator writen by chisel

```
-1.  YEMU-CHISEL/gen_verilog.sh

-2.  cd obj_dir && make -f VYEMU.mk

-3.  ./generate_progbin.sh

-4.  ./YEMU/obj_dir/VYEMU prog.bin
```

TOYCPU

1. run test directly in chisel 
```
sbt testOnly packageName.testClassName
```

for example: 

```
sbt "testOnly riscvtest.RiscvTest"

```

2. run test after compile chisel -> verilog -> cpp

```
make 0rvcpu
make 1binary APPFILE=add
make 2run APPFILE=src/riscv/rv32ui-p-sw.hex -> this will read error 
make 2run APPFILE=src/c/binary
make 2run APPFILE="bin file not hex file"
```
