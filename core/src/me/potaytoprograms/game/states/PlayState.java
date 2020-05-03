package me.potaytoprograms.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.potaytoprograms.game.FlappyDemo;

public class PlayState extends state
{
    private Texture bird;
    private Texture background;
    private float birdY;
    private float birdX;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        bird = new Texture("bird.png");
        background = new Texture("bg.png");
        birdY = (FlappyDemo.HIGHT / 2) - (bird.getHeight() /2);
        birdX = (FlappyDemo.WIDTH / 2) - (bird.getWidth() / 2);
    }

    @Override
    protected void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            birdY += 50;
        }
    }

    @Override
    public void update(float dt)
    {
        birdY -= 2.5;
        handleInput();
        if(birdY <= 0)
        {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HIGHT);
        sb.draw(bird, birdX, birdY);
        sb.end();
    }

    @Override
    public void dispose()
    {
        bird.dispose();
        background.dispose();
    }
}
