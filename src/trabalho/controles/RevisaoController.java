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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.PaginationBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author BRUNO
 */
public class RevisaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane paneRevisao;
    
    private Pagination pages;
    private Image[] imagens = new Image[4];
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (int i = 0; i < 4; i++) {
            imagens[i] = new Image("img/Revisao" + (i + 1) + ".jpg");
        }
        
        pages = PaginationBuilder.create().pageCount(4).pageFactory(new Callback<Integer, Node>() {           
            @Override public Node call(Integer pageIndex) {
                return criarPagina(pageIndex);
            }
        }).build();
        
        pages.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        
        paneRevisao.getChildren().add(pages);
    }
    
    public VBox criarPagina(int index){
        VBox box = new VBox();
        box.setStyle("-fx-padding: 30;");
        ImageView iv = new ImageView(imagens[index]);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(iv);
        return box;
    }
    
}
