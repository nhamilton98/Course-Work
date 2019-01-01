#include "myUtils.h"

void strip(char * array)
{
	if (array == NULL)
	{
		perror("Invalid parameter.");
		exit(-99);
	}

	int len = strlen(array), x = 0;

	while (array[x] != '\0' && x < len)
	{
		if (array[x] == '\r')
			array[x] = '\0';
		else if (array[x] == '\n')
			array[x] = '\0';

		x++;
	}
}
