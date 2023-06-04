#include <stdio.h>
#include "VYEMU.h"
#include "VYEMU___024root.h"
static VYEMU *top = NULL;

void step() { top->clock = 0; top->eval(); top->clock = 1; top->eval();  }
void reset(int n) { top->reset = 1; while (n --) { step();  } top->reset = 0;  }
void load_prog(const char *bin) {
   FILE *fp = fopen(bin, "r");
   // fread(&top->rootp->YEMU__DOT__M_ext__DOT__Memory, 1, 1024, fp);
   fread(&top->rootp->YEMU__DOT__M, 1, 1024, fp);
   fclose(fp);
}

int main(int argc, char *argv[]) {
    top = new VYEMU;
    load_prog(argv[1]);
    reset(10);
    while (!top->io_halt) { step();  }
    return 0;
              
}
