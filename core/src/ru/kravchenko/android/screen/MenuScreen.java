package ru.kravchenko.android.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Base2DScreen;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.sprite.Background;
import ru.kravchenko.android.sprite.ExitButton;
import ru.kravchenko.android.sprite.Star;
import ru.kravchenko.android.sprite.StartButton;

public class MenuScreen extends Base2DScreen {

    private static final int STAR_COUNT = 256;

    private Texture imageBackgraund;

    private TextureAtlas textureAtlas;

    private Background background;

    private Star[] star;

    private StartButton startButton;

    private ExitButton exitButton;

    private Music music;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        textureAtlas = new TextureAtlas("textures/menuAtlas.tpack");
        imageBackgraund = new Texture("textures/sky.jpg");
        background = new Background(new TextureRegion(imageBackgraund));
        star = new Star[STAR_COUNT];
        startButton = new StartButton(textureAtlas, game);
        exitButton = new ExitButton(textureAtlas);
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/music_fon.mp3"));
        music.play();
        music.setLooping(true);
        for (int i = 0; i < star.length; i++) { star[i] = new Star(textureAtlas); }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        startButton.update(delta);
        exitButton.update(delta);
        for (int i = 0; i < star.length; i++) { star[i].update(delta); }
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) { star[i].draw(batch); }
        startButton.draw(batch);
        exitButton.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rectangle worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) { star[i].resize(worldBounds); }
        startButton.resize(worldBounds);
        exitButton.resize(worldBounds);
    }

    @Override
    public void dispose() {
        imageBackgraund.dispose();
        textureAtlas.dispose();
//        music.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        startButton.touchDown(touch, pointer);
        exitButton.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        startButton.touchUp(touch, pointer);
        exitButton.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        startButton.touchDragged(touch, pointer);
        exitButton.touchDragged(touch, pointer);
        return super.touchDragged(touch, pointer);
    }


}
