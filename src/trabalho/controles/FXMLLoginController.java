/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import trabalho.Trabalho;


/**
 *
 * @author BRUNO
 */
public class FXMLLoginController implements Initializable {
    
    @FXML
    private Line line_tracejada_3;

    @FXML
    private TextField txtAluno;

    @FXML
    private Line line_tracejada_2;

    @FXML
    private Line line_tracejada_1;

    @FXML
    private TextField txtSerie;
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private AnchorPane fundoLogin;
    
    @FXML
    private Ellipse fundoElipse;
    
    public static String aluno,ano;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        line_tracejada_1.getStrokeDashArray().addAll(3d);
        line_tracejada_2.getStrokeDashArray().addAll(3d);
        line_tracejada_3.getStrokeDashArray().addAll(3d);
        
        
        txtSerie.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                Trabalho.numericField(txtSerie);
            }
        });
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                aluno = (String)txtAluno.getText().toUpperCase();
                ano = (String) txtSerie.getText();
                Parent root;
                try {
                    if(aluno.equalsIgnoreCase("") || ano.equalsIgnoreCase("")){
                        Trabalho.alerta();
                    }else{
                        //Transição da tela
                        
                        
                        root = FXMLLoader.load(PrincipalController.class.getResource("/trabalho/telas/Principal.fxml"));
                        Trabalho.SCENE.setRoot(root);
                        Trabalho.STAGE.close();
                        Trabalho.STAGE = new Stage();
                        
                        /*TELA CHEIA*/ 
                        Trabalho.STAGE.centerOnScreen();
                        Trabalho.STAGE.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
                        Trabalho.STAGE.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
                        
                        Trabalho.STAGE.setScene(Trabalho.SCENE);
                        Trabalho.STAGE.setTitle("Tela Principal");
                        Trabalho.STAGE.show();
                    }
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        //Animação
        
    }
    
    
}
