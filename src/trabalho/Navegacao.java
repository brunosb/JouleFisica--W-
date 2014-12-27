/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import trabalho.controles.PrincipalController;

/**
 *
 * @author BRUNO
 */
public class Navegacao {
    
    public void navegar(String tela){
        Parent root2;
        try{
            switch(tela){
                case "forca":
                    root2 = FXMLLoader.load(getClass().getResource("/trabalho/telas/Forca.fxml"));
                    PrincipalController.CONTEUDO.getChildren().clear();
                    PrincipalController.CONTEUDO.getChildren().add(root2);
                    break;

                case "peso":
                    root2 = FXMLLoader.load(getClass().getResource("/trabalho/telas/Peso.fxml"));
                    PrincipalController.CONTEUDO.getChildren().clear();
                    PrincipalController.CONTEUDO.getChildren().add(root2);
                    break;

                case "hook":
                    root2 = FXMLLoader.load(getClass().getResource("/trabalho/telas/Hook.fxml"));
                    PrincipalController.CONTEUDO.getChildren().clear();
                    PrincipalController.CONTEUDO.getChildren().add(root2);
                    break;

                case "grafico":
                    root2 = FXMLLoader.load(getClass().getResource("/trabalho/telas/Grafico.fxml"));
                    PrincipalController.CONTEUDO.getChildren().clear();
                    PrincipalController.CONTEUDO.getChildren().add(root2);
                    break;

                case "estudo":
                    root2 = FXMLLoader.load(getClass().getResource("/trabalho/telas/Revisao.fxml"));
                    PrincipalController.CONTEUDO.getChildren().clear();
                    PrincipalController.CONTEUDO.getChildren().add(root2);
                    break;
            }
        }catch(IOException ex){}
    }
    
    
}
