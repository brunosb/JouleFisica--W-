/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Path;
import trabalho.Trabalho;

/**
 * FXML Controller class
 *
 * @author BRUNO
 */
public class HookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnCalcular;

    @FXML
    private AreaChart<?, ?> graficoFelastica;

    @FXML
    private Label resulFelastica;

    @FXML
    private Label resulTrabalhoFelastica;

    @FXML
    private TextField txtK;

    @FXML
    private TextField txtX;
    
    @FXML
    private Group gpMola;
    
    double k=0,x=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Mascara Numero
        txtK.setOnKeyReleased(event -> Trabalho.numericField(txtK));
        txtX.setOnKeyReleased(event -> Trabalho.numericField(txtX));
        
        btnCalcular.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                k=0;x=0;
                graficoFelastica.getData().clear();
                if(txtK.getText().equals("") || txtX.getText().equals("")){
                    Trabalho.alerta();
                }else{
                    k = Double.parseDouble(txtK.getText());
                    x = Double.parseDouble(txtX.getText());

                    resulFelastica.setText("F_elástica = "+Trabalho.decimalFormat(k*x)+" N");
                    resulTrabalhoFelastica.setText("Trabalho (W) = "+Trabalho.decimalFormat((k*x*x)/2)+" J");

                    XYChart.Series area = new XYChart.Series();
                    area.setName("Joule");
                    area.getData().add(new XYChart.Data(0,0));
                    area.getData().add(new XYChart.Data(x,k*x));
                    graficoFelastica.getData().add(area);
                }
            }
        });       
    }    
    
}
