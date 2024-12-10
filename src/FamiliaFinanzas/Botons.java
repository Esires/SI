package FamiliaFinanzas;
import Fonts.Mides;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Botons {

    float x, y, w, h;  // Posició (x, y) i dimensions (w, h)
    int fillColor, strokeColor; // Colors del boto (fill / stroke).
    int fillColorOver, fillColorDisabled;  // Colors del boto (actiu / inactiu).
    String textBoto;  // Text
    boolean enabled;  // Estat del botó (actiu / inactiu).
    PFont tipografia;
    PImage foto;

    // Constructor
    public Botons(PApplet p5, String text, float x, float y, float w, float h){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColor = p5.color(155, 10 ,10);
        this.fillColorOver = p5.color(255, 55, 155);
        this.fillColorDisabled = p5.color(150);
        this.strokeColor = p5.color(0);
        this.tipografia = p5.createFont("data/Refile.otf", Mides.midaBotons);
    }
    public Botons(PApplet p5, String text, float x, float y, float w, float h, String p){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColor = p5.color(255);
        this.fillColorOver = p5.color(255, 55, 155);
        this.fillColorDisabled = p5.color(150);
        this.strokeColor = p5.color(255);
        this.tipografia = p5.createFont("data/Refile.otf", Mides.midaBotons);
        this.foto = p5.loadImage(p);
    }

    // Setters

    public void setTipografia(PFont tipografia) {
        this.tipografia = tipografia;
    }

    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public void setTextBoto(String t){ this.textBoto = t; }

    public void setColors(int cFill, int cStroke, int cOver, int cDisabled){
        this.fillColor = cFill;
        this.strokeColor = cStroke;
        this.fillColorOver = cOver;
        this.fillColorDisabled = cDisabled;
    }

    // Getters
    public boolean isEnabled(){
        return  this.enabled;
    }

    // Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        if(!enabled){
            p5.fill(fillColorDisabled);  // Color desabilitat
        }
        else if(mouseOverButton(p5)){
            p5.fill(fillColorOver);      // Color quan ratolí a sobre
        }
        else{
            p5.fill(fillColor);          // Color actiu però ratolí fora
        }
        p5.stroke(strokeColor); p5.strokeWeight(2);        //Color i gruixa del contorn
        p5.rectMode(p5.CORNER);
        p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectangle del botó

        p5.textAlign(p5.CENTER); p5.textSize(Mides.midaBotons);p5.textFont(tipografia);
        if (foto != null){
            p5.fill(0);
            p5.image(foto, x, y, w, h);
            p5.text(textBoto, this.x + this.w/2, this.y + this.h+ 20);
        }
        else {
            p5.fill(255);
            p5.text(textBoto, this.x + this.w/2, this.y/2+ this.h/2+43);
        }


        p5.popStyle();
    }

    // Indica si el cursor està sobre el botó
    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    // Indica si cal posar el cursor a HAND
    public boolean updateHandCursor(PApplet p5){
        return mouseOverButton(p5) && enabled;
    }
}

