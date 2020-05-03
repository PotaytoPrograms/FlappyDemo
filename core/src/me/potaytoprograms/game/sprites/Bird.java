package me.potaytoprograms.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import me.potaytoprograms.game.states.GameStateManager;
import me.potaytoprograms.game.states.MenuState;

public class Bird
{
    private static final int GRAVITY = -15;
    private static final int MOVEMENT_SPEED = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Texture birdSpriteSheet;
    private Rectangle bounds;
    private GameStateManager gsm;
    private Animation birdAnim;

    public Bird(int x, int y)
    {
        gsm = new GameStateManager();
        position = new Vector3(x, y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        birdSpriteSheet = new Texture("birdanimation.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
        birdAnim = new Animation(birdSpriteSheet, 3, 1);
    }

    public void update(float dt)
    {
        if(position.y != 50)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT_SPEED * dt, velocity.y, 0);

        bounds.setPosition(position.x, position.y);
        birdAnim.update(dt);
        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public void jump()
    {
        velocity.y = 250;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose()
    {
        bird.dispose();
    }

    public Animation getBirdAnim() {
        return birdAnim;
    }
}
