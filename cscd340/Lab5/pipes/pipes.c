#include "pipes.h"
#include "../tokenize/makeArgs.h"

int containsPipe(char *s)
{
	int x = 0, count = 0;

	for(x; x < strlen(s); x++)
	{
		if(s[x] == '|')
			count++;
	}

	//printf("Total of %d pipe(s).\n", count);

	return count;
}

char ** parsePrePipe(char *s, int * preCount)
{
	char ** pre = NULL;

	char * copy = (char *) calloc(strlen(s) + 1, sizeof(char));
	strcpy(copy, s);

	char * ptr;
	char * token = strtok_r(copy, "|", &ptr);
	strip(copy);

	*preCount = makeargs(token, &pre);

	//printf("Pre-pipe Command: %s\n", token);

	free(copy);
	copy = NULL;

	return pre;
}

char ** parsePostPipe(char *s, int * postCount)
{
	char ** post = NULL;

	char * copy = (char *) calloc(strlen(s) + 1, sizeof(char));
	strcpy(copy, s);

	char * ptr;
	char * token = strtok_r(copy, "|", &ptr);
	token = strtok_r(NULL, "|", &ptr);
	strip(token);

	*postCount = makeargs(token, &post);

	//printf("Post-pipe Command: %s\n", token + 1);

	free(copy);
	copy = NULL;

	return post;
}

void pipeIt(char ** prePipe, char ** postPipe)
{
	/*int fd[2];
	pipe(fd);

	if (fork() == 0)
	{
		close(fd[0]);
		close(1);
		dup(fd[1]);
		close(fd[1]);
		execvp(prePipe[1], prePipe);
	}
	else
	{
		close(fd[1]);
		close(0);
		dup(fd[0]);
		close(fd[0]);
		execvp(postPipe[0], postPipe);
	}*/

	int fd[2];
	int temp, num;

	pid_t pid1 = fork();

	if (pid1 == 0)
	{
		temp = pipe(fd);

		if (temp < 0)
		{
			printf("Invalid pipe.");
			exit(-99);
		}

		pid_t pid2 = fork();

		if (pid2 == 0)
		{
			close(fd[0]);
			close(1);
			dup(fd[1]);
			close(fd[1]);

			temp = execvp(prePipe[0], prePipe);

			if (temp == -1)
			{
				printf("Command not found: %s\n", prePipe[0]);
				exit(-99);
			}
		}
		else
		{
			waitpid(pid2, &num, 0);

			if (num > 0)
				printf("Command not found: %s\n", prePipe[0]);

			close(fd[1]);
			close(0);
			dup(fd[0]);
			close(fd[0]);

			temp = execvp(postPipe[0], postPipe);

			if (temp == -1)
			{
				printf("Command not found: %s\n", postPipe[0]);
				exit(-99);
			}
		}
	}
	else
		waitpid(pid1, &num, 0);
}

