#include "Plane.h"

unsigned int plane_vao;

void createPlane()
{
	float side = 40.0f;
	GLfloat plane_vertices[] = { -side, 0.0, side, 1.0, //0
								 side, 0.0, side, 1.0, //1
								 -side, 0.0, -side, 1.0, //2
								 side, 0.0, -side, 1.0, //3
								 -side, 0.0, -side, 1.0, //2
								 side, 0.0, side, 1.0 }; //1

	GLfloat plane_normals[] = { 0.0, 1.0, 0.0, //0
								0.0, 1.0, 0.0, //1
								0.0, 1.0, 0.0, //2
								0.0, 1.0, 0.0, //3
								0.0, 1.0, 0.0, //2
								0.0, 1.0, 0.0, }; //1

	GLushort plane_indices[] = { 
		0, 1, 2,
		3, 2, 1
	};

	glGenVertexArrays(1, &plane_vao);
	glBindVertexArray(plane_vao);

	unsigned int handle[3];
	glGenBuffers(3, handle);

	glBindBuffer(GL_ARRAY_BUFFER, handle[0]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(plane_vertices), plane_vertices, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);

	glBindBuffer(GL_ARRAY_BUFFER, handle[1]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(plane_normals), plane_normals, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)1, 3, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(1);

	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handle[2]);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(plane_indices), plane_indices, GL_STATIC_DRAW);

	glBindVertexArray(0);
}

void drawPlane()
{
	glBindVertexArray(plane_vao);
	glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_SHORT, 0);
}
