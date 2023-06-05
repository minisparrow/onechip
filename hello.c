#include <stdio.h>
static void ebreak(long int arg0, long int arg1) {
    __asm__ __volatile__ (
                 "addi a0, x0, %0;\n"
                 "addi a1, x0, %1;\n"
                 "ebreak" 
                 : 
                 : "i"(arg0), "i"(arg1));
}

static void putch(char ch) {
    ebreak(0, ch);
}

static _Noreturn void halt(int code) {
    ebreak(1, code);
    while(1);
}


void _start() {
    putch('H'); putch('e'); putch('l'); putch('l'); putch('o'); putch(','); putch(' ');
    putch('R'); putch('I'); putch('S'); putch('C'); putch('-'); putch('V'); putch('!');
    putch('\n');
    halt(0);
            
}
