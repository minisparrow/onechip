#include <iostream>

const int ARRAY_SIZE = 64;
void saveToHex(uint8_t* buf, const char* hexFileName) {
     FILE *hex_file = fopen(hexFileName, "w");
     if (hex_file == NULL)
     {
         printf("Error opening file\n");
     }
     for (int i = 0; i < ARRAY_SIZE; i++)
     {
         char hex_data[5];
         sprintf(hex_data, "%02X", buf[i]); // convert to hex string
         fprintf(hex_file, "%s\n", hex_data); // write to file
     }
     fclose(hex_file);
}
void save32bToHex(uint32_t* buf, const char* hexFileName) {
     FILE *hex_file = fopen(hexFileName, "w");
     if (hex_file == NULL)
     {
         printf("Error opening file\n");
     }
     for (int i = 0; i < ARRAY_SIZE; i++)
     {
         char hex_data[5];
         sprintf(hex_data, "%04X", buf[i]); // convert to hex string
         fprintf(hex_file, "%s\n", hex_data); // write to file
     }
     fclose(hex_file);
}

int main()
{
    uint8_t abuf[64];
    uint8_t bbuf[64];
    uint32_t cbuf[64];

    for(int i = 0; i < ARRAY_SIZE; i++) {
        abuf[i] = i;
        bbuf[i] = i;
        cbuf[i] = 0;
    }
    saveToHex(abuf, "abuf.hex");
    saveToHex(bbuf, "bbuf.hex");
    save32bToHex(cbuf, "cbuf.hex");
    return 0;
}
