package me.potaytoprograms.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.potaytoprograms.game.states.GameStateManager;
import me.potaytoprograms.game.states.MenuState;
import me.potaytoprograms.game.states.PlayState;

public class FlappyDemo extends ApplicationAdapter
{
	public static final int WIDTH = 480;
	public static final int HIGHT = 800;

	public static final String TITLE = "Flappy Bird";
	private GameStateManager gsm;
	private Music bgMusic;

	private SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		img = new Texture("badlogic.jpg");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		bgMusic.setVolume(0.1f);
		bgMusic.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		bgMusic.dispose();
		gsm.dispose();
	}
}
