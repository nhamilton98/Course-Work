lab3:	cscd340Lab3.c makeArgs.o ./utils/myUtils.o
	gcc -g cscd340Lab3.c makeArgs.o ./utils/myUtils.o -o lab3

makeArgs.o:	makeArgs.c makeArgs.h
	gcc -g -c makeArgs.c

myUtils.o:	./utils/myUtils.o ./utils/myUtils.h
		gcc -g -c ./utils/myUtils.c

clean:
	rm ./utils/myUtils.o
	rm lab3.o
	rm lab3

