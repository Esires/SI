package Colors;
import processing.core.PApplet;

public class MainColors extends PApplet {

    // Colors de l'App
    PColors appColors;

    public static void main(String[] args) {
        PApplet.main("Colors.MainColors", args);
    }

    public void settings(){
        size(800, 800, P2D);
        smooth(10);
    }

    public void setup(){

        // Constructor dels colors de l'App
        appColors = new PColors(this);

    }

    public void draw(){
        // Dibuixa el fons (blanc)
        background(255);

        // Dibuixa rectangle amb 5è color (exemple d'emprar els colors directament).
        fill(appColors.getColorAt(4)); noStroke();
        rect(0, 3*height/4, width, height/4);

        // Mostra la paleta de colors
        appColors.displayColors(this, 100,100,width-200);
    }


    // ******************* KEYBOARD interaction ***************************** //

    public void keyPressed(){
        println("KEY PRESSED");
    }

    // ******************* MOUSE interaction ***************************** //

    public void mousePressed(){
        println("MOUSE PRESSED");
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }


}
