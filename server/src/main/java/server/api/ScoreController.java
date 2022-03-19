package server.api;

import commons.Activity;
import commons.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.database.ScoreRepository;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

  private final ScoreRepository repo;

  @Autowired
  public ScoreController(ScoreRepository repo) {
    this.repo = repo;
  }

  /**
   * Get a list of all the scores in the database
   *
   * @return list of all scores
   */
  @GetMapping("/list")
  public Iterable<Score> getAll() {
    return repo.findAll();
  }

  /**
   * Get a list of all scores in descending order aka the leaderboard
   *
   * @return leaderboard
   */
  @GetMapping("/leaderboard")
  public Iterable<Score> getLeaderboard() {
    return repo.getLeaderboard();
  }

  // TODO: implement actual multiplayer leaderboard by game session id
  @GetMapping("/multiLeaderboard/{gameId}")
  public Iterable<Score> getMultiLeaderboard(@PathVariable("gameId") String gameId) {
    return getLeaderboard();
  }

  /**
   * Adds a score to the database
   *
   * @param score the score to be added in JSON format
   * @return the score that was added
   */
  @PostMapping("/add")
  public ResponseEntity<Score> addScore(@RequestBody Score score) {
    return ResponseEntity.ok(repo.save(score));
  }
}
