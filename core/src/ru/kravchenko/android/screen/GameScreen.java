package ru.kravchenko.android.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Base2DScreen;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.pool.BulletPool;
import ru.kravchenko.android.sprite.Background;
import ru.kravchenko.android.sprite.EnemyShip1;
import ru.kravchenko.android.sprite.MainShip;
import ru.kravchenko.android.sprite.Star;

public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 64;

    private TextureAtlas textureAtlas;

    private Texture imageBackgraund;

    private Background background;

    private Star[] star;

    private MainShip mainShip;

    private BulletPool bulletPool;

    private EnemyShip1 enemyShip1;

    public GameScreen(Game game) {
        super(game);
        System.out.println("GameScreen");
    }

    @Override
    public void show() {
        super.show();
        textureAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        imageBackgraund = new Texture("textures/sky.jpg");
        background = new Background(new TextureRegion(imageBackgraund));
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) { star[i] = new Star(textureAtlas); }
        bulletPool = new BulletPool();
        mainShip = new MainShip(textureAtlas, bulletPool);
        enemyShip1 = new EnemyShip1(textureAtlas);
    }

    @Override
    public void render(float delta) {
        update(delta);
        chekCollisions();
        deleteAllDestroid();
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) { star[i].update(delta); }
        mainShip.update(delta);
        enemyShip1.update(delta);
        bulletPool.updateActiveSprites(delta);
    }

    public void chekCollisions() {

    }

    public void deleteAllDestroid() {
        bulletPool.freeAllDestroyedActiveSprites();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) { star[i].draw(batch); }
        mainShip.draw(batch);
        enemyShip1.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void resize(Rectangle worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) { star[i].resize(worldBounds); }
        mainShip.resize(worldBounds);
        enemyShip1.resize(worldBounds);
    }

    @Override
    public void dispose() {
        imageBackgraund.dispose();
        textureAtlas.dispose();
        bulletPool.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return super.keyUp(keycode);
    }

}
