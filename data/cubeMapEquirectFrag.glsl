uniform samplerCube cubemap;

const float PI = 3.14159265;

void main() {

  float theta = gl_FragCoord.x * PI;
  float phi = gl_FragCoord.y * PI / 2.0;
    
  vec3 reflectDir = vec3(cos(phi) * cos(theta), sin(phi), cos(phi) * sin(theta));

  vec3 color = vec3(textureCube(cubemap, reflectDir));
  gl_FragColor = vec4(color, 1.0);      
}
