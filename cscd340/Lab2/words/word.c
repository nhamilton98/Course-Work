#ifndef WORD_H_
#define WORD_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../utils/myUtils.h"
/**
 * @brief The word data structure.
 *
 * A word contains an array of letter and an int for the length.
 *
 * @note I prefer named structures and then the typedef after the structure
 */
struct word
{
   char * ltrs;
   int len;
};

typedef struct word Word;


/**
 * @brief Cleans up all dynamically allocated memory for the word
 *
 * Cleans up and frees all the dynamically allocated memory 
 * In this case ltrs
 *
 * Each pointer in the specific data type is set to NULL after it has been freed.
 *
 * @param passedIn - The void * passed in representing the specific word
 *
 * @warning - The passed in void * passedIn is checked - exit(-99) if NULL
 */
void cleanTypeWord(void * ptr)
{
	if (ptr == NULL)
	{
		printf("Invalid parameter.");
		exit(-99);
	}

	Word * temp = (Word *) ptr;

	free(temp -> ltrs);
	temp -> ltrs = NULL;
	temp -> len = 0;

	free(temp);
	temp = NULL;
}

/**
 * @brief Builds and returns a single word.
 *
 * Reads the initial data from the file and then builds
 * an object of type word.
 *
 * @note The carriage return will be removed when reading from the file
 *
 * @param fin - The FILE * representing the open file
 * @return void * - Representing a word object
 *
 * @warning - The passed in FILE * fin is checked - exit(-99) if NULL
 */
void * buildTypeWord(FILE * fin)
{
	if (fin == NULL)
	{
		printf("Invalid input.");
		exit(-99);
	}

	char temp[MAX];
	Word * word = (Word *) calloc(1, sizeof(Word));

	fgets(temp, MAX, fin);
	strip(temp);

	int length = strlen(temp);

	word -> ltrs = (char *) calloc(length + 1, sizeof(char));
	strcpy(word -> ltrs, temp);
	word -> len = length;

	return (void *) word;
}

/**
 * @brief Prints the specific word.
 *
 * Format is word - length
 *
 * @param passedIn - The void * passed in representing a specific word
 *
 * @warning - The passed in void * passedIn is checked - exit(-99) if NULL
 */
void printTypeWord(void * passedIn)
{
	if (passedIn == NULL)
	{
		printf("Invalid parameter.");
		exit(-99);
	}

	Word * temp = (Word *) passedIn;

	printf("%s - %d\n", temp -> ltrs, (int) strlen(temp -> ltrs));
}

/**
 * @brief Builds and returns a single word.
 *
 * Reads the initial data from the keyboard by prompting the user.
 * A word object is constructed.
 *
 * @note The carriage return will be removed when reading from the keyboard
 *
 * @param fin - The FILE * representing stdin
 * @return void * - Representing a word object
 *
 * @warning - The passed in FILE * fin is checked - exit(-99) if NULL
 */
void * buildTypeWord_Prompt(FILE * fin)
{
	if (fin == NULL)
	{
		printf("Invalid input.");
		exit(-99);
	}

	char temp[MAX];
	Word * word = (Word *) calloc(1, sizeof(Word));

	printf("Enter a word: ");
	fgets(temp, MAX, fin);
	strip(temp);

	int length = strlen(temp);

	word -> ltrs = (char *) calloc(length + 1, sizeof(char));
	strcpy(word -> ltrs, temp);
	word -> len = length;

	return (void *) word;
}

/**
 * @brief Compares two objects of type word.
 *
 * Words are compared based on the letters (word)
 *
 * @note The passed in items will need to be cast to the appropriate word type.
 *
 * @param p1 - The void * representing an object of type word
 * @param p2 - The void * representing an object of type word
 * @return int - Representing order < 0 indicates p1 is less than p2,
 * > 0 indicates p1 is greater than p2 and == 0 indicates p1 == p2 for contents
 *
 * @warning - The passed in void * p1 is checked - exit(-99) if NULL
 * @warning - The passed in void * p2 is checked - exit(-99) if NULL
 */
int compareWord(const void * p1, const void * p2)
{
	if (p1 == NULL || p2 == NULL)
	{
		printf("Invalid parameter.");
		exit(-99);
	}

	Word * w1 = (Word *) p1;
	Word * w2 = (Word *) p2;

	return strcmp(w1 -> ltrs, w2 -> ltrs);
}


#endif /* WORD_H_ */
