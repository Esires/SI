package FamiliaFinanzas;

import Pantalles.Mides;
import processing.core.PApplet;

public class MainApp extends PApplet {

    GUI gui;


    public static void main(String[] args) {
        PApplet.main("FamiliaFinanzas.MainApp", args);
    }

    public void settings(){
        //fullScreen();                       // Pantalla completa
        size(1920, 1080);        // Pantalla HD
        smooth(10);
    }

    public void setup(){
        noStroke();                         // Sense bordes
        textAlign(CENTER); textSize(18);   // Alineació i mida del text
        Mides.sidebarX = width - (Mides.sidebarWidth + 20);
        gui = new GUI(this);// Constructor de la Buttons.GUI
    }

    public void draw(){

        // Dibuixa la pantalla corresponent
        switch(gui.pantallaActual){
            case INICIAL:   gui.dibuixaPantallaInicial(this);
                break;

            case INICI:     gui.dibuixaPantallaInici(this);
                break;

            case REGISTRAR:   gui.dibuixaPantallaRegistre(this);
                break;

            case GRAFICS: gui.dibuixaPantallaGrafic(this);
                break;

            case OCI: gui.dibuixaPantallaOci(this);
                break;

            case ESPORTS: gui.dibuixaPantallaEsport(this);
                break;

            case INGRESOS: gui.dibuixaPantallaIngresos(this);
                break;

            case CASA: gui.dibuixaPantallaCasa(this);
                break;

            case ESCOLA: gui.dibuixaPantallaEscola(this);
                break;
        }

        // Actualitza el cursor
        updateCursor();

        text(gui.pantallaActual.toString(), 100, 100);

    }

    // Estableix quin cursor emprar (HAND, ARROW)
    public void updateCursor(){
        if(gui.bCasa.updateHandCursor(this) || gui.bEscola.updateHandCursor(this)|| gui.bEsports.updateHandCursor(this) || gui.bFamilia.updateHandCursor(this) || gui.bGrafics.updateHandCursor(this) || gui.bIngressos.updateHandCursor(this)){
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
            gui.pantallaActual = GUI.PANTALLA.INICI;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.REGISTRAR;
        }
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        if(gui.bCasa.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B1");
        }
        else if(gui.bEscola.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B2");
        }
        else if(gui.bEsports.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B3");
        }
        else if(gui.bFamilia.mouseOverButton(this)){
            println("HAS FET CLIC SOBRE EL BOTÓ B4");
        }
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }

}