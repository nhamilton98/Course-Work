#include <stdio.h>
#include <GL/glew.h>
#include <math.h>

#define GLM_FORCE_RADIANS 

#include <glm/mat4x4.hpp>
#include <glm/gtc/matrix_transform.hpp>

using namespace glm;

#define NumTriangles 50
#define NumVertices 3 * NumTriangles

void createCone();
void drawCone();

extern	unsigned int cone_vao;