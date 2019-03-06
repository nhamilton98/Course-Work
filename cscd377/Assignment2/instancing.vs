#version 430 core

layout (location = 0) in vec4 vPosition;
layout (location = 1) in vec4 instanceColor;
layout (location = 2) in vec4 instancePosition;

uniform vec3 scalar;

out vec4 color;

void main(void)
{
    vec4 scale_factor = vec4(scalar, 1.0);
    gl_Position = vPosition * scale_factor + instancePosition;
    color = instanceColor;
}
