/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author BRUNO
 */

public class Trabalho extends Application {
    
    public static Scene SCENE;
    public static Stage STAGE;
    
    //Alerta de Campos em Branco
    public static final void alerta(){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Campos em Branco, favor preenchelos", ButtonType.OK);
        alert.show();
    }
    
    //Formatar casas decimais
    public static double decimalFormat(double num){
        DecimalFormat df = new DecimalFormat("0.00");
        String numero = df.format(num);
        String[] part = numero.split(",");
        String numfinal = part[0]+"."+part[1];
        return Double.parseDouble(numfinal);
    }
    
    // Mascara de numeros
    public static void numericField(final TextField textField) {
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = textField.getText().charAt(oldValue.intValue());
                    if (!(ch >= '0' && ch <= '9' || ch == '.')) {
                        textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                    }
                }
            }
        });
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/trabalho/telas/Login.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        SCENE = scene;
     
        STAGE = stage;
        STAGE.setScene(scene);
        STAGE.setResizable(false);
        STAGE.setTitle("Tela de Login");
        STAGE.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
