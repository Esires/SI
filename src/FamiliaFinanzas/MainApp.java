package FamiliaFinanzas;

import processing.core.PApplet;

public class MainApp extends PApplet {

    GUI gui;

    DataBase db;

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
        db = new DataBase("admin", "12345", "prova");
        db.connect();
    }

    public void draw(){

        // Dibuixa la pantalla corresponent
        switch(gui.pantallaActual){
            case INICIAL:   gui.dibuixaPantallaInicial(this);
                break;

            case SESSIO:     gui.dibuixaPantallaSessio(this);
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
    }

    // Estableix quin cursor emprar (HAND, ARROW)
    public void updateCursor(){
        if(gui.bCasa.updateHandCursor(this) || gui.bEscola.updateHandCursor(this)|| gui.bEsports.updateHandCursor(this) || gui.bFamilia.updateHandCursor(this) || gui.bGrafics.updateHandCursor(this) || gui.bIngressos.updateHandCursor(this) || gui.bVolver.updateHandCursor(this)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }

    }

    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        gui.tUsuari1.keyPressed(key, keyCode);
        gui.tUsuari2.keyPressed(key, keyCode);
        gui.tCasa.keyPressed(key, keyCode);
        gui.tEscola.keyPressed(key, keyCode);
        gui.tEsport.keyPressed(key, keyCode);
        gui.tFamilia.keyPressed(key, keyCode);
        gui.pContraseña.keyPressed(key, keyCode);
        gui.pContraseña1.keyPressed(key, keyCode);
        gui.pContraseña2.keyPressed(key, keyCode);
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        if(gui.pantallaActual==GUI.PANTALLA.CASA){
            if(gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.sCasa.mouseOverSelect(this)){
                gui.sCasa.toggle();
                gui.sCasa.update(this);
            }


        }
        else if(gui.pantallaActual== GUI.PANTALLA.ESPORTS){
            if(gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.sEsport.mouseOverSelect(this)){
                gui.sEsport.toggle();
                gui.sEsport.update(this);
            }
            gui.rBEsport1.updateOnClick(this);
            gui.rBEsport2.updateOnClick(this);

            if(gui.rBEsport1.selectedOption==1){
                gui.showrBCasa2 = true;
            }
            else {
                gui.showrBCasa2 = false;
            }
        }
        else if (gui.pantallaActual==GUI.PANTALLA.SESSIO){
            if (gui.bRegistrar.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.REGISTRAR;
            }
        }
        else if (gui.pantallaActual== GUI.PANTALLA.REGISTRAR){
            if (gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual= GUI.PANTALLA.SESSIO;
            }
            if (gui.bEnregistret.mouseOverButton(this) && (gui.pContraseña1 == gui.pContraseña2)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
        }
        else if (gui.pantallaActual== GUI.PANTALLA.ESCOLA){
            if(gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.sEscola.mouseOverSelect(this)){
                gui.sEscola.toggle();
                gui.sEscola.update(this);
            }
        }
        else if (gui.pantallaActual== GUI.PANTALLA.OCI){
            if(gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.sFamilia.mouseOverSelect(this)){
                gui.sFamilia.toggle();
                gui.sFamilia.update(this);
            }

            gui.tFamilia.mouseOverTextField(this);
            if(gui.cBMembre1.onMouseOver(this)){
                gui.cBMembre1.toggle();
            }
            else if(gui.cBMembre2.onMouseOver(this)){
                gui.cBMembre2.toggle();
            }
            else if(gui.cBMembre3.onMouseOver(this)){
                gui.cBMembre3.toggle();
            }
        }
        else if (gui.pantallaActual==GUI.PANTALLA.GRAFICS){
            if(gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
            if(gui.sGrafic.mouseOverSelect(this)){
                gui.sGrafic.toggle();
                gui.sGrafic.update(this);
            }
            if(gui.cBMembre1.onMouseOver(this)){
                gui.cBMembre1.toggle();
            }
            else if(gui.cBMembre2.onMouseOver(this)){
                gui.cBMembre2.toggle();
            }
            else if(gui.cBMembre3.onMouseOver(this)){
                gui.cBMembre3.toggle();
            }
        }
        else if (gui.pantallaActual== GUI.PANTALLA.INGRESOS){
            if(gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
        }
        else if (gui.pantallaActual== GUI.PANTALLA.INICIAL){
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

        }
        gui.tUsuari1.isPressed(this);
        gui.tUsuari2.isPressed(this);
        gui.tCasa.isPressed(this);
        gui.pContraseña.isPressed(this);
        gui.pContraseña1.isPressed(this);
        gui.pContraseña2.isPressed(this);
    }

    /*public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }*/

}