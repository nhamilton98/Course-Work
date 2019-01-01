#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdint.h>

#include "./utils/fileUtils.h"
#include "./utils/myUtils.h"

struct pte
{
	int frames;
	int present;
};
typedef struct pte PTE;

struct pf
{
	int mappedTo;
};
typedef struct pf PF;

int main()
{
	int vas, pas, frames, size, x, total = 0, mod = 0;
	uint32_t va, pa;

	FILE * fin = fopen("setup.txt", "r");
	fscanf(fin, "%d", &vas);
	fscanf(fin, "%d", &size);
	fscanf(fin, "%d", &pas);

	fclose(fin);

	FILE * input = openInputFile_Prompt();
	total = countRecords(input, 1);

	PTE * pte = (PTE *) calloc(vas, sizeof(PTE));
	PF * pf = (PF *) calloc(pas, sizeof(PF));

	frames = pas / size;

	int page, sizeBits;
	for (x = 0; x < total; x++)
	{
		sizeBits = log(size)/log(2);

		fscanf(input, "%i", &va);
		page = (va >> sizeBits);

		if (pte[page].present == 1)
			pf[pte[page].frames].mappedTo = page;
		else
		{
			pte[page].present = 1;
			pte[page].frames = mod % frames;
			pf[pte[page].frames].mappedTo = page;

			mod++;
		}

		pa = pte[page].frames * size + (va & ((1 << sizeBits) - 1));

		printf("Virtual Address: %d\n", va);
		printf("Page Number: %d\n", page);
		printf("Page Frame: %d\n", pte[page].frames);
		printf("Physical Address: %d\n\n", pa);
	}

	free(pte);
	free(pf);

	fclose(input);

	return 0;
}
