#include "fileUtils.h"
#include "myUtils.h"

FILE * openInputFile_Prompt()
{
	char temp[MAX];
	printf("Enter the name of the input file: ");
	fgets(temp, 100, stdin);
	strip(temp);
	FILE * fin = fopen(temp, "r");

	while (fin == NULL)
	{
		printf("Enter the name of the input file: ");
        	fgets(temp, MAX, stdin);
        	strip(temp);
        	FILE * fin = fopen(temp, "r");
	}

	return fin;
}

FILE * openInputFileType_Prompt(char * type)
{
	char temp[MAX];
	printf("Enter the name of the %s input file: ", type);
	fgets(temp, MAX, stdin);
	strip(temp);
	FILE * fin = fopen(temp, "r");

	while (fin == NULL)
	{
		printf("Enter the name of the %s file: ", type);
        	fgets(temp, 100, stdin);
        	strip(temp);
        	FILE * fin = fopen(temp, "r");
	}

	return fin;
}

int countRecords(FILE * fin, int per)
{
	if (fin == NULL || per <= 0)
	{
		perror("Invalid parameter.");
		exit(-99);
	}

	int count = 0;
	char temp[MAX];
	fgets(temp, MAX, fin);

	while (!feof(fin))
	{
		count++;
		fgets(temp, MAX, fin);
	}

	if (count == 0)
	{
		perror("Empty file.");
		exit(-99);
	}

	rewind(fin);

	return count / per;
}

int readTotal(FILE * fin)
{
	if (fin == NULL)
	{
		perror("Invalid parameter.");
		exit(-99);
	}

	int total;
	char temp[MAX];
	fgets(temp, MAX, fin);

	total = atoi(temp);

	if (total <= 0)
	{
		perror("Invalid total.");
		exit(-99);
	}

	return total;
}
