package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JTextField;


public class GLRenderer implements GLEventListener {
    
    private float rotation = 0.0f;
    Dicionary d;
  
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

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

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );  
        gl.glLoadIdentity();  
        gl.glTranslatef( 0f, 0f, -2.0f );   

        gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);   

        gl.glBegin(GL.GL_QUADS);   
        gl.glColor3f(0f,0f,1f); //Blue color  
              //Top Quadrilateral  
        gl.glVertex3f(0.5f, 0.5f, -0.5f); //Upper Right  
        gl.glVertex3f( -0.5f, 0.5f, -0.5f); // Upper Left  
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Bottom Left  
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Bottom Right  
                //Below Quadrilateral  
        gl.glColor3f( 1f,0f,0f ); //Red color  
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Upper Right   
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Upper Left   
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left   
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right   
              //Front Quadrilateral  
        gl.glColor3f( 0f,1f,0f ); //Green color  
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Upper Right   
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Upper Left   
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Left   
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Right  
              //Back Quadrilateral  
        gl.glColor3f( 1f,1f,0f ); //Yellow  
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Left   
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Right   
        gl.glVertex3f( -0.5f, 0.5f, -0.5f ); // Upper Right   
        gl.glVertex3f( 0.5f, 0.5f, -0.5f ); // Upper Left   
              //Left Quadrilateral  
        gl.glColor3f( 1f,0f,1f ); //Purple  
        gl.glVertex3f( -0.5f, 0.5f, 0.5f ); // Upper Right  
        gl.glVertex3f( -0.5f, 0.5f, -0.5f ); // Upper Left   
        gl.glVertex3f( -0.5f, -0.5f, -0.5f ); // Bottom Left   
        gl.glVertex3f( -0.5f, -0.5f, 0.5f ); // Bottom Right   
              //Right Quadrilateral  
        gl.glColor3f( 0f,1f, 1f ); //Cyan  
        gl.glVertex3f( 0.5f, 0.5f, -0.5f ); // Upper Right   
        gl.glVertex3f( 0.5f, 0.5f, 0.5f ); // Upper Left   
        gl.glVertex3f( 0.5f, -0.5f, 0.5f ); // Bottom Left   
        gl.glVertex3f( 0.5f, -0.5f, -0.5f ); // Bottom Right   
        gl.glEnd();   
        gl.glFlush();  

        rotation += 0.6f;  
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

