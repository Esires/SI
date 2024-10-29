import Buttons.Botons;
import processing.core.PApplet;

public class GUI {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, DETALLS, ABOUT};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    //Botons
    Botons b1, b2;
    //declarar components

    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIAL;
        // Inicialització de components (botons)
        creaBotons(p5);
    }

    public void  creaBotons(PApplet p5){
        b1 = new Botons(p5, "RED", 40, 400, 250, 100);
        b2 = new Botons(p5, "GREEN", 40, 550, 250, 100);
    }

}
