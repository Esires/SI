package FamiliaFinanzas;

import processing.core.PApplet;

import static Pantalles.Mides.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, DETALLS, ABOUT};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Components de la Buttons.GUI
    Botons b1, b2, b3, b4;

    // Constructor de la Buttons.GUI
    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIAL;

        // Inicialització de components (botons)
        b1 = new Botons(p5, "RED", 40, 400, 250, 100);
        b2 = new Botons(p5, "GREEN", 40, 550, 250, 100);
        b3 = new Botons(p5, "BLUE", 40, 700, 250, 100);
        b4 = new Botons(p5, "DISABLED", 40, 850, 250, 100);

        b4.setEnabled(false); // Desactiva el botó b4
    }




    // PANTALLES DE LA Buttons.GUI

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(55);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(55);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
    }

    public void dibuixaPantallaDetalls(PApplet p5){
        p5.background(55);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
    }


    // ZONES DE LA Buttons.GUI

    public void dibuixaLogo(PApplet p5){
        p5.fill(200,50,100);
        p5.rect(marginH, marginV, logoWidth, logoHeight);
        p5.fill(0);
        p5.text("LOGO", marginH + logoWidth/2, marginV + logoHeight/2);
    }

    public void dibuixaSideBar(PApplet p5){
        // Zona Sidebar
        p5.fill(50,200,100);
        p5.rect(marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        p5.fill(0);
        p5.text("SIDEBAR", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2);

        b1.display(p5);
        b2.display(p5);
        b3.display(p5);
        b4.display(p5);
    }

}