#include "Parasol.h"

unsigned int parasol_vao;
unsigned int parasol_vbo;

void createParasolOpen()
{
	GLfloat parasol_vertices[] = {
		0.0, 0.5, 0.0, 1.0, //0
		2.0*cos(radians(0.0f)), 0.0,  2.0*sin(radians(0.0f)), 1.0, //1
		2.0*cos(radians(60.0f)), 0.0,  2.0*sin(radians(60.0f)), 1.0, //2

		0.0, 0.5, 0.0, 1.0, //0
		2.0*cos(radians(60.0f)), 0.0,  2.0*sin(radians(60.0f)), 1.0, //2
		2.0*cos(radians(120.0f)), 0.0,  2.0*sin(radians(120.0f)), 1.0, //3

		0.0, 0.5, 0.0, 1.0, //0
		2.0*cos(radians(120.0f)), 0.0,  2.0*sin(radians(120.0f)), 1.0, //3
		2.0*cos(radians(180.0f)), 0.0,  2.0*sin(radians(180.0f)), 1.0, //4

		0.0, 0.5, 0.0, 1.0, //0
		2.0*cos(radians(180.0f)), 0.0,  2.0*sin(radians(180.0f)), 1.0, //4
		2.0*cos(radians(240.0f)), 0.0,  2.0*sin(radians(240.0f)), 1.0, //5

		0.0, 0.5, 0.0, 1.0, //0
		2.0*cos(radians(240.0f)), 0.0,  2.0*sin(radians(240.0f)), 1.0, //5
		2.0*cos(radians(300.0f)), 0.0,  2.0*sin(radians(300.0f)), 1.0, //6

		0.0, 0.5, 0.0, 1.0, //0
		2.0*cos(radians(300.0f)), 0.0,  2.0*sin(radians(300.0f)), 1.0, //6
		2.0*cos(radians(0.0f)), 0.0,  2.0*sin(radians(0.0f)), 1.0 //1
	};


	const GLfloat parasol_colors[] = { 1.0, 0.0, 0.0, 1.0f,
									   1.0, 0.0, 0.0, 1.0f,
									   1.0, 0.0, 0.0, 1.0f,

									   1.0, 1.0, 0.0, 1.0f,
									   1.0, 1.0, 0.0, 1.0f,
									   1.0, 1.0, 0.0, 1.0f,

									   0.0, 1.0, 0.0, 1.0f,
									   0.0, 1.0, 0.0, 1.0f,
									   0.0, 1.0, 0.0, 1.0f,

									   0.0, 0.0, 1.0, 1.0f,
									   0.0, 0.0, 1.0, 1.0f,
									   0.0, 0.0, 1.0, 1.0f,

									   0.5, 1.0, 1.0, 1.0f,
									   0.5, 1.0, 1.0, 1.0f,
									   0.5, 1.0, 1.0, 1.0f,

									   0.5, 0.0, 0.5, 1.0f,
									   0.5, 0.0, 0.5, 1.0f,
									   0.5, 0.0, 0.5, 1.0f, };

	glGenVertexArrays(1, &parasol_vao);
	glBindVertexArray(parasol_vao);

	glGenBuffers(1, &parasol_vbo);
	glBindBuffer(GL_ARRAY_BUFFER, parasol_vbo);

	GLuint offset = 0;

	glBufferData(GL_ARRAY_BUFFER, sizeof(parasol_vertices) + sizeof(parasol_colors), NULL, GL_STATIC_DRAW);
	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(parasol_vertices), parasol_vertices);
	offset += sizeof(parasol_vertices);
	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(parasol_colors), parasol_colors);

	glVertexAttribPointer((GLuint)0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(1, 4, GL_FLOAT, GL_FALSE, 0, (GLvoid *)sizeof(parasol_vertices));
	glEnableVertexAttribArray(1);

	glBindVertexArray(0);
}

void createParasolClosed()
{
	GLfloat parasol_vertices[] = {
		0.0, 0.5, 0.0, 1.0, //0
		1.25*cos(radians(0.0f)), -2.0,  1.25*sin(radians(0.0f)), 1.0, //1
		1.25*cos(radians(60.0f)), -2.0,  1.25*sin(radians(60.0f)), 1.0, //2

		0.0, 0.5, 0.0, 1.0, //0
		1.25*cos(radians(60.0f)), -2.0,  1.25*sin(radians(60.0f)), 1.0, //2
		1.25*cos(radians(120.0f)), -2.0,  1.25*sin(radians(120.0f)), 1.0, //3

		0.0, 0.5, 0.0, 1.0, //0
		1.25*cos(radians(120.0f)), -2.0,  1.25*sin(radians(120.0f)), 1.0, //3
		1.25*cos(radians(180.0f)), -2.0,  1.25*sin(radians(180.0f)), 1.0, //4

		0.0, 0.5, 0.0, 1.0, //0
		1.25*cos(radians(180.0f)), -2.0,  1.25*sin(radians(180.0f)), 1.0, //4
		1.25*cos(radians(240.0f)), -2.0,  1.25*sin(radians(240.0f)), 1.0, //5

		0.0, 0.5, 0.0, 1.0, //0
		1.25*cos(radians(240.0f)), -2.0,  1.25*sin(radians(240.0f)), 1.0, //5
		1.25*cos(radians(300.0f)), -2.0,  1.25*sin(radians(300.0f)), 1.0, //6

		0.0, 0.5, 0.0, 1.0, //0
		1.25*cos(radians(300.0f)), -2.0,  1.25*sin(radians(300.0f)), 1.0, //6
		1.25*cos(radians(0.0f)), -2.0,  1.25*sin(radians(0.0f)), 1.0 //1
	};

	const GLfloat parasol_colors[] = { 1.0, 0.0, 0.0, 1.0f,
									   1.0, 0.0, 0.0, 1.0f,
									   1.0, 0.0, 0.0, 1.0f,

									   1.0, 1.0, 0.0, 1.0f,
									   1.0, 1.0, 0.0, 1.0f,
									   1.0, 1.0, 0.0, 1.0f,

									   0.0, 1.0, 0.0, 1.0f,
									   0.0, 1.0, 0.0, 1.0f,
									   0.0, 1.0, 0.0, 1.0f,

									   0.0, 0.0, 1.0, 1.0f,
									   0.0, 0.0, 1.0, 1.0f,
									   0.0, 0.0, 1.0, 1.0f,

									   0.5, 1.0, 1.0, 1.0f,
									   0.5, 1.0, 1.0, 1.0f,
									   0.5, 1.0, 1.0, 1.0f,

									   0.5, 0.0, 0.5, 1.0f,
									   0.5, 0.0, 0.5, 1.0f,
									   0.5, 0.0, 0.5, 1.0f, };

	glGenVertexArrays(1, &parasol_vao);
	glBindVertexArray(parasol_vao);

	glGenBuffers(1, &parasol_vbo);
	glBindBuffer(GL_ARRAY_BUFFER, parasol_vbo);

	GLuint offset = 0;

	glBufferData(GL_ARRAY_BUFFER, sizeof(parasol_vertices) + sizeof(parasol_colors), NULL, GL_STATIC_DRAW);
	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(parasol_vertices), parasol_vertices);
	offset += sizeof(parasol_vertices);
	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(parasol_colors), parasol_colors);

	glVertexAttribPointer((GLuint)0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(1, 4, GL_FLOAT, GL_FALSE, 0, (GLvoid *)sizeof(parasol_vertices));
	glEnableVertexAttribArray(1);

	glBindVertexArray(0);
}

void drawParasol()
{
	glBindVertexArray(parasol_vao);
	glDrawArrays(GL_TRIANGLES, 0, 18);
}
