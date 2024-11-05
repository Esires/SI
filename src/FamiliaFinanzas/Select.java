package FamiliaFinanzas;

public class Select {

    float x, y, w, h;          // Posició i dimensions
    String[] texts;            // Valors possibles
    String selectedValue;      // Valor Seleccionat

    boolean collapsed = true;  // Plegat / Desplegat
    boolean enabled;           // Abilitat / desabilitat

    float lineSpace = 15;      // Espai entre línies

    public Select(String[] texts, float x, float y, float w, float h){

        this.texts = texts;
        this.selectedValue = "";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.collapsed = true;
    }



}
