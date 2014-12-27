/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author BRUNO
 */

public class Cordenadas {
    
    private final DoubleProperty x,y;
    private final double x2,y2;

    public Cordenadas(double a, double b){
        this.x = new SimpleDoubleProperty(a);
        this.y = new SimpleDoubleProperty(b);
        
        this.x2 = a;
        this.y2 = b;
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
        return y;
    }
    
     public double getX() {
        return x2;
    }

    public double getY() {
        return y2;
    }
    
}
