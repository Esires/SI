/**
 * Clase que representa la interfaz gráfica de usuario (GUI) de la aplicación FamiliaFinanzas.
 *
 * <p>Esta clase gestiona todas las pantallas, componentes visuales y su interacción en la aplicación.
 * Proporciona métodos para dibujar las diferentes pantallas y manejar los elementos de la interfaz.</p>
 */
package FamiliaFinanzas;

import processing.core.PApplet;
import processing.core.PImage;

import static FamiliaFinanzas.Mides.*;

public class GUI {

    /**
     * Enumerado que representa las diferentes pantallas disponibles en la aplicación.
     */
    public enum PANTALLA {INICIAL, SESSIO, REGISTRAR, CASA, ESPORTS, GRAFICS, INGRESOS, OCI, ESCOLA};

    /**
     * Pantalla actualmente visible en la aplicación.
     */
    public PANTALLA pantallaActual;

    // Componentes de la GUI
    Botons bCasa, bEscola, bEsports, bFamilia, bGrafics, bIngressos, bRegistrar, bEntrar, bVolver, bEnregistret, bAplicar, bReset;
    Tipografies fontsGUI;
    PColors colorsGUI;
    TextField tUsuari1, tUsuari2, tCasa, tEscola, tEsport, tFamilia, tID;
    PassField pContraseña, pContraseña1, pContraseña2;
    PImage logo, logo2;
    RadioButton rBE1, rBE2, rBE3, rBE4, rBE5, rBE6, rBG1, rBG2, rBG3, rBG4, rBG5, rBG6, rBG7, rBG8, rBG9, rBG10, rBG11, rBG12,
            rBG13, rBG14, rBG15, rBG16, rB1, rB2;
    RadioButtonGroup rBEsport1, rBEsport2, rBGrafic1, rBGrafic2, rBGrafic3, rBGrafic4, rBGrafic5, rBCasa;
    boolean showrBEsport2, showrBGrafic2, showrBGrafic3, showrBGrafic4, showrBGrafic5;
    CheckBox cBMembre1, cBMembre2, cBMembre3;
    Select sRegistre, sCasa, sEscola, sFamilia, sEsport, sGrafic;
    String [] sC = {"Electricitat", "Aigua", "Compra"};
    String [] sE = {"Material Escolar", "Viatge d'Estudis", "Repàs"};
    String [] sF = {"Excursio", "Restaurant", "Viatge"};
    String [] sEs = {"D'Equip", "Individual", "Ball"};
    String [] sG = {"Semanal", "Mensual", "Semestral", "Anual", "Total"};
    String [] sR = {"A", "U"};


    /**
     * Constructor de la clase GUI.
     *
     * @param p5 Objeto PApplet de Processing que permite el dibujo y la interacción
     *
     * <p>Inicializa todos los componentes de la interfaz:
     * - Fuentes y colores
     * - Campos de texto y contraseña
     * - Selectores desplegables
     * - Botones de radio y grupos de botones de radio
     * - Botones de acción
     * - Imágenes del logo</p>
     */
    public GUI(PApplet p5){
        fontsGUI = new Tipografies(p5);
        colorsGUI = new PColors(p5);
        this.pantallaActual = PANTALLA.CASA;
        logo = p5.loadImage("LogoEntrar.jpg");
        logo2 = p5.loadImage("LogoLlarg.jpg");
        tUsuari1 = new TextField(p5,910, 400, textFieldW, textFieldH );
        tUsuari2 = new TextField(p5,910, 150, textFieldW, textFieldH );
        tID = new TextField(p5, 70, 350, textFieldW, textFieldH);
        pContraseña = new PassField(p5, 910, 600, textFieldW, textFieldH);
        pContraseña1 = new PassField(p5, 910, 350, textFieldW, textFieldH);
        pContraseña2 = new PassField(p5, 910, 550, textFieldW, textFieldH);
        sRegistre = new Select(sR, 910, 750, 400, 75);
        sRegistre.setFontSelect(fontsGUI.getThirdFont());
        sCasa = new Select(sC, 750, 350, 400, 75);
        sCasa.setFontSelect(fontsGUI.getThirdFont());
        sEscola = new Select(sE, 400, 250, 400, 75);
        sEscola.setFontSelect(fontsGUI.getThirdFont());
        sFamilia = new Select(sF, 250, 250, 400, 75);
        sFamilia.setFontSelect((fontsGUI.getThirdFont()));
        sEsport = new Select(sEs, 400, 250, 400, 75);
        sEsport.setFontSelect(fontsGUI.getThirdFont());
        sGrafic = new Select(sG,100, 250, 400, 75);
        sGrafic.setFontSelect((fontsGUI.getThirdFont()));
        tCasa = new TextField(p5,1240, 350, 250, 75);
        tEscola = new TextField(p5,1100, 250, 250, 75);
        tEsport = new TextField(p5,950, 250, 250, 75);
        tFamilia = new TextField(p5,850, 250, 250, 75);
        rB1 = new RadioButton(p5, 1570, 375, 15, "Sí");
        rB2 = new RadioButton(p5, 1570, 475, 15, "No");
        rBCasa = new RadioButtonGroup(2);
        rBCasa.setRadioButtons(rB1, rB2);
        rBE1 = new RadioButton(p5, 1300,275,15, "Espontani");
        rBE2 = new RadioButton(p5, 1300,375,15, "Regular");
        rBE3 = new RadioButton(p5, 1300,475,15, "Diaria");
        rBE4 = new RadioButton(p5, 1300,575,15, "Setmanal");
        rBE5 = new RadioButton(p5, 1300,675,15, "Mensual");
        rBE6 = new RadioButton(p5, 1300,775,15, "Anual");
        rBEsport1 = new RadioButtonGroup(2);
        rBEsport1.setRadioButtons(rBE1, rBE2);
        rBEsport2 = new RadioButtonGroup(4);
        rBEsport2.setRadioButtons(rBE3, rBE4, rBE5, rBE6);
        showrBEsport2 = false;
        rBG1 = new RadioButton(p5, 600, 275, 15, "Casa");
        rBG2 = new RadioButton(p5, 600, 375, 15, "Escuela y Extraescolares");
        rBG3 = new RadioButton(p5, 600, 475, 15, "Deportes");
        rBG4 = new RadioButton(p5, 600, 575, 15, "Ocio y Familia");
        rBG5 = new RadioButton(p5, 1050, 275, 15, "Electricidad");
        rBG6 = new RadioButton(p5, 1050, 375, 15, "Agua");
        rBG7 = new RadioButton(p5, 1050, 475, 15, "Compra");
        rBG8 = new RadioButton(p5, 1050, 275, 15, "Material escolar");
        rBG9 = new RadioButton(p5, 1050, 375, 15, "Viage de estudios");
        rBG10 = new RadioButton(p5, 1050, 475, 15, "Repaso");
        rBG11 = new RadioButton(p5, 1050, 275, 15, "De equipo");
        rBG12 = new RadioButton(p5, 1050, 375, 15, "Individual");
        rBG13 = new RadioButton(p5, 1050, 475, 15, "Baile");
        rBG14 = new RadioButton(p5, 1050, 275, 15, "Excursión");
        rBG15 = new RadioButton(p5, 1050, 375, 15, "Restaurante");
        rBG16 = new RadioButton(p5, 1050, 475, 15, "Viaje");
        rBGrafic1 = new RadioButtonGroup(4);
        rBGrafic1.setRadioButtons(rBG1, rBG2, rBG3, rBG4);
        rBGrafic2 = new RadioButtonGroup(3);
        rBGrafic2.setRadioButtons(rBG5, rBG6, rBG7);
        showrBGrafic2 = false;
        rBGrafic3 = new RadioButtonGroup(3);
        rBGrafic3.setRadioButtons(rBG8, rBG9, rBG10);
        showrBGrafic3 = false;
        rBGrafic4 = new RadioButtonGroup(3);
        rBGrafic4.setRadioButtons(rBG11, rBG12, rBG13);
        showrBGrafic4 = false;
        rBGrafic5 = new RadioButtonGroup(3);
        rBGrafic5.setRadioButtons(rBG14, rBG15, rBG16);
        showrBGrafic5 = false;
        // Inicialització de components (botons)
        bCasa = new Botons(p5, "Casa", sidebarX+50, sidebarY+50, 200, 200, "BotoCasa.jpg");
        bEscola = new Botons(p5, "Escuela i Extraescolares", sidebarX+50, sidebarY+300, 200, 200, "BotoEscola.jpg");
        bEsports = new Botons(p5, "Deportes", sidebarX+50, sidebarY+550, 200, 200, "BotoEsports.jpg");
        bFamilia = new Botons(p5, "Familia i Ocio", sidebarX+50, sidebarY+800, 200, 200, "BotoFamilia.jpg");
        bGrafics = new Botons(p5, "Gráficos", 400, 800, 350, 200, "BotoGrafics.jpg");
        bIngressos = new Botons(p5, "Ingresos", 900, 800, 350, 200, "BotoIngresos.jpg");
        bRegistrar = new Botons(p5, "Registrarse", 900, 750,350, 150);
        bEntrar = new Botons(p5,"Entrar", 1300,750, 350, 150 );
        bVolver = new Botons(p5, "Volver", 1700, 75, 200, 100);
        bEnregistret = new Botons(p5, "Registrate",910, 850,350, 150);
        bAplicar = new Botons(p5,"Aplicar",  600, 900, 350, 75);
        bReset = new Botons(p5, "Reset", 1100, 900, 350, 75);
    }

    // MÉTODOS PARA DIBUJAR PANTALLAS

    /**
     * Dibuja la pantalla inicial de la aplicación.
     *
     * @param p5 Objeto PApplet de Processing para el dibujo
     *
     * <p>Este método:
     * 1. Establece el color de fondo
     * 2. Dibuja la barra lateral
     * 3. Muestra los botones principales
     * 4. Muestra el logo principal</p>
     */

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(colorsGUI.getFirstColor());
        //dibuixaLogo(p5);
        dibuixaSideBar(p5);
        bCasa.display(p5);
        bEscola.display(p5);
        bEsports.display(p5);
        bFamilia.display(p5);
        bGrafics.display(p5);
        //bIngressos.display(p5);
        dibuixaLogoInici(p5);
    }

    /**
     * Dibuja la pantalla de inicio de sesión.
     *
     * @param p5 Objeto PApplet de Processing para el dibujo
     *
     * <p>Este método muestra:
     * - Logo de entrada
     * - Campos para usuario y contraseña
     * - Botones para registrar o entrar</p>
     */

    public void dibuixaPantallaSessio(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        dibuixaLogoEntrar(p5);
        bRegistrar.display(p5);
        bEntrar.display(p5);
        tUsuari1.display(p5);
        pContraseña.display(p5);
        p5.fill(0);
        p5.textFont(this.fontsGUI.getThirdFont());
        p5.textSize(Mides.midaTitol);
        p5.text("Usuario", tUsuari1.x+100, tUsuari1.y-20);
        p5.text("Contraseña", pContraseña.x+135, pContraseña.y-20);

    }

    /**
     * Dibuja la pantalla de registro de nuevos usuarios.
     *
     * @param p5 Objeto PApplet de Processing para el dibujo
     *
     * <p>Este método muestra:
     * - Campos para usuario, contraseña y confirmación
     * - Selector de rol (A/U)
     * - Botones para registrarse o volver</p>
     */

    public void dibuixaPantallaRegistre(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        dibuixaLogoEntrar(p5);
        bEnregistret.display(p5);
        bVolver.display(p5);
        tUsuari2.display(p5);
        pContraseña1.display(p5);
        pContraseña2.display(p5);
        sRegistre.display(p5);
        p5.fill(0);
        p5.textFont(this.fontsGUI.getThirdFont());
        p5.textSize(Mides.midaTitol);
        p5.text("Usuario", tUsuari2.x+100, tUsuari2.y-20);
        p5.text("Contraseña", pContraseña1.x+135, pContraseña1.y-20);
        p5.text("Repite la contraseña", pContraseña2.x+235, pContraseña2.y-20);
        p5.text("Elige tu rol", sRegistre.x+125, sRegistre.y-20);
    }

    /**
     * Dibuja la pantalla de gestión de gastos del hogar.
     *
     * @param p5 Objeto PApplet de Processing para el dibujo
     *
     * <p>Este método muestra:
     * - Selector de tipo de gasto (Electricidad, Agua, Compra)
     * - Campos para identificador e importe
     * - Radio buttons para indicar si es un gasto compartido
     * - Botones para aplicar cambios o resetear</p>
     */

    public void dibuixaPantallaCasa(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        sCasa.display(p5);
        tCasa.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
        tID.display(p5);
        rBCasa.display(p5);
        p5.fill(0);
        p5.textFont(this.fontsGUI.getThirdFont());
        p5.textSize(midaTitol);
        p5.text("Identificador", tID.x+155, tID.y-20);
        p5.text("Motivo", sCasa.x+95, tID.y-20);
        p5.text("Importe", tCasa.x+95, tID.y-20);
        p5.text("Compartido", rB1.x+95, tID.y-20);
    }

    public void dibuixaPantallaOci(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
        rBCasa.display(p5);
        p5.fill(0);
        p5.textFont(this.fontsGUI.getThirdFont());
        p5.textSize(midaTitol);
        p5.text("Compartido", rB1.x+95, tID.y-20);
        sFamilia.display(p5);
        tFamilia.display(p5);
    }

    public void dibuixaPantallaGrafic(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
        sGrafic.display(p5);
        rBCasa.display(p5);
        p5.fill(0);
        p5.textFont(this.fontsGUI.getThirdFont());
        p5.textSize(midaTitol);
        p5.text("Compartido", rB1.x+95, tID.y-20);
        rBGrafic1.display(p5);
        if (showrBGrafic2 == true){rBGrafic2.display(p5);};
        if (showrBGrafic3 == true){rBGrafic3.display(p5);};
        if (showrBGrafic4 == true){rBGrafic4.display(p5);};
        if (showrBGrafic5 == true){rBGrafic5.display(p5);};
    }

    public void dibuixaPantallaEsport(PApplet p5){
        p5.background(colorsGUI.getFirstColor());
        bVolver.display(p5);
        bAplicar.display(p5);
        bReset.display(p5);
        sEsport.display(p5);
        tEsport.display(p5);
        rBEsport1.display(p5);
        if (showrBEsport2 == true){rBEsport2.display(p5);};
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

    public void dibuixaLogoInici(PApplet p5){
        p5.imageMode(p5.CORNER);
        p5.image(logo2, marginH+150, marginV+50, logoWidth+200, logoHeight);
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