#define _XOPEN_SOURCE
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<regex.h>
#include<unistd.h>
#include<sys/stat.h>
#include<crypt.h>

#define MAX_LENGTH 1000

int validate(const char*, const char*);
void strip(char*);

int main(int argc, char** argv)
{
	printf("%d\n", argc);
	for (int x = 0; x < argc; x++)
	{
		printf("%s\n", argv[x]);
	}
	printf("\n");

	char firstName[51];
	char lastName[51];
	long long int num1;
	long long int num2;
	char inputFilename[MAX_LENGTH];
	FILE* input;
	FILE* output;
	//FILE* password;
	FILE* error;

	char errorFilename[MAX_LENGTH] = "error.txt";
	if (access(errorFilename, 0) == 0)
		remove(errorFilename);
	error = fopen(errorFilename, "w");
	fprintf(error, "CodeDefender Errors Encountered:\n");

	int invalid = 1;
	char tempName[MAX_LENGTH];
	printf("Note: Names must be no larger than 50 characters.\n");
	printf("      A-Z, a-z, ', -, and space are permitted.\n");
	printf("      First letter must be uppercase.\n");
	printf("-------------------------------------------------\n");
	do
	{
		printf("FIRST NAME:\n");
		fgets(tempName, MAX_LENGTH, stdin);
		strip(tempName);

		if (validate(tempName, "name"))
		{
			strncpy(firstName, tempName, 51);
			invalid = 0;
		}
		else
		{
			printf("Invalid First Name.\n\n");
			fprintf(error, "Invalid First Name: %s\n", tempName);
		}
	} while (invalid);

	printf("\n");
	invalid = 1;
	do
	{
		printf("LAST NAME:\n");
		fgets(tempName, MAX_LENGTH, stdin);
		strip(tempName);

		if (validate(tempName, "name"))
		{
			strncpy(lastName, tempName, 51);
			invalid = 0;
		}
		else
		{
			printf("Invalid Last Name.\n\n");
			fprintf(error, "Invalid Last Name: %s\n", tempName);
		}
	} while (invalid);

	invalid = 1;
	char tempNum[MAX_LENGTH];
	printf("\n");
	printf("Note: Numbers must be integers between [-2147483648, 2147483647].\n");
	printf("-----------------------------------------------------------------\n");
	do
	{
		printf("NUMBER:\n");
		fgets(tempNum, MAX_LENGTH, stdin);
		strip(tempNum);

		if (validate(tempNum, "number"))
		{
			if (strtoll(tempNum, NULL, 0) >= -2147483648 && strtoll(tempNum, NULL, 0) <= 2147483647)
			{
				num1 = strtoll(tempNum, NULL, 0);
				invalid = 0;
			}
			else
			{
				printf("Invalid Number.\n\n");
				fprintf(error, "Invalid Number: %s\n", tempNum);
			}
		}
		else
		{
			printf("Invalid Number.\n\n");
			fprintf(error, "Invalid Number: %s\n", tempNum);
		}
	} while (invalid);

	invalid = 1;
	printf("\n");
	do
	{
		printf("ANOTHER NUMBER:\n");
		fgets(tempNum, MAX_LENGTH, stdin);
		strip(tempNum);

		if (validate(tempNum, "number"))
		{
			if (strtoll(tempNum, NULL, 0) >= -2147483648 && strtoll(tempNum, NULL, 0) <= 2147483647)
			{
				num2 = strtoll(tempNum, NULL, 0);
				invalid = 0;
			}
			else
			{
				printf("Invalid Number.\n");
				fprintf(error, "Invalid Number: %s\n", tempNum);
			}
		}
		else
		{
			printf("Invalid Number.\n");
			fprintf(error, "Invalid Number: %s\n", tempNum);
		}
	} while (invalid);

	invalid = 1;
	char tempFilename[MAX_LENGTH];
	printf("\n");
	printf("Note: File must reside in the current directory.\n");
	printf("      File must be of type text. (extension: .txt)\n");
	printf("--------------------------------------------------\n");
	do
	{
		printf("INPUT FILE:\n");
		fgets(tempFilename, MAX_LENGTH, stdin);
		strip(tempFilename);

		if (validate(tempFilename, "file"))
		{
			if (access(tempFilename, 0) == 0)
			{
				strncpy(inputFilename, tempFilename, MAX_LENGTH);
				input = fopen(tempFilename, "r");
				invalid = 0;
			}
			else
			{
				printf("Invalid Filename.\n\n");
				fprintf(error, "Invalid Input Filename: %s\n", tempFilename);
			}
		}
		else
		{
			printf("Invalid Filename.\n\n");
			fprintf(error, "Invalid Input Filename: %s\n", tempFilename);
		}
	} while (invalid);

	invalid = 1;
	printf("\n");
	do
	{
		printf("OUTPUT FILE:\n");
		fgets(tempFilename, MAX_LENGTH, stdin);
		strip(tempFilename);

		if (validate(tempFilename, "file"))
		{
			if (access(tempFilename, 0) == 0)
				remove(tempFilename);
			output = fopen(tempFilename, "w");
			invalid = 0;
		}
		else
		{
			printf("Invalid Filename.\n\n");
			fprintf(error, "Invalid Output Filename: %s\n",  tempFilename);
		}
	} while (invalid);

	int c;
	fprintf(output, "NAME:\n");
	fprintf(output, "%s %s\n", firstName, lastName);
	fprintf(output, "\nNUMBERS:\n");
	fprintf(output, "%lld, %lld\n", num1, num2);
	fprintf(output, "\nADDITION:\n");
	fprintf(output, "%lld\n", num1 + num2);
	fprintf(output, "\nMULTIPLICATION:\n");
	fprintf(output, "%lld\n", num1 * num2);
	fprintf(output, "\nINPUT FILE:\n");
	fprintf(output, "%s\n", inputFilename);
	fprintf(output, "\nCONTENTS:\n");
	while ((c = getc(input)) != EOF)
		fprintf(output, "%c", c);

	fclose(input);
	fclose(output);

	/*invalid = 1;
	printf("\n");
	char passwordFilename[MAX_LENGTH] = "password.txt";
	char tempPassword[MAX_LENGTH];
	char salt[16];
	char hashed[MAX_LENGTH];
	if (access(passwordFilename, 0) == 0)
		remove(passwordFilename);
	password = fopen(passwordFilename, "rw");
	printf("Note: Password must be at least 10 characters.\n");
	printf("      Password must contain at least one upper case letter, one\n");
	printf("      lower case letter, one number, and one punctuation character.\n");
	printf("-------------------------------------------------------------------\n");
	do
	{
		printf("CREATE PASSWORD:\n");
		fgets(tempPassword, MAX_LENGTH, stdin);
		strip(tempPassword);
		if (validate(tempPassword, "password"))
		{
			for (int x = 0; x < 16; x++)
				salt[x] = rand();
			strncpy(hashed, crypt(tempPassword, salt), MAX_LENGTH);
			fprintf(password, "%s\n", hashed);
			strncpy(tempPassword, "", MAX_LENGTH);
			strncpy(salt, "", MAX_LENGTH);
			invalid = 0;
		}
		else
		{
			printf("Password does not adhere to requirements.\n\n");
			fprintf(error, "Password does not adhere to requirements: %s\n", tempPassword);
			strncpy(tempPassword, "", MAX_LENGTH);
		}
	} while (invalid);

	

	fclose(password);*/
	fclose(error);
}

int validate(const char* input, const char* type)
{
	regex_t regex;
	int status;

	if (strcmp(type, "name") == 0)
	{
		if (regcomp(&regex, "^[A-Z]{1}[A-Za-z '-]{1,49}$", REG_EXTENDED|REG_NOSUB))
			return 0;
	}
	else if (strcmp(type, "number") == 0)
	{
		if (regcomp(&regex, "^-?[0-9]{1,10}|0", REG_EXTENDED|REG_NOSUB))
			return 0;
	}
	else if (strcmp(type, "file") == 0)
	{
		if (regcomp(&regex, "^(\\./)?[A-Za-z -_]+.txt$", REG_EXTENDED|REG_ICASE|REG_NOSUB))
			return 0;
	}
	else if (strcmp(type, "password") == 0)
	{
		if (regcomp(&regex, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\.\\?]).{10,}$", REG_EXTENDED|REG_NOSUB))
			return 0;
	}

	status = regexec(&regex, input, 0, NULL, 0);
	regfree(&regex);
	if (status != 0)
		return 0;
	return 1;
}

void strip(char* string)
{
	if (string == NULL)
	{
		perror("Invalid string.");
		exit(-1);
	}

	size_t len = strlen(string);
	unsigned int x = 0;

	while (string[x] != '\0' && x < len)
	{
		if (string[x] == '\r')
			string[x] = '\0';
		else if (string[x] == '\n')
			string[x] = '\0';

		x++;
	}
}
