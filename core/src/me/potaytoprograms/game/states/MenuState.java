package me.potaytoprograms.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.potaytoprograms.game.FlappyDemo;

public class MenuState extends state
{
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH, FlappyDemo.HIGHT);
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0, FlappyDemo.WIDTH, FlappyDemo.HIGHT);
        sb.draw(playButton, (FlappyDemo.WIDTH / 2) - (playButton.getWidth() / 2), (FlappyDemo.HIGHT /2) - (playButton.getHeight() / 2));
        sb.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playButton.dispose();
        System.out.println("Menu State Disposed");
    }
}
