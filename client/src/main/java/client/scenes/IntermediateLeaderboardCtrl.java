package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class IntermediateLeaderboardCtrl implements Initializable {
  private final ServerUtils server;
  private final MainCtrl mainCtrl;

  @FXML
  private VBox leaderboardDisplay;
  @FXML
  private Button backButton;
  @FXML
  private Button helpButton;
  @FXML
  private Circle circle;
  @FXML
  private Label emoji1;
  @FXML
  private Label emoji2;
  @FXML
  private Label emoji3;
  @FXML
  private Label emoji4;
  @FXML
  private Label emoji5;
  @FXML
  private GridPane emojiGrid;
  @FXML
  private Button emojiButton;
  @FXML
  private StackPane pane;

  private Label[] emojis;

  @Inject
  public IntermediateLeaderboardCtrl(ServerUtils server, MainCtrl mainCtrl) {
    this.server = server;
    this.mainCtrl = mainCtrl;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    emojis = new Label[] {emoji1, emoji2, emoji3, emoji4, emoji5};
    emojiButton.setOnMouseEntered(event -> {
      pane.setVisible(true);
      circle.setVisible(true);
      emojiGrid.setVisible(true);
    });
    pane.setOnMouseExited(event -> {
      pane.setVisible(false);
      circle.setVisible(false);
      emojiGrid.setVisible(false);
    });
  }

  public void display() {
    pane.setVisible(false);
    circle.setVisible(false);
    emojiGrid.setVisible(false);
  }

  public void refresh() {
    var leaderboard = server.getMultiLeaderboard(mainCtrl.serverIp, mainCtrl.gameId);

    leaderboardDisplay.getChildren().removeAll(leaderboardDisplay.getChildren());
    int[] i = {1};
    leaderboard.forEach(score -> {
      Label l = new Label(i[0] + ". " + score.getPlayer() + " " + score.getPoints());
      l.getStyleClass().add("list-item");
      l.getStyleClass().add("border-bottom");
      if (i[0]++ == 1) {
        l.getStyleClass().add("list-item-top");
      }
      leaderboardDisplay.getChildren().add(l);
    });
  }

  @FXML
  private void back(ActionEvent actionEvent) {
    mainCtrl.showSplash();
  }

  @FXML
  private void help(ActionEvent actionEvent) {
    mainCtrl.openHelp();
  }


}
