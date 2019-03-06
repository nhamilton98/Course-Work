#include <GL/glew.h>
#include <GL/freeglut.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define GLM_FORCE_RADIANS 

#include <glm/mat4x4.hpp>
#include <glm/gtc/matrix_transform.hpp>

using namespace glm;

GLuint  cube_vao;
GLuint  cube_vbo;
GLuint  cube_ebo;

GLuint program;

GLboolean wire_frame = false;
GLboolean change_projection = false;

mat4 view_matrix;
mat4 projection_matrix;
mat4 model_matrix;

GLuint view_loc;
GLuint projection_loc;
GLuint model_loc;
GLuint shear_loc;

GLint timer = 0.0;
GLfloat radius = 3.0;
GLfloat camX = 0.0;
GLfloat camZ = 0.0;

using namespace glm;

const GLfloat cube_vertices[] = { -0.15, -0.15, 0.15, 1.0f, //Front
                                   0.15, -0.15, 0.15, 1.0f,
                                   0.15, 0.15, 0.15, 1.0f,
                                  -0.15, 0.15, 0.15, 1.0f,

                                   0.15, -0.15, 0.15, 1.0f, //Right
                                   0.15, -0.15, -0.15, 1.0f,
                                   0.15, 0.15, -0.15, 1.0f,
                                   0.15, 0.15, 0.15, 1.0f,

                                  -0.15, -0.15, -0.15, 1.0f, //Back
                                  -0.15, 0.15, -0.15, 1.0f,
                                   0.15, 0.15, -0.15, 1.0f,
                                   0.15, -0.15, -0.15, 1.0f,
                                                            
                                  -0.15, -0.15, 0.15, 1.0f, //Left
                                  -0.15, 0.15, 0.15, 1.0f,
                                  -0.15, 0.15, -0.15, 1.0f,
                                  -0.15, -0.15, -0.15, 1.0f,
                                                           
                                  -0.15, -0.15, 0.15, 1.0f, //Bottom
                                  -0.15, -0.15, -0.15, 1.0f,
                                   0.15, -0.15, -0.15, 1.0f,
                                   0.15, -0.15,  0.15, 1.0f,
                                                          
                                  -0.15, 0.15, 0.15, 1.0f, //Top
                                   0.15, 0.15, 0.15, 1.0f,
                                   0.15, 0.15, -0.15, 1.0f,
                                  -0.15, 0.15, -0.15, 1.0f
                               };

const GLfloat cube_colors[] = { 0.0, 0.0, 1.0, 1.0f, //Front
								0.0, 0.0, 1.0, 1.0f,
								0.0, 0.0, 1.0, 1.0f,
								0.0, 0.0, 1.0, 1.0f,

								1.0, 0.0, 0.0, 1.0f, //Right
								1.0, 0.0, 0.0, 1.0f,
								1.0, 0.0, 0.0, 1.0f,
								1.0, 0.0, 0.0, 1.0f,

								0.0, 0.0, 1.0, 1.0f, //Back
								0.0, 0.0, 1.0, 1.0f,
								0.0, 0.0, 1.0, 1.0f,
								0.0, 0.0, 1.0, 1.0f,

								1.0, 0.0, 0.0, 1.0f, //Left
								1.0, 0.0, 0.0, 1.0f,
								1.0, 0.0, 0.0, 1.0f,
								1.0, 0.0, 0.0, 1.0f,

								0.0, 1.0, 0.0, 1.0f, //Bottom
								0.0, 1.0, 0.0, 1.0f,
								0.0, 1.0, 0.0, 1.0f,
								0.0, 1.0, 0.0, 1.0f,

								0.0, 1.0, 0.0, 1.0f, //Top
								0.0, 1.0, 0.0, 1.0f,
								0.0, 1.0, 0.0, 1.0f,
								0.0, 1.0, 0.0, 1.0f,
							  };


GLushort cube_indices[] = {
	0, 1, 2, 0, 2, 3,
	4, 5, 6, 4, 6, 7,
	8, 9, 10, 8, 10, 11,
	12, 13, 14, 12, 14, 15,
	16, 17, 18, 16, 18, 19,
	20, 21, 22, 20, 22, 23
};


char* ReadFile(const char* filename);
GLuint initShaders(const char* v_shader, const char* f_shader);
void Initialize();
void Display(void);

char* ReadFile(const char* filename)
{
	FILE* infile;

	#ifdef WIN32
		fopen_s(&infile, filename, "rb");
	#else
		infile = fopen(filename, "r");
	#endif

	if (!infile)
	{
		printf("Unable to open file %s\n", filename);
		return NULL;
	}

	fseek(infile, 0, SEEK_END);
	int len = ftell(infile);
	fseek(infile, 0, SEEK_SET);
	GLchar* source = (GLchar*)malloc(len + 1);
	fread(source, 1, len, infile);
	fclose(infile);
	source[len] = 0;

	return (GLchar*) (source);
}

GLuint initShaders(const char *v_shader, const char *f_shader)
{
	GLuint p = glCreateProgram();
	GLuint v = glCreateShader(GL_VERTEX_SHADER);
	GLuint f = glCreateShader(GL_FRAGMENT_SHADER);

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

		GLchar* log = (GLchar*)malloc(len + 1);
		glGetShaderInfoLog(v, len, &len, log);
		printf("Vertex Shader compilation failed: %s\n",log);
		free(log);
	}

	glCompileShader(f);

	glGetShaderiv(f, GL_COMPILE_STATUS, &compiled);
	if (!compiled)
	{
		GLsizei len;
		glGetShaderiv(f, GL_INFO_LOG_LENGTH, &len);

		GLchar* log = (GLchar*)malloc(len + 1);
		glGetShaderInfoLog(f, len, &len, log);
		printf("Fragment Shader compilation failed: %s\n", log);
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

		GLchar* log = (GLchar*)malloc(len + 1);
		glGetProgramInfoLog(p, len, &len, log);
		printf("Shader linking failed: %s\n", log);
		free(log);
	}

	glUseProgram(p);

	return p;
}

void Initialize(void)
{
	program = initShaders("cube_shader.vs", "cube_shader.fs");

	GLuint offset = 0;

	glGenVertexArrays(1, &cube_vao);
	glBindVertexArray(cube_vao);

	glGenBuffers(1, &cube_vbo);
	glBindBuffer(GL_ARRAY_BUFFER, cube_vbo);
	glBufferData(GL_ARRAY_BUFFER, sizeof(cube_vertices) + sizeof(cube_colors), NULL, GL_STATIC_DRAW);
	
	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(cube_vertices), cube_vertices);
	offset += sizeof(cube_vertices);

	glBufferSubData(GL_ARRAY_BUFFER, offset, sizeof(cube_colors), cube_colors);
	offset += sizeof(cube_colors);
	
	glVertexAttribPointer(0, 4, GL_FLOAT, GL_FALSE, 0, 0);
	glEnableVertexAttribArray(0);

	glVertexAttribPointer(1, 4, GL_FLOAT, GL_FALSE, 0, (GLvoid*)sizeof(cube_vertices));
	glEnableVertexAttribArray(1);

	glGenBuffers(1, &cube_ebo);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cube_ebo);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(cube_indices), cube_indices, GL_STATIC_DRAW);

	view_loc = glGetUniformLocation(program, "view_matrix");
	view_matrix = lookAt(vec3(0.0, 0.0, 3.0), vec3(0.0, 0.0, 0.0), vec3(0.0, 1.0, 0.0));
	glUniformMatrix4fv(view_loc, 1, GL_FALSE, (GLfloat*)&view_matrix[0]);

	projection_loc = glGetUniformLocation(program, "projection_matrix");
	projection_matrix = ortho(-2.0, 2.0, -2.0, 2.0, 0.2, 10.0);
	glUniformMatrix4fv(projection_loc, 1, GL_FALSE, (GLfloat*)&projection_matrix[0]);

	model_loc = glGetUniformLocation(program, "model_matrix");
	glUniformMatrix4fv(model_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
}

void Display(void)
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LEQUAL);

	glLineWidth(20.0);

	if (wire_frame)
	    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	else
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

	if (change_projection)
		projection_matrix = frustum(-0.5, 0.5, -0.5, 0.5, 0.2, 10.0);
	else
		projection_matrix = ortho(-2.0, 2.0, -2.0, 2.0, 0.2, 10.0);

	glUniformMatrix4fv(projection_loc, 1, GL_FALSE, (GLfloat*)&projection_matrix[0]);

	glBindVertexArray(cube_vao);
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, cube_ebo);

	model_matrix = {1, 0.5, 0, 0, 
					0.5, 1, 0, 0, 
					0, 0, 1, 0, 
					0, 0, 0, 1};
	glUniformMatrix4fv(model_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_SHORT, NULL);

	// Bottom Left
	model_matrix = translate(mat4(1.0), vec3(-0.6, -0.6, 0.0));
	glUniformMatrix4fv(model_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_SHORT, NULL);

	// Bottom Right
	model_matrix = scale(mat4(1.0), vec3(1.5, 1.5, 1.0))*translate(mat4(1.0), vec3(0.6, -0.6, 0.0));
	glUniformMatrix4fv(model_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_SHORT, NULL);

	// Top Right
	model_matrix = translate(mat4(1.0), vec3(0.6, 0.6, 0.0))*rotate(mat4(1.0), radians(45.0f), vec3(1.0, 0.0, 0.0));
	glUniformMatrix4fv(model_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_SHORT, NULL);

	// Top Left
	model_matrix = translate(mat4(1.0), vec3(-0.6, 0.6, 0.0))*rotate(mat4(1.0), radians(45.0f), vec3(1.0, 2.0, 3.0));
	glUniformMatrix4fv(model_loc, 1, GL_FALSE, (GLfloat*)&model_matrix[0]);
	glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_SHORT, NULL);

	glFlush();
}

/**************************************************************************************/
void keyboard(unsigned char key, int x, int y)
{
	switch (key)
	{
	case 'q':case 'Q':
		exit(EXIT_SUCCESS);
		break;
	case 'w':case 'W':
		wire_frame = !wire_frame;
		break;
	case 'c':case 'C':
		change_projection = !change_projection;
		break;
	}
	glutPostRedisplay();
}
/*********************************************************************************************/

void RotateCamera(int n)
{
	camX = sin(timer) * radius;
	camZ = cos(timer) * radius;
	view_matrix = lookAt(vec3(camX, 0.0, camZ), vec3(0.0, 0.0, 0.0), vec3(0.0, 1.0, 0.0));
	glUniformMatrix4fv(view_loc, 1, GL_FALSE, (GLfloat*)&view_matrix[0]);
	timer++;
	glutPostRedisplay();
	glutTimerFunc(500, RotateCamera, 1);
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGBA);
	glutInitWindowSize(1024, 1024);

	glutCreateWindow("Transformation, Camera, and Projection");

	if (glewInit()) {
		printf("Unable to initialize GLEW ... exiting\n");
	}

	Initialize();
	printf("%s\n", glGetString(GL_VERSION));
	glutDisplayFunc(Display);
	glutKeyboardFunc(keyboard);
	glutTimerFunc(500, RotateCamera, 1);
	glutMainLoop();
	return 0;
}