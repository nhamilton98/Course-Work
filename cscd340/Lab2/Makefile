FLAGS= -g -c -m32

EXE = lab2
MAIN = ./cscd340Lab2.c

WORDS = ./words/word.o
MOVIES = ./movies/movie.o

UTILS= ./utils/myUtils.o ./utils/fileUtil.o
LINKEDLIST = ./linkedlist/linkedList.o ./linkedlist/listUtils.o 

${EXE}:	${UTILS} ${WORDS} ${MOVIES} ${LINKEDLIST}  ${MAIN}
	gcc -o ${EXE} ${MAIN} ${WORDS} ${MOVIES} ${LINKEDLIST} ${UTILS}

#==============================================================================================================================

listUtils.o:	./linkedlist/listUtils.c listUtils.h
	gcc  ${FLAGS} ./linkedlist/listUtils.c

linkedList.o:	./linkedlist/linkedList.h ./linkedlist/linkedList.c ./linkedlist/requiredIncludes.h
	gcc ${FLAGS} ./linkedlist/linkedList.c

#==============================================================================================================================

myUtils.o:	./utils/myUtils.h ./utils/myUtils.c
	gcc ${FLAGS} ./utils/myUtils.c

fileUtil.o:	./utils/fileUtil.h ./utils/fileUtil.c
	gcc ${FLAGS} ./utils/fileUtil.c

#==============================================================================================================================

word.o:	./words/word.h ./words/word.c
	gcc ${FLAGS} ./words/word.c

movie.o:	./movies/actor.h ./movies/movie.h ./movies/movie.c
	gcc ${FLAGS} ./movies/movie.c

#==============================================================================================================================

clean:	
	rm words/*.o
	rm movies/*.o
	rm linkedlist/*.o
	rm utils/*.o
	rm ${EXE}


