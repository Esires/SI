package FamiliaFinanzas;

import processing.core.PApplet;

public class MainApp extends PApplet {

    GUI gui;


    public static void main(String[] args) {
        PApplet.main("trivioAppBotons.Trivio005", args);
    }

    public void settings(){
        //fullScreen();                       // Pantalla completa
        size(1920, 1080);        // Pantalla HD
        smooth(10);
    }

    public void setup(){
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        gui = new GUI(this);                   // Constructor de la Buttons.GUI
    }

    public void draw(){

        // Dibuixa la pantalla corresponent
        switch(gui.pantallaActual){
            case INICIAL:   gui.dibuixaPantallaInicial(this);
                break;

            case ABOUT:     gui.dibuixaPantallaAbout(this);
                break;

            case DETALLS:   gui.dibuixaPantallaDetalls(this);
                break;
        }

        // Actualitza el cursor
        updateCursor();

    }

    // Estableix quin cursor emprar (HAND, ARROW)
    public void updateCursor(){
        if(gui.b1.updateHandCursor(this) || gui.b2.updateHandCursor(this)|| gui.b3.updateHandCursor(this) || gui.b4.updateHandCursor(this)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        }
        else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.DETALLS;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.ABOUT;
        }
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        if(gui.b1.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B1");
            gui.b4.setEnabled(true);
            gui.b4.setTextBoto("ENABLED");
        }
        else if(gui.b2.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B2");
        }
        else if(gui.b3.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B3");
        }
        else if(gui.b4.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B4");
            gui.b4.setEnabled(false);
            gui.b4.setTextBoto("DISABLED");
        }
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}