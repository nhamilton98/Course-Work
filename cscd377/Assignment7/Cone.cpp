#include "Cone.h"

unsigned int cone_vao;

void createCone()
{
	float side = 0.5f;

	vec4 cone_vertices[NumVertices];
	vec2 cone_textures[NumVertices];
	vec3 cone_normals[NumVertices];
	
	int index = 0;

	float angleOne;
	float angleTwo;

	vec4 p1, p2, p3;

	for (int i = 0; i < NumTriangles; ++i)
	{
		angleOne = radians(i*7.2f);
		angleTwo = radians((i + 1)*7.2f);

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

		vec2 tex1((float)i / (float)NumTriangles, 0.0);
		vec2 tex2((float)(i + 1) / (float)NumTriangles, 0.0);
		vec2 tex3(0.5, 1.0);

		vec3 normal = normalize(cross(vec3(p2 - p1), vec3(p3 - p1)));
		cone_vertices[index] = p1; cone_textures[index] = tex1; cone_normals[index] = normal; index++;
		cone_vertices[index] = p2; cone_textures[index] = tex2; cone_normals[index] = normal; index++;
		cone_vertices[index] = p3; cone_textures[index] = tex3; cone_normals[index] = normal; index++;
	}

	printf("Index is: %d\n", index);

	glGenVertexArrays(1, &cone_vao);
	glBindVertexArray(cone_vao);

	unsigned int handle[3];
	glGenBuffers(3, handle);

	glBindBuffer(GL_ARRAY_BUFFER, handle[0]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(cone_vertices), cone_vertices, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);

	glBindBuffer(GL_ARRAY_BUFFER, handle[1]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(cone_normals), cone_normals, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)1, 3, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(1);

	glBindBuffer(GL_ARRAY_BUFFER, handle[2]);
	glBufferData(GL_ARRAY_BUFFER, sizeof(cone_textures), cone_textures, GL_STATIC_DRAW);
	glVertexAttribPointer((GLuint)2, 2, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(2);

	glBindVertexArray(0);
}

void drawCone()
{
	glBindVertexArray(cone_vao);
	glDrawArrays(GL_TRIANGLES, 0, NumVertices);
}


