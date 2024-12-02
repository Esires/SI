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
            gui.pantallaActual = GUI.PANTALLA.CASA;
        }
        else if(gui.bEscola.mouseOverButton(this)){
            gui.pantallaActual = GUI.PANTALLA.ESCOLA;
        }
        else if(gui.bEsports.mouseOverButton(this)){
            gui.pantallaActual = GUI.PANTALLA.ESPORTS;
        }
        else if(gui.bFamilia.mouseOverButton(this)){
            gui.pantallaActual = GUI.PANTALLA.OCI;
        }
        else if (gui.bGrafics.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.GRAFICS;
        }
        else if (gui.bIngressos.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INGRESOS;
        }
        else if (gui.bVolver.mouseOverButton(this) && !(gui.pantallaActual == GUI.PANTALLA.REGISTRAR)){
            gui.pantallaActual = GUI.PANTALLA.INICI;
        }
        else if (gui.bVolver.mouseOverButton(this) && gui.pantallaActual == GUI.PANTALLA.REGISTRAR){
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        }
    }

    /*public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }*/

}