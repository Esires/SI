package FamiliaFinanzas;

import processing.core.PApplet;
import processing.core.PImage;

import static FamiliaFinanzas.Mides.*;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, SESSIO, REGISTRAR, CASA, ESPORTS, GRAFICS, INGRESOS, OCI, ESCOLA};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Components de la Buttons.GUI
    Botons bCasa, bEscola, bEsports, bFamilia, bGrafics, bIngressos, bRegistrar, bEntrar, bVolver, bEnregistret, bAplicar, bReset;
    Tipografies fontsGUI;
    PColors colorsGUI;
    TextField tUsuari1, tUsuari2, tCasa, tEscola, tEsport, tFamilia;
    PassField pContraseña, pContraseña1, pContraseña2;
    PImage logo;
    RadioButton rBC1, rBC2, rBC3, rBC4, rBC5, rBC6;
    RadioButtonGroup rBCasa1, rBCasa2;
    boolean showrBCasa2;
    CheckBox cBMembre1, cBMembre2, cBMembre3;
    Select sCasa, sEscola, sFamilia, sEsport;
    String [] sC = {"Electricitat", "Aigua", "Compra"};
    String [] sE = {"Material Escolar", "Viatge d'Estudis", "Repàs"};
    String [] sF = {"Excursio", "Restaurant", "Viatge"};
    String [] sEs = {"D'Equip", "Individual", "Ball"};
    /*String [] ss5 = {"hola", "adeu", "sí"};
    String [] ss6 = {"hola", "adeu", "sí"};
    String [] ss7 = {"hola", "adeu", "sí"};
    String [] ss8 = {"hola", "adeu", "sí"};*/


    // Constructor de la Buttons.GUI
    public GUI(PApplet p5){
        fontsGUI = new Tipografies(p5);
        colorsGUI = new PColors(p5);
        this.pantallaActual = PANTALLA.INICIAL;
        logo = p5.loadImage("LogoEntrar.jpg");
        tUsuari1 = new TextField(p5,910, 400, textFieldW, textFieldH );
        tUsuari2 = new TextField(p5,910, 300, textFieldW, textFieldH );
        pContraseña = new PassField(p5, 910, 600, textFieldW, textFieldH);
        pContraseña1 = new PassField(p5, 910, 450, textFieldW, textFieldH);
        pContraseña2 = new PassField(p5, 910, 600, textFieldW, textFieldH);
        sCasa = new Select(sC, 400, 250, 400, 75);
        sCasa.setFontSelect(fontsGUI.getThirdFont());
        sEscola = new Select(sE, 400, 250, 400, 75);
        sEscola.setFontSelect(fontsGUI.getThirdFont());
        sFamilia = new Select(sF, 250, 250, 400, 75);
        sFamilia.setFontSelect((fontsGUI.getThirdFont()));
        sEsport = new Select(sEs, 400, 250, 400, 75);
        sEsport.setFontSelect(fontsGUI.getThirdFont());
        tCasa = new TextField(p5,1100, 250, 250, 75);
        tEscola = new TextField(p5,1100, 250, 250, 75);
        tEsport = new TextField(p5,1100, 250, 250, 75);
        tFamilia = new TextField(p5,900, 250, 250, 75);
        rBC1 = new RadioButton(p5, 1300,275,15, "Espontani");
        rBC2 = new RadioButton(p5, 1300,375,15, "Regular");
        rBC3 = new RadioButton(p5, 1300,475,15, "Diaria");
        rBC4 = new RadioButton(p5, 1300,575,15, "Setmanal");
        rBC5 = new RadioButton(p5, 1300,675,15, "Mensual");
        rBC6 = new RadioButton(p5, 1300,775,15, "Anual");
        rBCasa1 = new RadioButtonGroup(2);
        rBCasa1.setRadioButtons(rBC1, rBC2);
        rBCasa2 = new RadioButtonGroup(4);
        rBCasa2.setRadioButtons(rBC3, rBC4, rBC5, rBC6);
        showrBCasa2 = false;
        cBMembre1 = new CheckBox(p5, 1300, 250, 25, "membre 1");
        cBMembre2 = new CheckBox(p5, 1300, 350, 25, "membre 2");
        cBMembre3 = new CheckBox(p5, 1300, 450, 25, "membre 3");
        // Inicialització de components (botons)
        bCasa = new Botons(p5, "Casa", sidebarX+50, sidebarY+50, 200, 200, "BotoCasa.jpg");
        bEscola = new Botons(p5, "Escola i Extraescolars", sidebarX+50, sidebarY+300, 200, 200, "BotoEscola.jpg");
        bEsports = new Botons(p5, "Esports", sidebarX+50, sidebarY+550, 200, 200, "BotoEsports.jpg");
        bFamilia = new Botons(p5, "Familia i Oci", sidebarX+50, sidebarY+800, 200, 200, "BotoFamilia.jpg");
        bGrafics = new Botons(p5, "Gràfics", 400, 800, 350, 200, "BotoGrafics.jpg");
        bIngressos = new Botons(p5, "Ingressos", 900, 800, 350, 200, "BotoIngresos.jpg");
        bRegistrar = new Botons(p5, "Registrarse", 900, 750,350, 150);
        bEntrar = new Botons(p5,"Entrar", 1300,750, 350, 150 );
        bVolver = new Botons(p5, "Volver", 1700, 75, 200, 100);
        bEnregistret = new Botons(p5, "Enregistret",900, 750,350, 150);
        bAplicar = new Botons(p5,"Aplicar",  600, 900, 350, 75);
        bReset = new Botons(p5, "Reset", 1100, 900, 350, 75);
    }





    // PANTALLES DE LA Buttons.GUI

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(colorsGUI.getFirstColor());
        //dibuixaLogo(p5);
        dibuixaSideBar(p5);
        bCasa.display(p5);
        bEscola.display(p5);
        bEsports.display(p5);
        bFamilia.display(p5);
        bGrafics.display(p5);
        bIngressos.display(p5);
    }

    public void dibuixaPantallaSessio(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        dibuixaLogoEntrar(p5);
        bRegistrar.display(p5);
        bEntrar.display(p5);
        tUsuari1.display(p5);
        pContraseña.display(p5);
        p5.textFont(this.fontsGUI.getThirdFont());
        p5.textSize(Mides.midaTitol);
        p5.text("Usuari", tUsuari1.x+75, tUsuari1.y-50);
    }

    public void dibuixaPantallaRegistre(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        dibuixaLogoEntrar(p5);
        bEnregistret.display(p5);
        bVolver.display(p5);
        tUsuari2.display(p5);
        pContraseña1.display(p5);
        pContraseña2.display(p5);
    }

    public void dibuixaPantallaCasa(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        sCasa.display(p5);
        tCasa.display(p5);
       // rBCasa1.display(p5);
        //if (showrBCasa2 == true){rBCasa2.display(p5);};
        bAplicar.display(p5);
        bReset.display(p5);
    }

    public void dibuixaPantallaOci(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
        cBMembre1.display(p5);
        cBMembre2.display(p5);
        cBMembre3.display(p5);
        sFamilia.display(p5);
        tFamilia.display(p5);
    }

    public void dibuixaPantallaGrafic(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
    }

    public void dibuixaPantallaEsport(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
        sEsport.display(p5);
        tEsport.display(p5);
    }

    public void dibuixaPantallaIngresos(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
    }

    public void dibuixaPantallaEscola(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
        sEscola.display(p5);
        tEscola.display(p5);
    }


    // ZONES DE LA Buttons.GUI

    public void dibuixaLogoEntrar(PApplet p5){
        p5.imageMode(p5.CORNER);
        p5.image(logo, marginH+175, marginV+150, logoWidth, logoHeight);
    }

    public void dibuixaSideBar(PApplet p5){
        // Zona Sidebar
        p5.fill(50,200,100);
        p5.rectMode(p5.CORNER);
        p5.rect(sidebarX, sidebarY, sidebarWidth, sidebarHeight);
        p5.fill(0);
    }
    //Zona TextFields

}