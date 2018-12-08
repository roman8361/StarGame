package ru.kravchenko.android.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.pool.BulletPool;

public class MainShip extends Sprite {

    private Vector2 speedMainShip0 = new Vector2(0.5f, 0);

    private Vector2 speedMainShip = new Vector2();

    private TextureAtlas atlas;

    private Rectangle worldBounds;

    private boolean pressedLeft;

    private boolean pressedRigth;

    private BulletPool bulletPool;

    private Sound shotSound = Gdx.audio.newSound(Gdx.files.internal("sound/shot.wav"));


    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeigthProportion(0.15f);
        this.atlas = atlas;
        this.bulletPool = bulletPool;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        position.mulAdd(speedMainShip, delta);
        chekEndScreenLeft();
        chekEndScreenRigth();
    }

    @Override
    public void resize(Rectangle worldBounce) {
        this.worldBounds = worldBounce;
        setBottom(worldBounce.getBottom() + 0.05f);
    }

    public boolean keyDown(int keycode) {
       switch (keycode) {
           case Input.Keys.A:
           case Input.Keys.LEFT:
               pressedLeft = true;
               moveLeft();
               chekEndScreenLeft();
               break;
           case Input.Keys.D:
           case Input.Keys.RIGHT:
               pressedRigth = true;
               moveRigth();
               chekEndScreenRigth();
               break;
           case Input.Keys.UP:
               break;
       }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRigth) { moveRigth();}
                else stop();
                chekEndScreenLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRigth = false;
                if (pressedLeft) { moveLeft();}
                else stop();
                chekEndScreenRigth();
                break;
            case Input.Keys.SPACE:
                shoot();
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < 0) {
            System.out.println("POSITION.X " + position.x);
            chekEndScreenLeft();
            moveLeft();
        }

        if (touch.x > 0) moveRigth();

        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (touch.x < 0) stop();
        if (touch.x > 0) stop();
        return super.touchUp(touch, pointer);
    }

    private void moveRigth() {
        speedMainShip.set(speedMainShip0);
    }

    private void moveLeft() {
        speedMainShip.set(speedMainShip0).rotate(180);
    }

    private void stop() {
        speedMainShip.setZero();
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, atlas.findRegion("bulletMainShip"), position, new Vector2(0, 1.5f), 0.01f, worldBounds,1  );
        shotSound.play();
    }

    private void chekEndScreenLeft(){
        if (position.x < worldBounds.getLeft() + 0.05f) stop();
    }

    private void chekEndScreenRigth() {
        if (position.x > worldBounds.getRight() - 0.05f) stop();
    }

}
