import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za odczytywanie uzycia klawiatury.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Spawn spawn;

    /**
     * Konstruktor klasy KeyInput.
     * @param handler Schowek obiektow gry.
     * @param spawn Obiekt tworzacy nowe obiekty gry.
     */
    public KeyInput(Handler handler, Spawn spawn) {

        this.handler = handler;
        this.spawn = spawn;
    }

    /**
     * Metoda wczytujaca wcisniecie klawisza.
     * @param e Event klawisza.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int key = e.getKeyCode();

        if (Hud.state != 2)
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Player) {
                    Player player = (Player) tempObject;
                    if (key == KeyEvent.VK_LEFT) player.pressL = true;
                    if (key == KeyEvent.VK_RIGHT) player.pressR = true;
                }

                if (tempObject.getId() == ID.Ball) {
                    Ball ball = (Ball) tempObject;
                    if (ball.getVelX() == 0 && ball.getVelY() == 0)
                        if (key == KeyEvent.VK_SPACE) {
                            if (Hud.state == 0) {
                                Hud.state = 1;
                                ball.setVelX(Game.getSpeedBallX());
                                ball.setVelY(Game.getSpeedBallY());
                            }
                        }
                }

            }
        if (key == KeyEvent.VK_SPACE) {
            if (Hud.state == 2) {
                handler.removeAllObjects();
                spawn.tick();
            }
        }
        if (key == KeyEvent.VK_SPACE) {
            if (Hud.state == 3) {
                handler.removeAllObjects();
                spawn.tick();
            }
        }
    }

    /**
     * Metoda wczytujaca puszczenie klawisza.
     * @param e Event klawisza.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        int key = e.getKeyCode();

        //zmieniono pętlę na foreach
        for (GameObject tempObject: handler.object) {

            if (tempObject.getId() == ID.Player) {
                Player player = (Player) tempObject;
                if (key == KeyEvent.VK_LEFT) player.pressL = false;//tempObject.setVelX(0);
                if (key == KeyEvent.VK_RIGHT) player.pressR = false;//tempObject.setVelX(0);
            }
        }
    }
}
