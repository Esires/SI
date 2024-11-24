package FamiliaFinanzas;

import Colors.PColors;
import Fonts.Tipografies;
import processing.core.PApplet;
import processing.core.PImage;

import static Pantalles.Mides.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, DETALLS, ABOUT};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Components de la Buttons.GUI
    Botons bCasa, bEscola, bEsports, bFamilia, bGrafics, bIngressos, bRegistrar, bEntrar, bVolver;
    Tipografies fontsGUI;
    PColors colorsGUI;
    TextField t1, t2;
    PImage logo;

    // Constructor de la Buttons.GUI
    public GUI(PApplet p5){
        fontsGUI = new Tipografies(p5);
        colorsGUI = new PColors(p5);
        pantallaActual = PANTALLA.INICIAL;
        logo = loadImatge("Logo.jpg");

        // Inicialització de components (botons)
        bCasa = new Botons(p5, "Casa", sidebarX+25, sidebarY+150, 250, 100);
        bEscola = new Botons(p5, "Escola i Extraescolars", sidebarX+25, sidebarY+350, 250, 100);
        bEsports = new Botons(p5, "Esports", sidebarX+25, sidebarY+550, 250, 100);
        bFamilia = new Botons(p5, "Familia i Oci", sidebarX+25, sidebarY+750, 250, 100);
        bGrafics = new Botons(p5, "Gràfics", 400, 800, 350, 200);
        bIngressos = new Botons(p5, "Ingressos", 900, 800, 350, 200);
        bRegistrar = new Botons(p5, "Registrarse", 900, 850,350, 150);
        bEntrar = new Botons(p5,"Entrar", 1300,850, 350, 150 );
        bVolver = new Botons(p5, "Volver", 1300, 200, 200, 100);
    }




    // PANTALLES DE LA Buttons.GUI

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(255);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
        bRegistrar.display(p5);
        bEntrar.display(p5);
    }

    public void dibuixaPantallaInici(PApplet p5){
        p5.background(245);
        dibuixaLogo(p5);

    }

    public void dibuixaPantallaRegistre(PApplet p5){
        p5.background(245);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
        bRegistrar.display(p5);
        bVolver.display(p5);
    }


    // ZONES DE LA Buttons.GUI

    public void dibuixaLogo(PApplet p5){
        p5.imageMode(p5.CENTER);
        p5.image(logo, marginH, marginV, logoWidth, logoHeight);
    }

    public void dibuixaSideBar(PApplet p5){
        // Zona Sidebar
        p5.fill(50,200,100);
        p5.rectMode(p5.CORNER);
        p5.rect(sidebarX, sidebarY, sidebarWidth, sidebarHeight);
        p5.fill(0);

        bCasa.display(p5);
        bEscola.display(p5);
        bEsports.display(p5);
        bFamilia.display(p5);
        bGrafics.display(p5);
        bIngressos.display(p5);
    }
    //Zona TextFields
    public void dibuixaUsuari(PApplet p5){

    }

    public void dibuixaContrasenya(PApplet p5){

    }

}