/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.controles;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import trabalho.Cordenadas;
import trabalho.Trabalho;

/**
 * FXML Controller class
 *
 * @author BRUNO
 */
public class GraficoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCalcular;

    @FXML
    private AreaChart<?, ?> graficoGeral;

    @FXML
    private TextField txtResultado;

    @FXML
    private TableView<Cordenadas> tbvCordenadas;

    @FXML
    private Button btnLimparTabela;

    @FXML
    private TextField txtX;

    @FXML
    private TextField txtY;
    
    @FXML
    private TableColumn colunaY;

    @FXML
    private TableColumn colunaX;
    
    private double x,y;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mascara numerica
        txtX.setOnKeyReleased(event -> Trabalho.numericField(txtX));
        txtY.setOnKeyReleased(event -> Trabalho.numericField(txtY));
        
        //PONTOS DO GRAFICO
        ArrayList<Cordenadas> pontos = new ArrayList<Cordenadas>();
        
        //ATRIBUIR DADOS A TABELA
        ObservableList<Cordenadas> dados = FXCollections.observableArrayList();
        colunaX.setCellValueFactory(new PropertyValueFactory("x"));
        colunaY.setCellValueFactory(new PropertyValueFactory("y"));
        
       
        btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                tbvCordenadas.setItems(dados);
                if(txtX.getText().equals("") || txtY.getText().equals("")){
                    Trabalho.alerta();
                }else{
                    pontos.add(new Cordenadas(Double.parseDouble(txtX.getText()),Double.parseDouble(txtY.getText())));
                    dados.add(new Cordenadas(Double.parseDouble(txtX.getText()),Double.parseDouble(txtY.getText())));                  
                    txtX.clear();
                    txtY.clear();
                    
                    XYChart.Series popular = new XYChart.Series();
                    for(int i=0;i<pontos.size();i++){
                        Cordenadas xy = pontos.get(i);
                        popular.setName("A"+i);
                        popular.getData().add(new XYChart.Data(xy.getX(),xy.getY()));
                    }
                    graficoGeral.getData().add(popular);
                    
                }
            }
        });
        //CALCULAR O GRAFICO
        btnCalcular.setOnAction(event -> calcAreaGrafico(pontos));
        
        //LIMPAR TUDO
        btnLimparTabela.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                graficoGeral.getData().clear();
                pontos.clear();
                tbvCordenadas.getItems().clear();
                txtResultado.clear();
            }
        });
    }
    
    
    public void calcAreaGrafico(ArrayList<Cordenadas> xy){
        double somaArea = 0;
        for(int i=0;i<xy.size()-1;i++){
            double b=0,h=0,bm=0,area=0;
            //Triângulo /|
            if((xy.get(i).getY()==0) && (xy.get(i+1).getY()>xy.get(i).getY()) && (xy.get(i).getX()<xy.get(i+1).getX())){
                b = xy.get(i+1).getX()-xy.get(i).getX();
                h = xy.get(i+1).getY();
                area = (b*h)/2;
                somaArea+=area;
            }
            //Triângulo |\
            else if((xy.get(i).getY()>0) && (xy.get(i+1).getY()==0) && (xy.get(i+1).getX()>xy.get(i).getX())){
                b = xy.get(i+1).getX()-xy.get(i).getX();
                h = xy.get(i).getY();
                area = (b*h)/2;
                somaArea+=area;
            }
            //Retângulo
            else if(Objects.equals(xy.get(i).getY(), xy.get(i+1).getY()) && xy.get(i).getX() < xy.get(i+1).getX()){
                b = xy.get(i+1).getX()-xy.get(i).getX();
                h = xy.get(i).getY();
                area = b*h;
                somaArea+=area;
            }
            //Trapézio ->
            else if(xy.get(i).getY()>0 && xy.get(i+1).getY()>xy.get(i).getY() && xy.get(i).getX()<xy.get(i+1).getX()){
                b = xy.get(i).getY();
                bm = xy.get(i+1).getY();
                h = xy.get(i+1).getX()-xy.get(i).getX();
                area = ((b+bm)*h)/2;
                somaArea+=area;
            }
            //Trapézio <-
            else if(xy.get(i+1).getY()>0 && xy.get(i).getY()>xy.get(i+1).getY() && xy.get(i).getX()<xy.get(i+1).getX()){
                b = xy.get(i+1).getY();
                bm = xy.get(i).getY();
                h = xy.get(i+1).getX()-xy.get(i).getX();
                area = ((b+bm)*h)/2;
                somaArea+=area;
            }
            //nenhum desenho reconhecido
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Área de A"+i+" e Área de A"+(i+1)+
                                        " Não são reconhecidas", ButtonType.OK);
                alert.show();
            }
        }
        txtResultado.setText(Trabalho.decimalFormat(somaArea)+" J");
    }
}
