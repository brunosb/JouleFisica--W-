/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import trabalho.Trabalho;

/**
 * FXML Controller class
 *
 * @author BRUNO
 */
public class ForcaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtFcontra;

    @FXML
    private TextField txtdistancia;

    @FXML
    private Button btnCalcular;

    @FXML
    private Label resultado;

    @FXML
    private Rectangle bloco;

    @FXML
    private TextField txtFfavor;

    @FXML
    private TextField txtFangulo;

    @FXML
    private TextField txtangulo;

    @FXML
    private ToggleButton ForcaFavor;

    @FXML
    private Label avisoSelecionar;

    @FXML
    private ToggleButton ForcaAngulo;

    @FXML
    private TextField txtFresultante;

    @FXML
    private ToggleButton ForcaContraria;
    
    @FXML
    private Polygon pontaLineFavor;
    @FXML
    private Line lineFavor;
    
    @FXML
    private Polygon pontaLineContra;
    @FXML
    private Line lineContra;

        
    @FXML
    private Polygon pontaLineAngulo;
    @FXML
    private Line lineAngulo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // (F1 + Fd.cos() - Fcontra)*d = WJ
        /*
               B   _______    A
           <------|_______|------->
            B=F2           A= F1+Fdiagonal
        Caso 1    B=A entao d=0 e W=0   parado
          Caso 2    A>B  formula -> (A-B)*d= W    pra frente
           Caso3    B>A   formula -> (-1)(B-A)*d=W    pra tras     
        */
        
        
        //Desabilitar visibilidade de flexas
        
        lineFavor.setVisible(false);
        pontaLineFavor.setVisible(false);
      
        lineContra.setVisible(false);
        pontaLineContra.setVisible(false);
       
        lineAngulo.setVisible(false);
        pontaLineAngulo.setVisible(false);
       
        
        //------------ADICIONAR MASCARAS--------------------------
        Trabalho.numericField(txtFfavor);
        Trabalho.numericField(txtFcontra);
        Trabalho.numericField(txtFangulo);
        Trabalho.numericField(txtangulo);
        Trabalho.numericField(txtdistancia);
        
        // ----------DESABILITAR E HABILITAR CAMPOS-----------------
        
        txtFfavor.setEditable(false);
        txtFcontra.setEditable(false);
        txtFangulo.setEditable(false);
        txtangulo.setEditable(false);
        txtdistancia.setEditable(false);
        txtFresultante.setEditable(false);
        
        // ---------------ANGULO--------------
 
        txtangulo.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                
               if(!txtangulo.getText().equals("")){
                   double anguloo = Double.parseDouble(txtangulo.getText());
                   if(anguloo>90){
                       Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Versão Beta, Não aceitamos ângulos maiores que 90º", ButtonType.OK);
                       alerta.show();
                       txtangulo.clear();
                   }
               }
            }
        });
       
        
        //----------------BOTÕES------------------------
        ForcaFavor.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(ForcaFavor.isSelected()){
                    txtFfavor.setEditable(true);
                    txtdistancia.setEditable(true);
                    lineFavor.setVisible(true);
                    pontaLineFavor.setVisible(true);
                    posInicial();
                    
                }else{
                    txtFfavor.setEditable(false);
                    txtdistancia.setEditable(false);
                    txtFfavor.clear();
                    txtdistancia.clear();
                    txtFresultante.clear();
                    lineFavor.setVisible(false);
                    pontaLineFavor.setVisible(false);
                    posInicial();
                }
            }
        });
        ForcaContraria.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(ForcaContraria.isSelected()){
                    txtFcontra.setEditable(true);
                    txtdistancia.setEditable(true);
                    lineContra.setVisible(true);
                    pontaLineContra.setVisible(true);
                    posInicial();
                    
                }else{
                    txtFcontra.setEditable(false);
                    txtdistancia.setEditable(false);
                    txtFcontra.clear();
                    txtdistancia.clear();
                    txtFresultante.clear();
                    lineContra.setVisible(false);
                    pontaLineContra.setVisible(false);
                    posInicial();
                }
            }
        });
        ForcaAngulo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(ForcaAngulo.isSelected()){
                    txtFangulo.setEditable(true);
                    txtangulo.setEditable(true);
                    txtdistancia.setEditable(true);
                    lineAngulo.setVisible(true);
                    pontaLineAngulo.setVisible(true);
                    posInicial();
                    
                }else{
                    txtFangulo.setEditable(false);
                    txtangulo.setEditable(false);
                    txtdistancia.setEditable(false);
                    txtFangulo.clear();
                    txtangulo.clear();
                    txtdistancia.clear();
                    txtFresultante.clear();
                    lineAngulo.setVisible(false);
                    pontaLineAngulo.setVisible(false);
                    posInicial();
                }
            }
        });
        
        btnCalcular.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(ForcaFavor.isSelected() && !ForcaContraria.isSelected() && !ForcaAngulo.isSelected()){
                    if(!(txtFfavor.getText().equals("") || txtdistancia.getText().equals(""))){
                        calcular(1);
                    }else{
                        Trabalho.alerta();
                    }
                }
                else if(!ForcaFavor.isSelected() && ForcaContraria.isSelected() && !ForcaAngulo.isSelected()){
                    if(!(txtFcontra.getText().equals("") || txtdistancia.getText().equals(""))){
                        calcular(2);
                    }else{
                        Trabalho.alerta();
                    }
                }
                else if(!ForcaFavor.isSelected() && !ForcaContraria.isSelected() && ForcaAngulo.isSelected()){
                    if(!(txtFangulo.getText().equals("") || txtdistancia.getText().equals("") || txtangulo.getText().equals(""))){
                        calcular(3);
                    }else{
                        Trabalho.alerta();
                    }
                }
                else if(ForcaFavor.isSelected() && ForcaContraria.isSelected() && !ForcaAngulo.isSelected()){
                    if(!(txtFfavor.getText().equals("") || txtFcontra.getText().equals("") || txtdistancia.getText().equals(""))){
                        calcular(4);
                    }else{
                        Trabalho.alerta();
                    }
                }
                else if(!ForcaFavor.isSelected() && ForcaContraria.isSelected() && ForcaAngulo.isSelected()){
                    if(!(txtFcontra.getText().equals("") || txtFangulo.getText().equals("") || txtangulo.getText().equals("") || txtdistancia.getText().equals(""))){
                        calcular(5);
                    }else{
                        Trabalho.alerta();
                    }
                }
                else if(ForcaFavor.isSelected() && !ForcaContraria.isSelected() && ForcaAngulo.isSelected()){
                    if(!(txtFfavor.getText().equals("") || txtFangulo.getText().equals("") || txtangulo.getText().equals("") || txtdistancia.getText().equals(""))){
                        calcular(6);
                    }else{
                        Trabalho.alerta();
                    }
                }
                else if(ForcaFavor.isSelected() && ForcaContraria.isSelected() && ForcaAngulo.isSelected()){
                    if(!(txtFfavor.getText().equals("") || txtFcontra.getText().equals("") || txtFangulo.getText().equals("") || txtangulo.getText().equals("") || txtdistancia.getText().equals(""))){
                        calcular(7);
                    }else{
                        Trabalho.alerta();
                    }
                }
                else{
                   Alert alert = new Alert(Alert.AlertType.WARNING, "Erro inesperado!!!/n/nTente calcular novamente", ButtonType.OK);
                }
            }
        });
    } 
    //---------------------------MÉTODO CALCULO---------------------------------------------------------------------
    private double frA,frB,fresultante,ffavor,fcontra,fangulo,dist,angulo,resul;
    
    private void calcular(int num){
        switch(num){
            case 1:
                ffavor = Double.parseDouble(txtFfavor.getText());
                dist = Double.parseDouble(txtdistancia.getText());
                resul = Trabalho.decimalFormat(ffavor*dist);
                txtFresultante.setText(String.valueOf(Trabalho.decimalFormat(ffavor)));
                resultado.setText("Trabalho (W) = "+resul+" J");
                //play progressivo
                animacao("progressivo");
                break;
            
            case 2:
                fcontra = Double.parseDouble(txtFcontra.getText());
                dist = Double.parseDouble(txtdistancia.getText());
                resul = Trabalho.decimalFormat((-1)*fcontra*dist);
                txtFresultante.setText(String.valueOf(Trabalho.decimalFormat(fcontra)));
                resultado.setText("Trabalho (W) = "+resul+" J");
                //play retrogado
                animacao("retrogado");
                break;
                
            case 3:
                fangulo = Double.parseDouble(txtFangulo.getText());
                dist = Double.parseDouble(txtdistancia.getText());
                angulo = Math.toRadians(Double.parseDouble(txtangulo.getText()));
                resul = Trabalho.decimalFormat(fangulo*Math.cos(angulo)*dist);
                txtFresultante.setText(String.valueOf(Trabalho.decimalFormat(angulo)));
                resultado.setText("Trabalho (W) = "+resul+" J");
                if(resul==0){
                    animacao("parado");
                }else{
                    animacao("progressivo");
                }
                break;
                
            case 4:
                ffavor = Double.parseDouble(txtFfavor.getText());
                fcontra = Double.parseDouble(txtFcontra.getText());
                dist = Double.parseDouble(txtdistancia.getText());
                fresultante = ffavor-fcontra;
                txtFresultante.setText(String.valueOf(Trabalho.decimalFormat(fresultante)));
                if(fresultante>0){
                    resul = Trabalho.decimalFormat(fresultante*dist);
                    //play progressivo
                    animacao("progressivo");
                }else if(fresultante<0){
                    resul = Trabalho.decimalFormat(fresultante*dist);
                    //play retrogado
                    animacao("retrogado");
                }else{
                    resul=0;
                    //play parado
                    animacao("parado");
                }
                resultado.setText("Trabalho (W) = "+resul+" J");
                break;
                
            case 5:
                fcontra = Double.parseDouble(txtFcontra.getText());
                dist = Double.parseDouble(txtdistancia.getText());
                angulo = Math.toRadians(Double.parseDouble(txtangulo.getText()));
                fangulo = Double.parseDouble(txtFangulo.getText());
                //(fangulo*cos()-fcontra)*d
                fresultante = ((fangulo*Math.cos(angulo)) - fcontra);
                txtFresultante.setText(String.valueOf(Trabalho.decimalFormat(fresultante)));
                if(fresultante>0){
                    resul = Trabalho.decimalFormat(fresultante*dist);
                    //play progressivo
                    animacao("progressivo");
                }else if(fresultante<0){
                    resul = Trabalho.decimalFormat(fresultante*dist);
                    //play retrogado
                    animacao("retrogado");
                }else{
                    resul = 0;
                    //play parado
                    animacao("parado");
                }
                resultado.setText("Trabalho (W) = "+resul+" J");
                break;
                
            case 6:
                angulo = Math.toRadians(Double.parseDouble(txtangulo.getText()));
                fangulo = Double.parseDouble(txtFangulo.getText());
                ffavor = Double.parseDouble(txtFfavor.getText());
                dist = Double.parseDouble(txtdistancia.getText());
                //(ffavor + fangulo*cos())*d
                fresultante = ffavor + (fangulo*Math.cos(angulo));
                txtFresultante.setText(String.valueOf(Trabalho.decimalFormat(fresultante)));
                if(fresultante>0){
                    resul = Trabalho.decimalFormat(fresultante*dist);
                    //play progressivo
                    animacao("progressivo");
                }else if(fresultante<0){
                    resul = Trabalho.decimalFormat(fresultante*dist);
                    //play retrogado
                    animacao("retrogado");
                }else{
                    resul = 0;
                    //play parado
                    animacao("parado");
                }
                resultado.setText("Trabalho (W) = "+resul+" J");
                break;
                
            case 7:
                fangulo = Double.parseDouble(txtFangulo.getText());
                ffavor = Double.parseDouble(txtFfavor.getText());
                fcontra = Double.parseDouble(txtFcontra.getText());
                angulo = Math.toRadians(Double.parseDouble(txtangulo.getText()));
                dist = Double.parseDouble(txtdistancia.getText());
                //(ffavor+fangulo*cos()-fcontra)*d
                frA = ffavor+fangulo*Math.cos(angulo);
                frB = fcontra;
                fresultante = frA-frB;
                txtFresultante.setText(String.valueOf(Trabalho.decimalFormat(fresultante)));
                if(frA==frB){
                    resul = 0;
                    //play parado
                    animacao("parado");
                }else if(frA>frB){ //A>B  formula -> (A-B)*d= W
                    resul = Trabalho.decimalFormat((frA-frB)*dist);
                    //play progressivo
                    animacao("progressivo");
                }else{//              B>A   formula -> (-1)(B-A)*d=W
                    resul = Trabalho.decimalFormat((-1)*(frB-frA)*dist);
                    //play retrogado
                    animacao("retrogado");
                }
                resultado.setText("Trabalho (W) = "+resul+" J");
                break;
                
        }
        
    }
    
    private void animacao(String animacao){
        ParallelTransition pt,pt2,pt3,pt4;
        SequentialTransition st;
        FadeTransition ft,ft2,ft3,ft4,ft5,ft6,ft7;
        TranslateTransition tt,tt2,tt3,tt4,tt5,tt6,tt7;
        
        posInicial();
        
        switch(animacao){
            case "progressivo"://-------------------------------------------------------------------------------------
                ft = new FadeTransition(Duration.seconds(2), bloco);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft2 = new FadeTransition(Duration.seconds(2), lineFavor);
                ft2.setFromValue(1);
                ft2.setToValue(0);
                ft3 = new FadeTransition(Duration.seconds(2),pontaLineFavor);
                ft3.setFromValue(1);
                ft3.setToValue(0);
                ft4 = new FadeTransition(Duration.seconds(2),lineContra);
                ft4.setFromValue(1);
                ft4.setToValue(0);
                ft5 = new FadeTransition(Duration.seconds(2),pontaLineContra);
                ft5.setFromValue(1);
                ft5.setToValue(0);
                ft6 = new FadeTransition(Duration.seconds(2),lineAngulo);
                ft6.setFromValue(1);
                ft6.setToValue(0);
                ft7 = new FadeTransition(Duration.seconds(2),pontaLineAngulo);
                ft7.setFromValue(1);
                ft7.setToValue(0);
                
                pt = new ParallelTransition(ft,ft2,ft3,ft4,ft5,ft6,ft7); //fade sumindo
             
                ft = new FadeTransition(Duration.seconds(0.5), bloco);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft2 = new FadeTransition(Duration.seconds(0.5), lineFavor);
                ft2.setFromValue(0);
                ft2.setToValue(1);
                ft3 = new FadeTransition(Duration.seconds(0.5),pontaLineFavor);
                ft3.setFromValue(0);
                ft3.setToValue(1);
                ft4 = new FadeTransition(Duration.seconds(0.5),lineContra);
                ft4.setFromValue(0);
                ft4.setToValue(1);
                ft5 = new FadeTransition(Duration.seconds(0.5),pontaLineContra);
                ft5.setFromValue(0);
                ft5.setToValue(1);
                ft6 = new FadeTransition(Duration.seconds(0.5),lineAngulo);
                ft6.setFromValue(0);
                ft6.setToValue(1);
                ft7 = new FadeTransition(Duration.seconds(0.5),pontaLineAngulo);
                ft7.setFromValue(0);
                ft7.setToValue(1);
                
                pt2 = new ParallelTransition(ft,ft2,ft3,ft4,ft5,ft6,ft7);// fade aparecer
                
                tt = new TranslateTransition(Duration.seconds(5), bloco);
                tt.setFromX(-228);
                tt.setToX(228);
                tt2 = new TranslateTransition(Duration.seconds(5), lineFavor);
                tt2.setFromX(-228);
                tt2.setToX(228);
                tt3 = new TranslateTransition(Duration.seconds(5), pontaLineFavor);
                tt3.setFromX(-228);
                tt3.setToX(228);
                tt4 = new TranslateTransition(Duration.seconds(5), lineContra);
                tt4.setFromX(-228);
                tt4.setToX(228);
                tt5 = new TranslateTransition(Duration.seconds(5), pontaLineContra);
                tt5.setFromX(-228);
                tt5.setToX(228);
                tt6 = new TranslateTransition(Duration.seconds(5), lineAngulo);
                tt6.setFromX(-228);
                tt6.setToX(228);
                tt7 = new TranslateTransition(Duration.seconds(5), pontaLineAngulo);
                tt7.setFromX(-228);
                tt7.setToX(228);
                
                pt3 = new ParallelTransition(tt,tt2,tt3,tt4,tt5,tt6,tt7); //mover para destino
                pt4 = new ParallelTransition(pt3,pt2);
                st = new SequentialTransition(pt,pt4);
                st.play();
                break;
            
            case "retrogado"://---------------------------------------------------------------------------------------
                ft = new FadeTransition(Duration.seconds(2), bloco);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft2 = new FadeTransition(Duration.seconds(2), lineFavor);
                ft2.setFromValue(1);
                ft2.setToValue(0);
                ft3 = new FadeTransition(Duration.seconds(2),pontaLineFavor);
                ft3.setFromValue(1);
                ft3.setToValue(0);
                ft4 = new FadeTransition(Duration.seconds(2),lineContra);
                ft4.setFromValue(1);
                ft4.setToValue(0);
                ft5 = new FadeTransition(Duration.seconds(2),pontaLineContra);
                ft5.setFromValue(1);
                ft5.setToValue(0);
                ft6 = new FadeTransition(Duration.seconds(2),lineAngulo);
                ft6.setFromValue(1);
                ft6.setToValue(0);
                ft7 = new FadeTransition(Duration.seconds(2),pontaLineAngulo);
                ft7.setFromValue(1);
                ft7.setToValue(0);
                
                pt = new ParallelTransition(ft,ft2,ft3,ft4,ft5,ft6,ft7); //fade sumindo
             
                ft = new FadeTransition(Duration.seconds(0.5), bloco);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft2 = new FadeTransition(Duration.seconds(0.5), lineFavor);
                ft2.setFromValue(0);
                ft2.setToValue(1);
                ft3 = new FadeTransition(Duration.seconds(0.5),pontaLineFavor);
                ft3.setFromValue(0);
                ft3.setToValue(1);
                ft4 = new FadeTransition(Duration.seconds(0.5),lineContra);
                ft4.setFromValue(0);
                ft4.setToValue(1);
                ft5 = new FadeTransition(Duration.seconds(0.5),pontaLineContra);
                ft5.setFromValue(0);
                ft5.setToValue(1);
                ft6 = new FadeTransition(Duration.seconds(0.5),lineAngulo);
                ft6.setFromValue(0);
                ft6.setToValue(1);
                ft7 = new FadeTransition(Duration.seconds(0.5),pontaLineAngulo);
                ft7.setFromValue(0);
                ft7.setToValue(1);
                
                pt2 = new ParallelTransition(ft,ft2,ft3,ft4,ft5,ft6,ft7);// fade aparecer
                
                tt = new TranslateTransition(Duration.seconds(5), bloco);
                tt.setFromX(228);
                tt.setToX(-228);
                tt2 = new TranslateTransition(Duration.seconds(5), lineFavor);
                tt2.setFromX(228);
                tt2.setToX(-228);
                tt3 = new TranslateTransition(Duration.seconds(5), pontaLineFavor);
                tt3.setFromX(228);
                tt3.setToX(-228);
                tt4 = new TranslateTransition(Duration.seconds(5), lineContra);
                tt4.setFromX(228);
                tt4.setToX(-228);
                tt5 = new TranslateTransition(Duration.seconds(5), pontaLineContra);
                tt5.setFromX(228);
                tt5.setToX(-228);
                tt6 = new TranslateTransition(Duration.seconds(5), lineAngulo);
                tt6.setFromX(228);
                tt6.setToX(-228);
                tt7 = new TranslateTransition(Duration.seconds(5), pontaLineAngulo);
                tt7.setFromX(228);
                tt7.setToX(-228);
                
                pt3 = new ParallelTransition(tt,tt2,tt3,tt4,tt5,tt6,tt7); //mover para destino
                pt4 = new ParallelTransition(pt3,pt2);
                st = new SequentialTransition(pt,pt4);
                st.play();
                break;
                
            case "parado":
                ft = new FadeTransition(Duration.seconds(2), bloco);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft2 = new FadeTransition(Duration.seconds(2), lineFavor);
                ft2.setFromValue(1);
                ft2.setToValue(0);
                ft3 = new FadeTransition(Duration.seconds(2),pontaLineFavor);
                ft3.setFromValue(1);
                ft3.setToValue(0);
                ft4 = new FadeTransition(Duration.seconds(2),lineContra);
                ft4.setFromValue(1);
                ft4.setToValue(0);
                ft5 = new FadeTransition(Duration.seconds(2),pontaLineContra);
                ft5.setFromValue(1);
                ft5.setToValue(0);
                ft6 = new FadeTransition(Duration.seconds(2),lineAngulo);
                ft6.setFromValue(1);
                ft6.setToValue(0);
                ft7 = new FadeTransition(Duration.seconds(2),pontaLineAngulo);
                ft7.setFromValue(1);
                ft7.setToValue(0);
                
                pt = new ParallelTransition(ft,ft2,ft3,ft4,ft5,ft6,ft7); //fade sumindo
             
                ft = new FadeTransition(Duration.seconds(0.5), bloco);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft2 = new FadeTransition(Duration.seconds(0.5), lineFavor);
                ft2.setFromValue(0);
                ft2.setToValue(1);
                ft3 = new FadeTransition(Duration.seconds(0.5),pontaLineFavor);
                ft3.setFromValue(0);
                ft3.setToValue(1);
                ft4 = new FadeTransition(Duration.seconds(0.5),lineContra);
                ft4.setFromValue(0);
                ft4.setToValue(1);
                ft5 = new FadeTransition(Duration.seconds(0.5),pontaLineContra);
                ft5.setFromValue(0);
                ft5.setToValue(1);
                ft6 = new FadeTransition(Duration.seconds(0.5),lineAngulo);
                ft6.setFromValue(0);
                ft6.setToValue(1);
                ft7 = new FadeTransition(Duration.seconds(0.5),pontaLineAngulo);
                ft7.setFromValue(0);
                ft7.setToValue(1);
                
                pt2 = new ParallelTransition(ft,ft2,ft3,ft4,ft5,ft6,ft7);// fade aparecer
                
                st = new SequentialTransition(pt,pt2);
                st.play();
                break;
        }
    }
    
    private void posInicial(){
        bloco.setTranslateX(0);
        lineFavor.setTranslateX(0);
        pontaLineFavor.setTranslateX(0);
        lineContra.setTranslateX(0);
        pontaLineContra.setTranslateX(0);
        lineAngulo.setTranslateX(0);
        pontaLineAngulo.setTranslateX(0);
    }
}
