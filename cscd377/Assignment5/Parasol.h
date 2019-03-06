#include <stdio.h>
#include <GL/glew.h>
#include <math.h>

#define GLM_FORCE_RADIANS 

#include <glm/mat4x4.hpp>
#include <glm/gtc/matrix_transform.hpp> 

using namespace glm;

void createParasolOpen();
void createParasolClosed();
void drawParasol();

extern	unsigned int parasol_vao;
