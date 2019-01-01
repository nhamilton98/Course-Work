#ifndef FILEUTILS_H
#define FILEUTILS_H

#include <stdio.h>
#include <stdlib.h>

#define MAX 100

FILE * openInputFile_Prompt();
FILE * openInputFileType_Prompt(char * type);
int countRecords(FILE * fin, int per);
int readTotal(FILE * fin);

#endif
