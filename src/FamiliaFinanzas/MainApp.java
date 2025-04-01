package FamiliaFinanzas;

import processing.core.PApplet;

public class MainApp extends PApplet {

    GUI gui;

    BaseDeDades db;

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
        db = new BaseDeDades("admin", "12345", "avaluaciointerna");
        db.connect();
        /*
        db.getInfo("PASSWORD", "usuario", "Aina", "NOMBRE");
        String [] infoColumna = db.getInfoArray("usuario", "NOMBRE");
        printArray(infoColumna);
        String[][] infoTaula = db.getInfoArray2DUsuario();
        println("TAULA: ");
        for (int i=0; i<infoTaula.length; i++){
            print(i+": ");
            for (int j = 0; j<infoTaula[i].length;j++){
                System.out.print(infoTaula[i][j]+"\t");
            }
            println();
        }

        String[] infoQuery = db.getInfoPareMotiu();
        printArray(infoQuery);

        String[][] info2Taules = db.getInfoGastoMotiuPare();
        for (int i=0; i<info2Taules.length; i++){
            print(i+": ");
            for (int j = 0; j<info2Taules[i].length;j++){
                System.out.print(info2Taules[i][j]+"\t");
            }
            println();
        }

        int totalImportUsuari = db.getImporteUsuari("Eduard");
        System.out.println("TOTAL IMPORT EDUARD: " + totalImportUsuari);

        boolean existeix = db.checkLogIn("Eduard", "2007Eduard2007");
        System.out.println(existeix);

        //db.insertaUsuario("Papa", "1975Papa1975", "U");

        //db.deleteUsuario("Papa");

        db.updatePassword("Mama", "1978Mama1978");

         */
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
        gui.tID.keyPressed(key, keyCode);
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
                gui.showrBEsport2 = true;
            }
            else {
                gui.showrBEsport2 = false;
            }
        }
        else if (gui.pantallaActual==GUI.PANTALLA.SESSIO){
            if (gui.bRegistrar.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.REGISTRAR;
            }
            if(gui.bEntrar.mouseOverButton(this) && db.checkLogIn(gui.tUsuari1.text, gui.pContraseña.text)){
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            }
        }
        else if (gui.pantallaActual== GUI.PANTALLA.REGISTRAR){
            if (gui.bVolver.mouseOverButton(this)){
                gui.pantallaActual= GUI.PANTALLA.SESSIO;
            }
            if(gui.sRegistre.mouseOverSelect(this)){
                gui.sRegistre.toggle();
                gui.sRegistre.update(this);
            }
            if (gui.bEnregistret.mouseOverButton(this) && (gui.pContraseña1.text.equals(gui.pContraseña2.text))){
                db.insertaUsuario(gui.tUsuari2.text, gui.pContraseña1.text, gui.sRegistre.selectedValue);
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
            gui.rBGrafic1.updateOnClick(this);
            gui.rBGrafic2.updateOnClick(this);
            gui.rBGrafic3.updateOnClick(this);
            gui.rBGrafic4.updateOnClick(this);
            gui.rBGrafic5.updateOnClick(this);
            if(gui.rBGrafic1.selectedOption==0){
                gui.showrBGrafic2 = true;
                gui.showrBGrafic3 = false;
                gui.showrBGrafic4 = false;
                gui.showrBGrafic5 = false;
            }else if(gui.rBGrafic1.selectedOption==1){
                gui.showrBGrafic3 = true;
                gui.showrBGrafic2 = false;
                gui.showrBGrafic4 = false;
                gui.showrBGrafic5 = false;
            }else if(gui.rBGrafic1.selectedOption==2){
                gui.showrBGrafic4 = true;
                gui.showrBGrafic3 = false;
                gui.showrBGrafic2 = false;
                gui.showrBGrafic5 = false;
            }else if(gui.rBGrafic1.selectedOption==3){
                gui.showrBGrafic5 = true;
                gui.showrBGrafic2 = false;
                gui.showrBGrafic3 = false;
                gui.showrBGrafic4 = false;
            }
            else {
                gui.showrBGrafic2 = false;
                gui.showrBGrafic3 = false;
                gui.showrBGrafic4 = false;
                gui.showrBGrafic5 = false;
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
        gui.tID.isPressed(this);
    }

    /*public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }*/

}