CC=gcc

lab7: cscd340Lab7.c ./utils/myUtils.o ./utils/fileUtils.o
	gcc -g ./utils/myUtils.o ./utils/fileUtils.o cscd340Lab7.c -o lab7 -lm

myUtils.o:	./utils/myUtils.o ./utils/myUtils.h
		gcc -g -c ./utils/myUtils.o

fileUtils.o:	./utils/fileUtils.o ./utils/fileUtils.h
		gcc -g -c ./utils/fileUtils.c

clean:
	rm lab7
	rm lab7.o
	rm ./utils/myUtils.o
	rm ./utils/fileUtils.o
