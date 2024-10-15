package Colors;
import processing.core.PApplet;
public class PColors {

    int[] colors;

    public PColors(PApplet p5){
        this.setColors(p5);
    }

    // Estableix colors de l'App
    void setColors(PApplet p5){
        this.colors = new int[10];
        this.colors[0] = p5.color(0xFFFFFFFF);
        this.colors[1] = p5.color(0xFF000000);
        this.colors[2] = p5.color(0xFF22316E);
        this.colors[3] = p5.color(0xFF3DB147);
        this.colors[4] = p5.color(0xFFD3CB0B);
        this.colors[5] = p5.color(0xFF792671);
        this.colors[6] = p5.color(0xFF3270C2);
        this.colors[7] = p5.color(0xFFED4E85);
        this.colors[8] = p5.color(0xFFE32C4A);
        this.colors[9] = p5.color(0xFF7BA547);
    }

    // Getter del número de colors
    int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    int getFirstColor(){
        return  this.colors[0];
    }

    // Getter del color secundari
    int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-èssim
    int getColorAt(int i){
        return this.colors[i];
    }


    // Dibuixa paleta de colors
    void displayColors(PApplet p5, float x, float y, float w){
        p5.pushStyle();
        //Llegenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("Colors:", x, y-10);

        // Paleta de colors
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }

}
