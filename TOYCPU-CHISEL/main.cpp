#include <stdio.h>
#include "obj_dir/VTop.h""
#include "obj_dir/VTop___024root.h""
static VTop *top = NULL;

int counter = 0;

void step() { 
    top->clock = 0; 
    top->eval(); 
    top->clock = 1; 
    counter++; 
    top->eval();  
}


void reset(int n) { top->reset = 1; while (n --) { step();  } top->reset = 0;  }
void load_prog(const char *bin) {
   FILE *fp = fopen(bin, "r");
   fread(&top->rootp->Top__DOT__memory__DOT__mem, 1, 1024, fp);
   fclose(fp);
}

int main(int argc, char *argv[]) {
    top = new VTop;
    load_prog(argv[1]);
    reset(10);
    while (!top->io_exit && counter < 1000) { step();  }
    return 0;
              
}
