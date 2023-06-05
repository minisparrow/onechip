riscv64-unknown-elf-gcc hello.c -march=rv64g -ffreestanding -nostdlib -static -Wl,-Ttext=0 -O2
llvm-objcopy-10 -j .text -O binary a.out prog.bin
