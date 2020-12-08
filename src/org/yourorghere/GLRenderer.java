package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GLRenderer implements GLEventListener {

    private float rotation = 0.6f;
    private float translation = -0.5f;

    @Override
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        plano(gl);
        switch (SimpleGLCanvas.getTypeForm()) {
            case 1:
                // piramide
                plano(gl);  
                piramide(gl);
                break;
            case 2:
                // cubo
                plano(gl);
                cubo(gl);
                break;
            case 3:
                // cone
                plano(gl);
                System.out.println("CONE GERADO");
                break;
            case 4:
                // cilindro
                plano(gl);
                System.out.println("CILINDRO GERADO!");
                break;
            default:
        }
    }

    private void plano(GL gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, -1.0f, -6.0f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_LINES);
        for (float i = -2.5f; i <= 2.5f; i += 0.25f) {
            gl.glVertex3f(i, 0f, 2.5f);
            gl.glVertex3f(i, 0f, -2.5f);
            gl.glVertex3f(2.5f, 0f, i);
            gl.glVertex3f(-2.5f, 0f, i);
        }
        gl.glEnd();
        gl.glFlush();
    }

    private void piramide(GL gl) {
        gl.glTranslatef(translation, 0.0f, -6.0f);
        gl.glRotatef(rotation, 1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(1.0f, 0f, 0f);// eixo x
        gl.glVertex3f(0, 0f, 0f);
        gl.glVertex3f(3.0f, 0f, 0f);

        gl.glColor3f(0f, 1f, 0f);//eixo y
        gl.glVertex3f(0, 0f, 0f);
        gl.glVertex3f(0, 2.025f, 0f);

        gl.glColor3f(0f, 0f, 1f);//eixo z
        gl.glVertex3f(0, 0f, 0f);
        gl.glVertex3f(0, 0f, 3.5f);

        gl.glEnd();
        
        if (SimpleGLCanvas.getTypeTransform() == 0) {
            translation -= -0.005f;
        }
        //gl.glTranslatef(translation, 0.0f, -6.0f);
        if (SimpleGLCanvas.getTypeTransform() == 1) {
            rotation += 0.6f;
        }
        //gl.glRotatef(rotation, 1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_TRIANGLES);
        // Front  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.5f, 2f, 0.0f); // Top Of Triangle   

        gl.glColor3f(0.0f, 1.5f, 0.0f); // Green  
        gl.glVertex3f(-1.5f, -1.5f, 1.5f); // Left Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(1.5f, -1.5f, 1.5f); // Right Of Triangle   

        // Right  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.5f, 2.0f, 0.0f); // Top Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(1.5f, -1.5f, 1.5f); // Left Of Triangle   

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(1.5f, -1.5f, -1.5f); // Right Of Triangle   

        // Back  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.5f, 2.0f, 0.0f); // Top Of Triangle   

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(1.5f, -1.5f, -1.5f); // Left Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(-1.5f, -1.5f, -1.5f); // Right Of Triangle   

        //left  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.5f, 2.0f, 0.0f); // Top Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(-1.5f, -1.5f, -1.5f); // Left Of Triangle   

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(-1.5f, -1.5f, 1.5f); // Right Of Triangle   

        gl.glEnd();
        gl.glFlush();
    }

    private void cubo(GL gl) {
        gl.glTranslatef(translation, 0.0f, -6.0f);
        gl.glRotatef(rotation, 1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(1.0f, 0f, 0f);// eixo x
        gl.glVertex3f(0, 0f, 0f);
        gl.glVertex3f(3.0f, 0f, 0f);

        gl.glColor3f(0f, 1f, 0f);//eixo y
        gl.glVertex3f(0, 0f, 0f);
        gl.glVertex3f(0, 2.025f, 0f);

        gl.glColor3f(0f, 0f, 1f);//eixo z
        gl.glVertex3f(0, 0f, 0f);
        gl.glVertex3f(0, 0f, 3.5f);

        gl.glEnd();
        if (SimpleGLCanvas.getTypeTransform() == 0) {
            translation -= -0.005f;
        }
        //gl.glTranslatef(translation, 0.0f, -6.0f);
        if (SimpleGLCanvas.getTypeTransform() == 1) {
            rotation += 0.6f;
        }
        //gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0f, 0f, 1f); //Blue color  
        //Top Quadrilateral  
        gl.glVertex3f(0.5f, 0.5f, -0.5f); //Upper Right  
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left  
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Bottom Left  
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Bottom Right  
        //Below Quadrilateral  
        gl.glColor3f(1f, 0f, 0f); //Red color  
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Upper Right   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right   
        //Front Quadrilateral  
        gl.glColor3f(0f, 1f, 0f); //Green color  
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Right   
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Right  
        //Back Quadrilateral  
        gl.glColor3f(1f, 1f, 0f); //Yellow  
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Right   
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Right   
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Left   
        //Left Quadrilateral  
        gl.glColor3f(1f, 0f, 1f); //Purple  
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Right  
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Right   
        //Right Quadrilateral  
        gl.glColor3f(0f, 1f, 1f); //Cyan  
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Right   
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right   
        gl.glEnd();
        gl.glFlush();
    }
  
    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
