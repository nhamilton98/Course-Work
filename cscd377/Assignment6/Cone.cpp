#include "Cone.h"

unsigned int cone_vao;

void createCone()
{
	float side = 0.5f;

	vec4 points[NumVertices];
	vec4 colors[NumVertices];
	vec3 normals[NumVertices];
	
	int index = 0;

	float angleOne;
	float angleTwo;

	vec4 p1, p2, p3;

	vec4 col(1.0f, 0.0f, 1.0f, 1.0f);

	for (int i = 0; i < NumTriangles; ++i)
	{
		angleOne = radians(i*20.0f);
		angleTwo = radians((i + 1)*20.0f);

		p1.x = cos(angleOne);
		p1.y = -1.0;
		p1.z = -sin(angleOne);
		p1.w = 1.0;

		p2.x = cos(angleTwo);
		p2.y = -1.0;
		p2.z = -sin(angleTwo);
		p2.w = 1.0;

		p3.x = 0.0;
		p3.y = 1.0;
		p3.z = 0.0;
		p3.w = 1.0;

		vec3 normal = normalize(cross(vec3(p2 - p1), vec3(p3 - p1)));
		points[index] = p1; normals[index] = normal; colors[index] = col; index++;
		points[index] = p2; normals[index] = normal;  colors[index] = col; index++;
		points[index] = p3; normals[index] = normal;  colors[index] = col; index++;
	}

	printf("Index is: %d\n", index);

	glGenVertexArrays(1, &cone_vao);
	glBindVertexArray(cone_vao);

	unsigned int handle[3];
	glGenBuffers(3, handle);

	glBindBuffer(GL_ARRAY_BUFFER, handle[0]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vec4)*NumVertices, points, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);

	glBindBuffer(GL_ARRAY_BUFFER, handle[1]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vec4)*NumVertices, colors, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)1, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(1);

	glBindBuffer(GL_ARRAY_BUFFER, handle[2]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vec3)*NumVertices, normals, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)2, 3, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(2);

	glBindVertexArray(0);
}

void drawCone()
{
	glBindVertexArray(cone_vao);
	glDrawArrays(GL_TRIANGLES, 0, NumVertices);
}


