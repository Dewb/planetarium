uniform samplerCube cubemap;
uniform vec2 resolution;

const float PI = 3.14159265;

void main() {

  float x = (gl_FragCoord.x / resolution.x) * 2.0 - 1.0;
  float y = (gl_FragCoord.y / resolution.y) * 2.0 - 1.0;
  float theta = x * PI;
  float phi = y * PI / 2.0;
   
  float cos_phi = cos(phi);
  float cos_theta = cos(theta);
  float sin_phi = sin(phi);
  float sin_theta = sin(theta);

  vec3 reflectDir = vec3(cos_phi * cos_theta, sin_phi, cos_phi * sin_theta);

  vec3 color = vec3(textureCube(cubemap, reflectDir));
  gl_FragColor = vec4(color, 1.0); 

  // debugging stuff
  //if ((1.0 - abs(x)) < 0.05) { gl_FragColor.rgb = vec3(0.0, 1.0, 1.0); }     
  //if ((1.0 - abs(y)) < 0.05) { gl_FragColor.rgb = vec3(0.0, 1.0, 1.0); }     
  //gl_FragColor.rgb += 0.2 * vec3(x, 0, y);
}
