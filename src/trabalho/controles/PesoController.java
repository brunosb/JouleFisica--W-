/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.util.Duration;
import trabalho.Trabalho;

/**
 * FXML Controller class
 *
 * @author BRUNO
 */
public class PesoController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TextField txtPeso;

    @FXML
    private RadioButton formula1;

    @FXML
    private Button btnCalcular;

    @FXML
    private AreaChart<?, ?> graficoFpeso;

    @FXML
    private Label resultado;

    @FXML
    private ToggleGroup formulaPeso;

    @FXML
    private RadioButton formula2;

    @FXML
    private Label selecionarAcima;

    @FXML
    private TextField txtMassa;

    @FXML
    private TextField txtGravidade;

    @FXML
    private TextField txtAltura;
    
    @FXML
    private Line lnCaminho;
    
    @FXML
    private Circle cBola;

    double peso=0,massa=0,gravidade=0,altura=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lnCaminho.getStrokeDashArray().addAll(3d);
        cBola.setVisible(false);
        
        //Desabilitar componentes
        txtPeso.setEditable(false);
        txtPeso.setFocusTraversable(false);
        txtMassa.setEditable(false);
        txtMassa.setFocusTraversable(false);
        txtGravidade.setEditable(false);
        txtGravidade.setFocusTraversable(false);
        txtAltura.setEditable(false);
        txtAltura.setFocusTraversable(false);
        resultado.setVisible(false);
        
        //Campos com mascaras de números
        txtPeso.setOnKeyReleased(event -> trabalho.Trabalho.numericField(txtPeso));
        txtMassa.setOnKeyReleased(event -> trabalho.Trabalho.numericField(txtMassa));
        txtGravidade.setOnKeyReleased(event -> trabalho.Trabalho.numericField(txtGravidade));
        txtAltura.setOnKeyReleased(event -> trabalho.Trabalho.numericField(txtAltura));
       
        //Setando campos pelas formulas
        formulaPeso.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(formulaPeso.getSelectedToggle() == formula1){
                    selecionarAcima.setVisible(false);
                    resultado.setVisible(false);
                    txtPeso.setEditable(true);
                    txtPeso.setFocusTraversable(true);
                    txtPeso.clear();
                    txtAltura.setEditable(true);
                    txtAltura.setFocusTraversable(true);
                    txtAltura.clear();
                    txtMassa.setEditable(false);
                    txtMassa.setFocusTraversable(false);
                    txtMassa.clear();
                    txtGravidade.setEditable(false);
                    txtGravidade.setFocusTraversable(false);
                    txtGravidade.clear();
                }else if(formulaPeso.getSelectedToggle() == formula2){
                    selecionarAcima.setVisible(false);
                    resultado.setVisible(false);
                    txtPeso.setEditable(false);
                    txtPeso.setFocusTraversable(false);
                    txtPeso.clear();
                    txtAltura.setEditable(true);
                    txtAltura.setFocusTraversable(true);
                    txtAltura.clear();
                    txtMassa.setEditable(true);
                    txtMassa.setFocusTraversable(true);
                    txtMassa.clear();
                    txtGravidade.setEditable(true);
                    txtGravidade.setFocusTraversable(true);
                    txtGravidade.clear();
                }else{
                    selecionarAcima.setVisible(true);
                    resultado.setVisible(false);
                    txtPeso.setEditable(false);
                    txtAltura.setEditable(false);
                    txtMassa.setEditable(false);
                    txtGravidade.setEditable(false);
                }
            }
        });
        
        //Calcular trabalho        
        btnCalcular.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(formula1.isSelected()){
                    peso=0;altura=0;massa=0;gravidade=0;
                    graficoFpeso.getData().clear();
                    if(txtPeso.getText().equals("") || txtAltura.getText().equals("")){
                        Trabalho.alerta();
                    }else{
                        peso = Double.parseDouble(txtPeso.getText());
                        altura = Double.parseDouble(txtAltura.getText());
                        resultado.setText("Trabalho (W) = "+Trabalho.decimalFormat(peso*altura)+" J");
                        resultado.setVisible(true);
                        
                        //Popular Grafico
                        XYChart.Series area = new XYChart.Series();
                        area.setName("Joule");
                        area.getData().add(new XYChart.Data(0,peso));
                        area.getData().add(new XYChart.Data(altura,peso));
                        graficoFpeso.getData().add(area);
                        
                        //Animação
                        animacao();
                    }
                }else if(formula2.isSelected()){
                    peso=0;altura=0;massa=0;gravidade=0;
                    graficoFpeso.getData().clear();
                    if(txtMassa.getText().equals("") || txtGravidade.getText().equals("") || txtAltura.getText().equals("")){
                        Trabalho.alerta();
                    }else{
                        massa = Double.parseDouble(txtMassa.getText());
                        gravidade = Double.parseDouble(txtGravidade.getText());
                        altura = Double.parseDouble(txtAltura.getText());
                        resultado.setText("Trabalho (W) = "+Trabalho.decimalFormat((massa*gravidade)*altura)+" J");
                        resultado.setVisible(true);
                        
                        //Popular Gráfico
                        XYChart.Series area = new XYChart.Series();
                        area.setName("Joule");
                        area.getData().add(new XYChart.Data(0,(massa*gravidade)));
                        area.getData().add(new XYChart.Data(altura,(massa*gravidade)));
                        graficoFpeso.getData().add(area);
                        
                        //Animação
                        animacao();
                    }
                }
            }
        });
    } 
    
    private PathTransition transicao;
    
    public void animacao(){
        cBola.setVisible(true);
        transicao = PathTransitionBuilder.create()
                    .duration(Duration.seconds(4))
                    .path(lnCaminho)
                    .node(cBola)
                    .orientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT)
                    .autoReverse(false)
                    .build();
        transicao.play();
    }
    
}
