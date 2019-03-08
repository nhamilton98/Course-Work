#version 430 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec3 normal;

uniform mat4 model_matrix;
uniform mat4 view_matrix;
uniform mat4 projection_matrix;

uniform vec4 LightPosition;

out vec3 LightDirection;
out vec3 EyeSpaceNormal;
out vec3 ViewDirection;

void main(void)
{
	vec4 EyeSpacePosition = view_matrix * model_matrix * position;

	EyeSpaceNormal = mat3(view_matrix * model_matrix) * normal;
	LightDirection = LightPosition.xyz - EyeSpacePosition.xyz;
	ViewDirection = vec3(0.0) - vec3(EyeSpacePosition);

	gl_Position = projection_matrix * view_matrix * model_matrix * position;
}

