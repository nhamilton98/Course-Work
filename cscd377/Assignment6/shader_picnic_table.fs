#version 430 core

uniform vec3 Ambient;
uniform vec3 MaterialColor;
uniform vec3 LightColor;

in vec3 LightDirection;
in vec3 EyeSpaceNormal;
in vec3 ViewDirection;

out vec4 finalColor;

void main(void)
{
    vec3 N = normalize(EyeSpaceNormal);
    vec3 L = normalize(LightDirection);
    vec3 V = normalize(ViewDirection);

    vec3 H = normalize(L + V);

    float diffuse = max(0.0, dot(N, L));
    float specular = pow(max(0.0, dot(N, H)), 50.0);

    vec3 scatteredColor =  Ambient + diffuse * LightColor;
    vec3 reflectedLight = specular * LightColor;

    finalColor = min(vec4(MaterialColor*scatteredColor + reflectedLight, 1.0), vec4(1.0));
}
