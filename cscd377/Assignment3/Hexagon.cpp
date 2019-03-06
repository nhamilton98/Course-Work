#include <GL/glew.h>
#include <GL/freeglut.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define GLM_FORCE_RADIANS 

#include <glm/mat4x4.hpp>
#include <glm/gtc/matrix_transform.hpp>

using namespace glm;

mat4 model_matrix;

GLuint hex_vao;
GLuint hex_vbo;

GLuint v, f;

GLfloat angle = 0.0;
GLfloat axisAngle = 0.0;

GLfloat hex_vertices[] = {
	0.0, 0.0, 0.0, 1.0,         // index '0'
	-0.1125, -0.1875, 0.0, 1.0, // index '1'
	0.1125, -0.1875, 0.0, 1.0,  // index '2'

	0.0, 0.0, 0.0, 1.0,         // index '0'
	0.1125, -0.1875, 0.0, 1.0,  // index '2'
	0.225, 0.0, 0.0, 1.0,       // index '3'

	0.0, 0.0, 0.0, 1.0,         // index '0'
	0.225, 0.0, 0.0, 1.0,       // index '3'
	0.1125, 0.1875, 0.0, 1.0,   // index '4'

	0.0, 0.0, 0.0, 1.0,         // index '0'
	0.1125, 0.1875, 0.0, 1.0,   // index '4'
	-0.1125, 0.1875, 0.0, 1.0,  // index '5'

	0.0, 0.0, 0.0, 1.0,         // index '0'
	-0.1125, 0.1875, 0.0, 1.0,  // index '5'
	-0.225, 0.0, 0.0, 1.0,      // index '6'

	0.0, 0.0, 0.0, 1.0,         // index '0'
	-0.1125, -0.1875, 0.0, 1.0, // index '6'
	-0.225, 0.0, 0.0, 1.0       // index '1'
};

GLfloat hex_vertices_update[] = {
	0.0, 0.0, 0.0, 1.0,         // index '0'
	-0.1125, -0.1875, 0.0, 1.0, // index '1'
	0.1125, -0.1875, 0.0, 1.0,  // index '2'
	0.1125, 0.0, 0.0, 1.0,      // index '3'
	0.1125, 0.1875, 0.0, 1.0,   // index '4'
	-0.1125, 0.1875, 0.0, 1.0,  // index '5'
	-0.1125, 0.0, 0.0, 1.0      // index '6'
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

GLfloat hex_scale[] = {
	1.0f, 1.0f,  //scale factors for white hexagon
	1.0f, 1.25f, // scale factors for red hexagon
	0.8f, 0.8f,  // scale factors for green hexagon
	1.5f, 1.0f,  // scale factors for blue hexagon
	0.5f, 0.8f,  // scale factors for yellow hexagon
	1.5f, 1.2f,  // scale factors for cyan hexagon
	0.5f, 0.5f   // scale factors for grey hexagon
};

GLuint matrix_loc;
GLuint color_loc;
GLuint scalar_loc;

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
	GLuint program = initShaders("hexagon.vs", "hexagon.fs");
	glGenVertexArrays(1, &hex_vao);
	glBindVertexArray(hex_vao);

	glGenBuffers(1, &hex_vbo);
	glBindBuffer(GL_ARRAY_BUFFER, hex_vbo);
	glBufferData(GL_ARRAY_BUFFER, sizeof(hex_vertices), hex_vertices, GL_STATIC_DRAW);
	
	glVertexAttribPointer(0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);

	glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

	matrix_loc = glGetUniformLocation(program, "model_matrix");
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);

	color_loc = glGetUniformLocation(program, "colors");

	scalar_loc = glGetUniformLocation(program, "scalar");	
}

void keyboard(unsigned char key, int x, int y) {

	switch (key)
	{
	case 'q':case 'Q':
		exit(EXIT_SUCCESS);
		break;
	}
	glutPostRedisplay();
}

void Display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	glBindVertexArray(hex_vao);

	// Center Hexagon
	glUniform4fv(color_loc, 1, (GLfloat*)&hex_colors[0]);
	model_matrix = translate(mat4(1.0), vec3(0.0, 0.0, 0.0))*rotate(mat4(1.0), radians(-angle), vec3(0.0, 0.0, 1.0));
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glUniform2fv(scalar_loc, 1, (GLfloat*)&hex_scale[0]);
	glDrawArrays(GL_TRIANGLES, 0, 18);

	// Right Hexagon
	glUniform4fv(color_loc, 1, (GLfloat*)&hex_colors[4]);
	model_matrix = translate(mat4(1.0), vec3(0.75*cos(radians(angle)), 0.75*sin(radians(angle)), 0.0))*rotate(mat4(1.0), radians(-angle), vec3(0.0, 0.0, 1.0));
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glUniform2fv(scalar_loc, 1, (GLfloat*)&hex_scale[2]);
	glDrawArrays(GL_TRIANGLES, 0, 18);

	// Top Right Hexagon
	glUniform4fv(color_loc, 1, (GLfloat*)&hex_colors[8]);
	model_matrix = translate(mat4(1.0), vec3(0.75*cos(radians(60.0+angle)), 0.75*sin(radians(60.0+angle)), 0.0))*rotate(mat4(1.0), radians(-angle), vec3(0.0, 0.0, 1.0));
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glUniform2fv(scalar_loc, 1, (GLfloat*)&hex_scale[4]);
	glDrawArrays(GL_TRIANGLES, 0, 18);

	// Top Left Hexagon
	glUniform4fv(color_loc, 1, (GLfloat*)&hex_colors[12]);
	model_matrix = translate(mat4(1.0), vec3(-0.75*cos(radians(60.0 - angle)), 0.75*sin(radians(60.0 - angle)), 0.0))*rotate(mat4(1.0), radians(-angle), vec3(0.0, 0.0, 1.0));
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glUniform2fv(scalar_loc, 1, (GLfloat*)&hex_scale[6]);
	glDrawArrays(GL_TRIANGLES, 0, 18);

	// Left Hexagon
	glUniform4fv(color_loc, 1, (GLfloat*)&hex_colors[16]);
	model_matrix = translate(mat4(1.0), vec3(-0.75*cos(radians(-angle)), 0.75*sin(radians(-angle)), 0.0))*rotate(mat4(1.0), radians(-angle), vec3(0.0, 0.0, 1.0));
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glUniform2fv(scalar_loc, 1, (GLfloat*)&hex_scale[8]);
	glDrawArrays(GL_TRIANGLES, 0, 18);

	// Bottom Left Hexagon
	glUniform4fv(color_loc, 1, (GLfloat*)&hex_colors[20]);
	model_matrix = translate(mat4(1.0), vec3(-0.75*cos(radians(60.0 + angle)), -0.75*sin(radians(60.0 + angle)), 0.0))*rotate(mat4(1.0), radians(-angle), vec3(0.0, 0.0, 1.0));
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glUniform2fv(scalar_loc, 1, (GLfloat*)&hex_scale[10]);
	glDrawArrays(GL_TRIANGLES, 0, 18);

	// Bottom Right Hexagon
	glUniform4fv(color_loc, 1, (GLfloat*)&hex_colors[24]);
	model_matrix = translate(mat4(1.0), vec3(0.75*cos(radians(60.0 - angle)), -0.75*sin(radians(60.0 - angle)), 0.0))*rotate(mat4(1.0), radians(-angle), vec3(0.0, 0.0, 1.0));
	glUniformMatrix4fv(matrix_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glUniform2fv(scalar_loc, 1, (GLfloat*)&hex_scale[12]);
	glDrawArrays(GL_TRIANGLES, 0, 18);

	glutSwapBuffers();
}

void Rotate(int n)
{		
	angle += 5;
	glutPostRedisplay();
	glutTimerFunc(100, Rotate, 1);
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGBA);
	glutInitWindowSize(1000, 1000);
	glutInitWindowPosition(0, 0);

	glutCreateWindow("Hexagon");

	if (glewInit()) {
		printf("Unable to initialize GLEW ... exiting\n");
	}

	init();
	printf("%s\n", glGetString(GL_VERSION));
	glutDisplayFunc(Display);
	glutKeyboardFunc(keyboard);
	glutTimerFunc(100, Rotate, 1);

	glutMainLoop();
}