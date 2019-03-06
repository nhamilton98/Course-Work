#version 430 core

layout (location = 0) in vec4 position;

uniform mat4 model_matrix;
uniform vec2 scalar;

void main(void){
	vec4 scale_factor = vec4(scalar, 0.0, 1.0);
    gl_Position = model_matrix*position*scale_factor;
}
