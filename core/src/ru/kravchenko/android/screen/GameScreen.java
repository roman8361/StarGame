package ru.kravchenko.android.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import ru.kravchenko.android.base.Base2DScreen;
import ru.kravchenko.android.base.Font;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.pool.BulletPool;
import ru.kravchenko.android.pool.EnemyPool;
import ru.kravchenko.android.pool.ExplosionPool;
import ru.kravchenko.android.sprite.Background;
import ru.kravchenko.android.sprite.Bullet;
import ru.kravchenko.android.sprite.ButtonStartNewGame;
import ru.kravchenko.android.sprite.Enemy;
import ru.kravchenko.android.sprite.MainShip;
import ru.kravchenko.android.sprite.MessageGameOver;
import ru.kravchenko.android.sprite.Star;
import ru.kravchenko.android.utils.EnemiesEmitter;

public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 64;

    private static final float FONT_SIZE = 0.02f;

    private static final String FRAGS = "Frags: ";
    private static final String HEALTH = "Health: ";
    private static final String LEVEL = "Level: ";

    private TextureAtlas textureAtlas;

    private Texture imageBackgraund;

    private Background background;

    private Star[] star;

    private MainShip mainShip;

    private BulletPool bulletPool;

    private EnemyPool enemyPool;

    private ExplosionPool explosionPool;

    private EnemiesEmitter enemiesEmitter;

    private Sound mainShipShootSound;

    private Sound enemyShipShootSound;

    private Sound explosionSound;

    private enum State {PLAYING, GAME_OVER}

    private State state;

    private MessageGameOver messageGameOver;

    private int frags;

    private ButtonStartNewGame buttonStartNewGame;

    private Font font;

    private StringBuilder stringBuilderFrags = new StringBuilder();

    private StringBuilder stringBuilderHealth = new StringBuilder();

    private StringBuilder stringBuilderLevel = new StringBuilder();

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
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sound/explosion.wav"));
        explosionPool = new ExplosionPool(textureAtlas, explosionSound);
        mainShipShootSound = Gdx.audio.newSound(Gdx.files.internal("sound/shot.wav"));
        enemyShipShootSound = Gdx.audio.newSound(Gdx.files.internal("sound/lazer.wav"));
        mainShip = new MainShip(textureAtlas, bulletPool, mainShipShootSound, worldBounds, explosionPool);
        enemyPool = new EnemyPool(bulletPool, explosionPool, mainShip, worldBounds, enemyShipShootSound);
        enemiesEmitter = new EnemiesEmitter(worldBounds, enemyPool, textureAtlas);
        messageGameOver = new MessageGameOver(textureAtlas);
        buttonStartNewGame = new ButtonStartNewGame(textureAtlas, this);
        font = new Font("font/font.fnt", "font/font.png");
        font.setFontSize(FONT_SIZE);
        startNewGame();
    }

    @Override
    public void render(float delta) {
        update(delta);
        if (state == State.PLAYING) chekCollisions();
        deleteAllDestroid();
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) { star[i].update(delta); }
        explosionPool.updateActiveSprites(delta);
        switch (state) {
            case PLAYING:
                if (!mainShip.isDestroyed()) mainShip.update(delta);
                bulletPool.updateActiveSprites(delta);
                enemyPool.updateActiveSprites(delta);
                enemiesEmitter.generate(delta, frags);
                break;

            case GAME_OVER:
                break;
        }
    }

    public void chekCollisions() {
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getHalfWidth() + mainShip.getHalfWidth();
            if (enemy.position.dst2(mainShip.position) < minDist * minDist) {
                enemy.setDestroyed(true);
                enemy.boom();
                mainShip.damage(mainShip.getHealth());
                if (mainShip.isDestroyed()) state = State.GAME_OVER;
                return;
            }
        }

        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != mainShip || bullet.isDestroyed()) {
                    continue;
                }
                if (enemy.isBulletCollision(bullet)) {
                    enemy.damage(bullet.getDamage());
                    bullet.setDestroyed(true);
                    if (enemy.isDestroyed()) frags++;
                }
            }
        }

        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed() || bullet.getOwner() == mainShip) {
                continue;
            }
            if (mainShip.isBulletCollision(bullet)) {
                bullet.setDestroyed(true);
                mainShip.damage(bullet.getDamage());
                if (mainShip.isDestroyed()) state = State.GAME_OVER;
            }
        }
    }

    public void deleteAllDestroid() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
    }



    public void draw() {
        Gdx.gl.glClearColor(1, 0.3f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) star[i].draw(batch);
        explosionPool.drawActiveSprites(batch);
        switch (state) {
            case PLAYING:
                if (!mainShip.isDestroyed()) mainShip.draw(batch);
                bulletPool.drawActiveSprites(batch);
                enemyPool.drawActiveSprites(batch);
                break;

            case GAME_OVER:
                messageGameOver.draw(batch);
                buttonStartNewGame.draw(batch);
                break;
        }
        printInfo();
        batch.end();
    }

    public void printInfo() {
        stringBuilderFrags.setLength(0);
        stringBuilderHealth.setLength(0);
        stringBuilderLevel.setLength(0);
        font.draw(batch, stringBuilderFrags.append(FRAGS).append(frags), worldBounds.getLeft(), worldBounds.getTop());
        font.draw(batch, stringBuilderHealth.append(HEALTH).append(mainShip.getHealth()), worldBounds.position.x, worldBounds.getTop(), Align.center);
        font.draw(batch, stringBuilderLevel.append(LEVEL).append(enemiesEmitter.getLevel()), worldBounds.getRight(), worldBounds.getTop(), Align.right); }

    @Override
    public void resize(Rectangle worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) { star[i].resize(worldBounds); }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        imageBackgraund.dispose();
        textureAtlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        mainShipShootSound.dispose();
        enemyShipShootSound.dispose();
        font.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        switch (state) {
            case PLAYING:
                mainShip.touchDown(touch, pointer);
                break;
            case GAME_OVER:
                buttonStartNewGame.touchDown(touch, pointer);
                break;
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        switch (state) {
            case PLAYING:
                mainShip.touchUp(touch, pointer);
                break;

            case GAME_OVER:
                buttonStartNewGame.touchUp(touch, pointer);
                break;
        }
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

    public void startNewGame() {
        state = State.PLAYING;
        frags = 0;
        mainShip.setToNewGame();
        enemiesEmitter.setToNewGame();
        bulletPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
    }

}
