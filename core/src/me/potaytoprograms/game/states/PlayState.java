package me.potaytoprograms.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import me.potaytoprograms.game.FlappyDemo;
import me.potaytoprograms.game.sprites.Bird;
import me.potaytoprograms.game.sprites.Tube;

public class PlayState extends state
{
    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 groundOne, groundTwo;
    private Array<Tube> tubes;

    private static final int TUBE_SPACING = 125;
    private  static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        bird = new Bird(50, 200);
        tubes = new Array<Tube>();
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundOne = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundTwo = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HIGHT/2);

        for(int i = 1; i <= TUBE_COUNT; i++)
        {
           tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            bird.jump();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(int i = 0; i < tubes.size; i++)
        {
            if(cam.position.x - cam.viewportWidth/2 - Tube.TUBE_WIDTH > tubes.get(i).getPosTopTube().x)
            {
                tubes.get(i).reposition(tubes.get(i).getPosTopTube().x + ((tubes.get(i).TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(tubes.get(i).collides(bird.getBounds()))
                gsm.set(new MenuState(gsm));
        }
        if(bird.getPosition().y <= 50)
            gsm.set(new MenuState(gsm));
        updateGround();
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - cam.viewportWidth / 2, 0);
        for(Tube tube : tubes)
        {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundOne.x, groundOne.y);
        sb.draw(ground, groundTwo.x, groundTwo.y);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose()
    {
        bird.dispose();
        background.dispose();
        ground.dispose();
        for(Tube tube : tubes)
        {
            tube.dispose();
        }
        System.out.println("Play state Disposed");
    }

    private void updateGround()
    {
        if(groundOne.x < cam.position.x - ground.getWidth() - cam.viewportWidth/2)
            groundOne.add(ground.getWidth() * 2, 0);
        if(groundTwo.x <= cam.position.x - ground.getWidth() - cam.viewportWidth/2)
            groundTwo.add(ground.getWidth() * 2, 0);
    }
}
