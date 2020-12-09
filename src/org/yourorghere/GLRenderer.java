package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GLRenderer implements GLEventListener {

    private float rotation = 0.6f;
    private float translation = -0.5f;
    private float eixoXRotacao = -0.5f;
    private float eixoYRotacao = 0.0f;
    private float eixoZRotacao = -6.0f;

    private float eixoXTranslacao = -0.5f;
    private float eixoYTranslacao = 0.0f;
    private float eixoZTranslacao = -6.0f;
    
    private float valuePerspective = 60.0f;

    
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
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
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
        //valuePerspective = SimpleGLCanvas.getPerspective();
        
        glu.gluPerspective(60.0f, 4.0/3.0, 1.0, 40);
        
//      glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(4, 6, 5, 0, 0, 0, 0, 1, 0);
    }

 
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        plano(gl);
        iluminacao(gl);
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
            case 5:
                // piramide e cubo
                //System.out.println("PIRAMIDE E CUBO");
                piramide(gl);
                cubo(gl);
                break;
            case 6:
                // piramide e cone
                System.out.println("PIRAMIDE E CONE");
                break;
            case 7:
                // piramide e cilindro
                System.out.println("PIRAMIDE E CILINDRO");
                break;
            case 8:
                System.out.println("CUBO E CONE");
                break;
            case 9:
                System.out.println("CUBO E CILINDRO");
                break;
            case 10:
                System.out.println("CILINRO E CONE");
                break;
            default:
                nothing(gl);
                break;
        }
    }
    
    public void nothing(GL gl) {
        gl.glEnd();
        gl.glFlush();
    }

    private void iluminacao(GL gl) {

        float ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);

        float diffuse[] = {0.8f, 0.8f, 0.8f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse, 0);

        float specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, specular, 0);

        float position[] = {0.0f, 0.0f, 1.0f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);

        float matEspecular[] = {1.0f, 1.0f, 1.0f, 1.0f};

        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, matEspecular, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 128);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        // habilitar a coloração dos objetos
        gl.glEnable(GL.GL_COLOR_MATERIAL);

        //gl.glEnable(GL.GL_BLEND);
        //gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
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
        lines(gl);

//        if (SimpleGLCanvas.getTypeTransform() == 0) {
//            translation -= -0.005f;
//        }
        //gl.glTranslatef(translation, 0.0f, -6.0f);
        if(SimpleGLCanvas.getTypeTransform() == 2){
            rotation += 0.6f;
        }
        else if (SimpleGLCanvas.getTypeTransform() == 1) {
           rotation += 0.6f;
        }
        //gl.glRotatef(rotation, 1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_TRIANGLES);
        // Front  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Of Triangle   

        gl.glColor3f(0.0f, 1.5f, 0.0f); // Green  
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Left Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Right Of Triangle   

        // Right  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Left Of Triangle   

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Right Of Triangle   

        // Back  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Of Triangle   

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Left Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Right Of Triangle   

        //left  
        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow  
        gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Of Triangle   

        gl.glColor3f(1.0f, 0.0f, 1.0f); // Purple  
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Left Of Triangle   

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green  
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Right Of Triangle   

        gl.glEnd();
        gl.glFlush();
    }

    private void cubo(GL gl) {
        lines(gl);
//        if (SimpleGLCanvas.getTypeTransform() == 0) {
//            translation -= -0.005f;
//        }
        //gl.glTranslatef(translation, 0.0f, -6.0f);
        if(SimpleGLCanvas.getTypeTransform() == 2){
            rotation += 0.6f;
        }
        else if (SimpleGLCanvas.getTypeTransform() == 1) {
            rotation += 0.6f;
        }
        //gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_QUADS);

        gl.glColor3f(0f, 0f, 1f); //Blue color
        //gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f); //Transparent    
        //Top Quadrilateral  
        gl.glVertex3f(0.5f, 0.5f, -0.5f); //Upper Right  
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left  
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Bottom Left  
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Bottom Right  
        //Below Quadrilateral  
        gl.glColor3f(1f, 0f, 0f); //Red color
        //gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f); //Transparent       
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Upper Right   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right   
        //Front Quadrilateral  
        gl.glColor3f(0f, 1f, 0f); //Green color
        //gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f); //Transparent    
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Right   
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Right  
        //Back Quadrilateral  
        gl.glColor3f(1f, 1f, 0f); //Yellow 
        //gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f); //Transparent     
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Right   
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Right   
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Left   
        //Left Quadrilateral  
        gl.glColor3f(1f, 0f, 1f); //Purple  
        //gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f); //Transparent       
        gl.glVertex3f(-0.5f, 0.5f, 0.5f); // Upper Right  
        gl.glVertex3f(-0.5f, 0.5f, -0.5f); // Upper Left   
        gl.glVertex3f(-0.5f, -0.5f, -0.5f); // Bottom Left   
        gl.glVertex3f(-0.5f, -0.5f, 0.5f); // Bottom Right   
        //Right Quadrilateral  
        gl.glColor3f(0f, 1f, 1f); //Cyan  
        //gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f); //Transparent     
        gl.glVertex3f(0.5f, 0.5f, -0.5f); // Upper Right   
        gl.glVertex3f(0.5f, 0.5f, 0.5f); // Upper Left   
        gl.glVertex3f(0.5f, -0.5f, 0.5f); // Bottom Left   
        gl.glVertex3f(0.5f, -0.5f, -0.5f); // Bottom Right   
        gl.glEnd();
        gl.glFlush();
    }

    private void cilindro() {

    }

    private void conde() {

    }

    private void lines(GL gl) {

        translation = SimpleGLCanvas.getEixoXRotacao();
        eixoXRotacao = SimpleGLCanvas.getEixoXRotacao();
        eixoYRotacao = SimpleGLCanvas.getEixoYRotacao();
        eixoZRotacao = SimpleGLCanvas.getEixoZRotacao();
        
        eixoXTranslacao = SimpleGLCanvas.getEixoXTranslacao();
        eixoYTranslacao = SimpleGLCanvas.getEixoYTranslacao();
        eixoZTranslacao = SimpleGLCanvas.getEixoZTranslacao();

        gl.glTranslatef(eixoXTranslacao, eixoYTranslacao, eixoZTranslacao);
        gl.glRotatef(rotation, eixoXRotacao, eixoYRotacao, eixoZRotacao);
        System.out.println("eixo x rotacao = " + eixoXRotacao);
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
    }

    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
