#include <GL/glew.h>
#include <GL/freeglut.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define GLM_FORCE_RADIANS 

#include <glm/mat4x4.hpp>
#include <glm/gtc/matrix_transform.hpp> 

char* ReadFile(const char*);
GLuint initShaders(const char*, const char*);
void init();
void Display(void);
void keyboard(unsigned char, int, int);

glm::vec3 scale(1.0, 1.0, 1.0);
GLuint scale_loc;

GLuint hex_vao;
GLuint hex_vbo;
GLuint hex_ebo;

GLuint v, f;

GLboolean lines = false;
GLboolean points = false;
GLboolean rectangles = false;

GLfloat lineWidth = 0.0;
GLfloat pointSize = 0.0;

GLfloat hex_vertices[] = {
	0.0, 0.0, 0.0, 1.0,         // index '0'
	-0.1125, -0.1875, 0.0, 1.0, // index '1'
	0.1125, -0.1875, 0.0, 1.0,  // index '2'
	0.225, 0.0, 0.0, 1.0,       // index '3'
	0.1125, 0.1875, 0.0, 1.0,   // index '4'
	-0.1125, 0.1875, 0.0, 1.0,  // index '5'
	-0.225, 0.0, 0.0, 1.0,      // index '6'
};

GLfloat hex_vertices_update[] = {
	0.0, 0.0, 0.0, 1.0,              // index '0'
	-0.1125, -0.1875, 0.0, 1.0,      // index '1'
	0.1125, -0.1875, 0.0, 1.0,       // index '2'
	0.1125, 0.0, 0.0, 1.0,           // index '3'
	0.1125, 0.1875, 0.0, 1.0,        // index '4'
	-0.1125, 0.1875, 0.0, 1.0,       // index '5'
	-0.1125, 0.0, 0.0, 1.0           // index '6'
};

GLshort hex_indeces[] = {
	0, 1, 2,
	0, 2, 3,
	0, 3, 4,
	0, 4, 5,
	0, 5, 6,
	0, 6, 1
};

GLfloat hex_colors[] = {
	1.0f, 1.0f, 1.0f, 1.0f,
	1.0f, 0.0f, 0.0f, 1.0f,
	0.0f, 1.0f, 0.0f, 1.0f,
	0.0f, 0.0f, 1.0f, 1.0f,
	1.0f, 1.0f, 0.0f, 1.0f,
	0.0f, 1.0f, 1.0f, 1.0f,
	0.5f, 0.5f, 0.5f, 1.0f
};

GLfloat hex_positions[] =
{
	0.0f, 0.0f, 0.0f, 1.0f,
	-0.40f, -0.60f, 0.0f, 1.0f,
	0.40f, -0.60f, 0.0f, 1.0f,
	0.40f, 0.60f, 0.0f, 1.0f,
	-0.40f, 0.60f, 0.0f, 1.0f,
	-0.90f, 0.0f, 0.0f, 1.0f,
	0.90f, 0.0f, 0.0f, 1.0f
};

char* ReadFile(const char* filename)
{
	FILE* infile;

	#ifdef WIN32
		fopen_s(&infile, filename, "rb");
	#else
		infile = fopen(filename, "rb");
	#endif

	if (!infile)
	{
		printf("Unable to open file %s\n", filename);
		return NULL;
	}

	fseek(infile, 0, SEEK_END);
	int len = ftell(infile);
	fseek(infile, 0, SEEK_SET);
	char* source = (char*)malloc(len + 1);
	fread(source, 1, len, infile);
	fclose(infile);
	source[len] = 0;

	return (source);
}

GLuint initShaders(const char* v_shader, const char* f_shader)
{
	GLuint p = glCreateProgram();

	v = glCreateShader(GL_VERTEX_SHADER);
	f = glCreateShader(GL_FRAGMENT_SHADER);

	const char * vs = ReadFile(v_shader);
	const char * fs = ReadFile(f_shader);

	glShaderSource(v, 1, &vs, NULL);
	glShaderSource(f, 1, &fs, NULL);

	free((char*)vs);
	free((char*)fs);

	glCompileShader(v);

	GLint compiled;

	glGetShaderiv(v, GL_COMPILE_STATUS, &compiled);
	if (!compiled)
	{
		GLsizei len;
		glGetShaderiv(v, GL_INFO_LOG_LENGTH, &len);

		char* log = (char*)malloc(len + 1);

		glGetShaderInfoLog(v, len, &len, log);

		printf("Vertex Shader compilation failed: %s\n", log);

		free(log);
	}

	glCompileShader(f);
	glGetShaderiv(f, GL_COMPILE_STATUS, &compiled);

	if (!compiled)
	{
		GLsizei len;
		glGetShaderiv(f, GL_INFO_LOG_LENGTH, &len);
		char* log = (char*)malloc(len + 1);
		glGetShaderInfoLog(f, len, &len, log);
		printf("Vertex Shader compilation failed: %s\n", log);
		free(log);
	}

	glAttachShader(p, v);
	glAttachShader(p, f);

	glLinkProgram(p);
	GLint linked;

	glGetProgramiv(p, GL_LINK_STATUS, &linked);
	if (!linked)
	{
		GLsizei len;
		glGetProgramiv(p, GL_INFO_LOG_LENGTH, &len);
		char* log = (char*)malloc(len + 1);
		glGetProgramInfoLog(p, len, &len, log);
		printf("Shader linking failed: %s\n", log);
		free(log);
	}

	glUseProgram(p);

	return p;
}

void init()
{
	GLuint program = initShaders("instancing.vs", "instancing.fs");
	
	scale_loc = glGetUniformLocation(program, "scalar");
	glUniform3fv(scale_loc, 1, (GLfloat*)&scale[0]);

	GLuint offset = 0;

	glGenVertexArrays(1, &hex_vao);
	glBindVertexArray(hex_vao);

	glGenBuffers(1, &hex_vbo);
	glBindBuffer(GL_ARRAY_BUFFER, hex_vbo);

	if (rectangles)
	{
		glBufferData(GL_ARRAY_BUFFER, sizeof(hex_vertices_update) + sizeof(hex_colors) + sizeof(hex_positions) + sizeof(scale), NULL, GL_STATIC_DRAW);
		glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(hex_vertices_update), hex_vertices_update);
		offset += sizeof(hex_vertices_update);
	}
	else
	{
		glBufferData(GL_ARRAY_BUFFER, sizeof(hex_vertices) + sizeof(hex_colors) + sizeof(hex_positions) + sizeof(scale), NULL, GL_STATIC_DRAW);
		glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(hex_vertices), hex_vertices);
		offset += sizeof(hex_vertices);
	}

	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(hex_colors), hex_colors);
	offset += sizeof(hex_colors);
	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(hex_positions), hex_positions);

	glGenBuffers(1, &hex_ebo);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, hex_ebo);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(hex_indeces), hex_indeces, GL_STATIC_DRAW);
	
	glVertexAttribPointer(0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);
	
	if (rectangles)
	{
		glVertexAttribPointer(1, 4, GL_FLOAT, GL_FALSE, 0, (GLvoid *)sizeof(hex_vertices_update));
		glEnableVertexAttribArray(1);
		glVertexAttribDivisor(1, 1);

		glVertexAttribPointer(2, 4, GL_FLOAT, GL_FALSE, 0, (GLvoid *)(sizeof(hex_vertices_update) + sizeof(hex_colors)));
		glEnableVertexAttribArray(2);
		glVertexAttribDivisor(2, 1);
	}
	else
	{
		glVertexAttribPointer(1, 4, GL_FLOAT, GL_FALSE, 0, (GLvoid *)sizeof(hex_vertices));
		glEnableVertexAttribArray(1);
		glVertexAttribDivisor(1, 1);

		glVertexAttribPointer(2, 4, GL_FLOAT, GL_FALSE, 0, (GLvoid *)(sizeof(hex_vertices) + sizeof(hex_colors)));
		glEnableVertexAttribArray(2);
		glVertexAttribDivisor(2, 1);
	}
}

void keyboard(unsigned char key, int x, int y)
{
	GLfloat range[2];

	switch (key) {
	case 'q':case 'Q':
		exit(EXIT_SUCCESS);
		break;
	case 'l':case 'L':
		points = false;
		lines = !lines;
		glGetFloatv(GL_ALIASED_LINE_WIDTH_RANGE, range);
		lineWidth = range[1] * (float)((rand() % 20) + 1) / 10;
		break;
	case 'p':case 'P':
		lines = false;
		points = !points;
		glGetFloatv(GL_POINT_SIZE_RANGE, range);
		pointSize = range[1] * (float)((rand() % 20) + 1) / 10;
		break;
	case 'u':case 'U':
		rectangles = !rectangles;
		init();
		break;

	case 's':case 'S':
		scale[0] = ((float)((rand() % 20) + 1) / 10);
		scale[1] = ((float)((rand() % 20) + 1) / 10);
		scale[2] = 1;
		glUniform3fv(scale_loc, 1, (GLfloat*)&scale[0]);
		init();
		break;
	}
	glutPostRedisplay();
}
/**************************************************************************************/

void Display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	glBindVertexArray(hex_vao);

	if (lines)
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glLineWidth(lineWidth);
	}
	else if (points)
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_POINT);
		glPointSize(pointSize);
	}
	else
		glPolygonMode(GL_FRONT, GL_FILL);

	glBindVertexArray(hex_vao);
	glDrawElementsInstanced(GL_TRIANGLES, 18, GL_UNSIGNED_SHORT, 0, 7);

	glFlush();
	glutSwapBuffers();
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGBA);
	glutInitWindowSize(1000, 1000);
	glutInitWindowPosition(0, 0);

	glutCreateWindow("Instanced Hexagon");

	if (glewInit()) {
		printf("Unable to initialize GLEW ... exiting\n");
	}

	init();
	printf("%s\n", glGetString(GL_VERSION));
	glutDisplayFunc(Display);
	glutKeyboardFunc(keyboard);
	glutMainLoop();
}