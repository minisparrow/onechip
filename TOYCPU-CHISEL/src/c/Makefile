%: %.c
	riscv64-linux-gnu-gcc -O2 -march=rv64g -c -o $@.o $<
	riscv64-linux-gnu-ld -b elf64-littleriscv $@.o -T link.ld -o $@
	riscv64-linux-gnu-objcopy -O binary $@ $@.bin
	od -An -tx1 -w1 -v $@.bin > ../hex/$@.hex   
	riscv64-linux-gnu-objdump -b elf64-littleriscv -D $@ > ../dump/$@.elf.dmp
	rm -f $@.o
	rm -f $@
	rm -f $@.bin

clean:
	rm *.o *.bin
