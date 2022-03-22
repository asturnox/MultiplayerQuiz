package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WaitingRoomCtrl implements Initializable {
  private final ServerUtils server;
  private final MainCtrl mainCtrl;

  @FXML
  private VBox playerListDisplay;
  @FXML
  private Button backButton;
  @FXML
  private Button helpButton;
  @FXML
  private Button startButton;
  @FXML
  private Text playerCounterField;

  private static ScheduledExecutorService EXECGameStarted = Executors.newSingleThreadScheduledExecutor();

  @Inject
  public WaitingRoomCtrl(ServerUtils server, MainCtrl mainCtrl) {
    this.server = server;
    this.mainCtrl = mainCtrl;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  @FXML
  private void back(ActionEvent actionEvent) {
    stop();
    mainCtrl.waitingForGame = false;
    mainCtrl.showSplash();
  }

  @FXML
  private void help(ActionEvent actionEvent) {
    mainCtrl.openHelp();
  }

  @FXML
  private void play(ActionEvent actionEvent) {
    mainCtrl.waitingForGame = false;
    mainCtrl.start();
    stop();
  }

  /**
   * Displays a list of players in the lobby.
   */
  public void refresh() {
    // only include players that are marked as 'waitingForGame'
    var players = server.getPlayers(mainCtrl.serverIp).stream().filter(c -> c.waitingForGame)
      .collect(Collectors.toList());

    startButton.setDisable(players.size() < 2);
    playerCounterField.setText(String.valueOf(players.size()));

    // remove all players and re-add them
    playerListDisplay.getChildren().removeAll(playerListDisplay.getChildren());
    int[] i = {0};
    players.forEach(player -> {
      Label l = new Label(
        player.username.equals(server.getClient(mainCtrl.serverIp, mainCtrl.clientId).username)
          ? "You (" + player.username + ")"
          : player.username
      );
      l.getStyleClass().add("list-item");
      l.getStyleClass().add("border-bottom");
      if (i[0]++ == 0) {
        l.getStyleClass().add("list-item-top");
      }
      playerListDisplay.getChildren().add(l);
    });
  }

  /**
   * Checks every second if a game containing the player has started
   */
  public void checkGameActive() {
    try {
      EXECGameStarted = Executors.newSingleThreadScheduledExecutor();
      EXECGameStarted.scheduleAtFixedRate(() -> {
        String gameId = server.isGameActive(mainCtrl.serverIp, mainCtrl.clientId);
        if (!gameId.equals("")) {
          mainCtrl.gameId = gameId;
          stop();
          try {
            mainCtrl.play();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }, 0, 1, TimeUnit.SECONDS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    EXECGameStarted.shutdownNow();
    server.stopUpdates();
  }

  /**
   * Call long polling method to update the waiting room automatically
   */
  public void listenForNewPlayers() {
    server.registerForPlayerUpdates(mainCtrl.serverIp, np -> Platform.runLater(this::refresh));
  }
}
