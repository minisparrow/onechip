0rvcpu:
	sbt run
	verilator --cc --exe main.cpp YEMU.v
	cd obj_dir && make -f VYEMU.mk
	cd ..

1binary:
	riscv64-linux-gnu-gcc apps/hello.c -march=rv64g -ffreestanding -nostdlib -static -Wl,-Ttext=0 -O2
	llvm-objcopy-10 -j .text -O binary a.out prog.bin

2run:
	./obj_dir/VYEMU ./prog.bin 

clean:
	rm *.v *.json *.fir 
	rm *.out *.bin
	rm -rf obj_dir

