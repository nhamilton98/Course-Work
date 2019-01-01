#include "makeArgs.h"

void clean(int argc, char **argv)
{
	int x = 0;

	for (x; x < argc; x++)
	{
		free(argv[x]);
		argv[x] = NULL;
	}

	free(argv);
	argv = NULL;
	argc = 0;
}// end clean

void printargs(int argc, char **argv)
{
	int x;
	for(x = 0; x < argc; x++)
		printf("%s\n", argv[x]);

}// end printargs

int makeargs(char *s, char *** argv)
{
	char copy[MAX];
	strcpy(copy, s);

	int count = 0;
	char * ptr;
	char * token = strtok_r(s, " ", &ptr);
	strip(token);

	while (token != NULL)
	{
		count++;
		token = strtok_r(NULL, " ", &ptr);
	}

	if (count <= 0)
		return -1;

	*argv = (char **) calloc(count, sizeof(char *));
	token = strtok_r(copy, " ", &ptr);
	strip(token);

	(*argv)[0] = (char *) calloc(strlen(token) + 1, sizeof(char));
	strcpy((*argv)[0], token);
	int x = 1;

	for(x; x < count; x++)
	{
		token = strtok_r(NULL, " ", &ptr);
		strip(token);
		(*argv)[x] = (char *) calloc(strlen(token) + 1, sizeof(char));
		strcpy((*argv)[x], token);
	}
}// end makeArgs
