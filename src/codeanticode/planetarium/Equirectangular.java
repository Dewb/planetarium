/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package codeanticode.planetarium;

import java.lang.reflect.Method;
import java.nio.IntBuffer;

import processing.core.PApplet;
import processing.core.PShape;
import processing.opengl.PGL;
import processing.opengl.PGraphics3D;
import processing.opengl.PShader;

/**
 * Equirectangular variant of the dome renderer.
 * 
 */

public class Equirectangular extends Dome {
  public final static String RENDERER = "codeanticode.planetarium.Equirectangular";  
  public final static String VERSION  = "##library.prettyVersion##";

  protected PShape domeQuad;
  protected PShader cubeMapEquirectShader;

  protected void initDome() {
    super.initDome();

    lastFace = PGL.TEXTURE_CUBE_MAP_NEGATIVE_Z;

    if (domeQuad == null) {
      float x = 1.0f; 
      domeQuad = createShape(QUAD, -x, -x, -x, x, x, x, x, -x);
      domeQuad.setStroke(false);
    }

    if (cubeMapEquirectShader == null) {
      cubeMapEquirectShader = parent.loadShader("cubeMapEquirectFrag.glsl", 
                                                "cubeMapEquirectVert.glsl"); 
      cubeMapEquirectShader.set("cubemap", 1);
      cubeMapEquirectShader.set("resolution", screenWidth * 1.0f, screenHeight * 1.0f);
    }
  }

  protected void renderDome() {
    renderBorder();
    
    ortho(-1, 1, -1, 1);
    shader(cubeMapEquirectShader);
    shape(domeQuad);
    resetShader();
  
    renderScreen();
  }


}