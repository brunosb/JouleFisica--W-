/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.controles;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import trabalho.Navegacao;



/**
 * FXML Controller class
 *
 * @author BRUNO
 */
public class PrincipalController implements Initializable {
        
     @FXML
    private Button hook;

    @FXML
    private Label nomeAluno;

    @FXML
    private Button peso;

    @FXML
    private Button forca;

    @FXML
    private Label anoAluno;

    @FXML
    private Button graficos;

    @FXML
    private VBox BotoesMenu;

    @FXML
    private Button estudo;

    @FXML
    private AnchorPane conteudoTelas;
    
    @FXML
    private ImageView menuSelecao;
    
    public static AnchorPane CONTEUDO;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CONTEUDO = conteudoTelas;
        Navegacao navega = new Navegacao();
        
        nomeAluno.setText("Nome: "+FXMLLoginController.aluno);
        anoAluno.setText("Ano: "+FXMLLoginController.ano);
        
        menuSelecao.setVisible(false);
        
        /*------------ INTERAÇÃO DOS BOTÕES -------------*/
        
        forca.setOnAction(event -> {navega.navegar("forca");
                                    menuSelecao.setVisible(true);
                                    menuSelecao.setLayoutY(33);});
        peso.setOnAction(event -> {navega.navegar("peso");
                                    menuSelecao.setVisible(true);
                                    menuSelecao.setLayoutY(117);});
        hook.setOnAction(event -> {navega.navegar("hook");
                                    menuSelecao.setVisible(true);
                                    menuSelecao.setLayoutY(203);});
        graficos.setOnAction(event -> {navega.navegar("grafico");
                                    menuSelecao.setVisible(true);
                                    menuSelecao.setLayoutY(287);});
        estudo.setOnAction(event -> {navega.navegar("estudo");
                                    menuSelecao.setVisible(true);
                                    menuSelecao.setLayoutY(372);});
        
        
    }    
    
}
