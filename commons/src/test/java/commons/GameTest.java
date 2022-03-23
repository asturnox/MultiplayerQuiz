package commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  public void emptyConstructorTest() {
    Game gameEmpty = new Game();
    assertNotNull(gameEmpty);
  }


  @Test
  public void gameIdConstructorTest() {
    String id = "ID";
    Game gameWithId = new Game(id);
    assertNotNull(gameWithId);
  }

  @Test
  public void gameConstructorFullTest1() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    List collection = new LinkedList();
    collection.add(c1);
    collection.add(c2);
    collection.add(c3);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question q3 = new MultipleChoiceQuestion();
    List collectionQuestions = new LinkedList();
    collectionQuestions.add(q1);
    collectionQuestions.add(q2);
    collectionQuestions.add(q3);
    String id = "ID";
    Game game = new Game(id, collection, collectionQuestions);
    assertNotNull(game);
  }

  @Test
  public void gameConstructorFullTest2() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    List collection = new LinkedList();
    collection.add(c1);
    collection.add(c2);
    collection.add(c3);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question q3 = new MultipleChoiceQuestion();
    Question[] questions = new Question[3];
    questions[0] = q1;
    questions[1] = q2;
    questions[2] = q3;
    String id = "ID";
    Game game = new Game(id, collection, questions);
    assertNotNull(game);
  }

  @Test
  public void gameConstructorFullTest3() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    collection.put(c2, 0);
    collection.put(c3, 0);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question q3 = new MultipleChoiceQuestion();
    List collectionQuestions = new LinkedList();
    collectionQuestions.add(q1);
    collectionQuestions.add(q2);
    collectionQuestions.add(q3);
    String id = "ID";
    Game game = new Game(id, collection, collectionQuestions);
    assertNotNull(game);
  }

  @Test
  public void gameConstructorFullTest4() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    collection.put(c2, 0);
    collection.put(c3, 0);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question q3 = new MultipleChoiceQuestion();
    Question[] questions = new Question[3];
    questions[0] = q1;
    questions[1] = q2;
    questions[2] = q3;
    String id = "ID";
    Game game = new Game(id, collection, questions);
    assertNotNull(game);
  }

  @Test
  public void addPlayerTest() {
    String id = "GameID";
    Client c1 = new Client("ID1", "US1", true);
    Map<Client, Integer> map = new HashMap<>();
    Question[] questions = new Question[3];
    Game game = new Game(id, map, questions);
    game.addPlayer(c1);
    assertTrue(game.players.size() > 0);
  }

  @Test
  public void changeScoreTest() {
    String id = "GameID";
    Client c1 = new Client("ID1", "US1", true);
    Map<Client, Integer> map = new HashMap<>();
    Question[] questions = new Question[3];
    Game game = new Game(id, map, questions);
    game.addPlayer(c1);
    game.changeScore(c1, 100);
    assertTrue(game.players.get(c1) == 100);
  }

  @Test
  public void increaseScoreTest() {
    String id = "GameID";
    Client c1 = new Client("ID1", "US1", true);
    Map<Client, Integer> map = new HashMap<>();
    Question[] questions = new Question[3];
    Game game = new Game(id, map, questions);
    game.addPlayer(c1);
    game.increaseScore(c1, 50);
    assertTrue(game.players.get(c1) == 50);
  }

  @Test
  public void getByIdTest1() {
    String id = "GameID";
    Client c1 = new Client("ID1", "US1", true);
    Map<Client, Integer> map = new HashMap<>();
    Question[] questions = new Question[3];
    Game game = new Game(id, map, questions);
    game.addPlayer(c1);
    assertTrue(c1.equals(game.getPlayerById("ID1")));
  }

  @Test
  public void getByIDNull() {
    String id = "GameID";
    Map<Client, Integer> map = new HashMap<>();
    Question[] questions = new Question[3];
    Game game = new Game(id, map, questions);
    assertNull(game.getPlayerById("ID1"));
  }

  @Test
  public void containsPlayerTest() {
    String id = "GameID";
    Client c1 = new Client("ID1", "US1", true);
    Map<Client, Integer> map = new HashMap<>();
    Question[] questions = new Question[3];
    Game game = new Game(id, map, questions);
    game.addPlayer(c1);
    assertTrue(game.containsPlayer("ID1"));
  }

  @Test
  public void nextQuestionTest() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    collection.put(c2, 0);
    collection.put(c3, 0);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question q3 = new MultipleChoiceQuestion();
    Question[] questions = new Question[3];
    questions[0] = q1;
    questions[1] = q2;
    questions[2] = q3;
    String id = "ID";
    Game game = new Game(id, collection, questions);
    assertEquals(q1, game.next());
  }

  @Test
  public void nextQuestionEndTest() {
    String id = "ID";
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    Question q1 = new EstimateQuestion();
    Question[] questions = new Question[20];
    for (int i = 0; i < 20; i++) {
      questions[i] = q1;
    }
    Game game = new Game(id, collection, questions);
    Question finalQuestion;
    for (int i = 0; i < 21; i++) {
      finalQuestion = game.next();
    }
    assertTrue(game.next().getType().equals(Question.Type.ENDSCREEN));
  }

  @Test
  public void currentTest() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question[] questions = new Question[20];
    questions[0] = q1;
    for (int i = 1; i < 20; i++) {
      questions[i] = q2;
    }
    String id = "ID";
    Game game = new Game(id, collection, questions);
    assertEquals(q1, game.current());
  }

  @Test
  public void toStringTest() {
    Game game = new Game();
    String result = "Game{id='null', players={}, questionCounter=0, showedLeaderboard=false, questions=null}";
    assertEquals(result, game.toString());
  }

  @Test
  public void equalsSameTest() {
    Game game = new Game();
    assertEquals(game, game);
  }

  @Test
  public void equalsSameButDifferent() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    collection.put(c2, 0);
    collection.put(c3, 0);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question q3 = new MultipleChoiceQuestion();
    Question[] questions = new Question[3];
    questions[0] = q1;
    questions[1] = q2;
    questions[2] = q3;
    String id = "ID";
    Game game = new Game(id, collection, questions);
    Game gameNew = new Game(id, collection, questions);
    assertEquals(game, gameNew);
  }

  @Test
  public void equalsDifferent() {
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    collection.put(c2, 0);
    collection.put(c3, 0);
    Question q1 = new EstimateQuestion();
    Question q2 = new HowMuchQuestion();
    Question q3 = new MultipleChoiceQuestion();
    Question[] questions = new Question[3];
    questions[0] = q1;
    questions[1] = q2;
    questions[2] = q3;
    String id = "ID";
    Map<Client, Integer> collection1 = new HashMap<>();
    Game game = new Game(id, collection, questions);
    Game gameNew = new Game(id, collection1, questions);
    assertFalse(game.equals(gameNew));
  }

  @Test
  public void hashTest() {
    String id = "ID";
    Client c1 = new Client("ID1", "US1", true);
    Client c2 = new Client("ID2", "US2", true);
    Client c3 = new Client("ID3", "US3", true);
    Map<Client, Integer> collection = new HashMap<>();
    collection.put(c1, 0);
    Question q1 = new EstimateQuestion();
    Question[] questions = new Question[20];
    for (int i = 0; i < 20; i++) {
      questions[i] = q1;
    }
    Game game = new Game(id, collection, questions);
    assertTrue(game.hashCode() == Math.floor(game.hashCode()));
  }


}