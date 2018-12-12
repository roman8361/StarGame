package ru.kravchenko.android.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Ship;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.pool.BulletPool;

public class MainShip extends Ship {

    private static final int INVALID_POINTER = -1;

    private Vector2 speedMainShip0 = new Vector2(0.5f, 0);

    private int leftPointer = INVALID_POINTER;

    private int rightPointer = INVALID_POINTER;

    private boolean pressedLeft;

    private boolean pressedRight;

    private Sound shotSound = Gdx.audio.newSound(Gdx.files.internal("sound/shot.wav"));

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeigthProportion(0.15f);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletHeight = 0.01f;
        this.bulletSpeed.set(0, 2.0f);
        this.bulletDamage = 1;
        this.health = 100;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        position.mulAdd(speedShip, delta);
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
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
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) { moveRight();}
                else stop();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) { moveLeft();}
                else stop();
                break;
            case Input.Keys.SPACE:
                shoot();
                shotSound.play();
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.position.x) {
            if (leftPointer != INVALID_POINTER) return false;
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) return false;
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPointer) {
        leftPointer = INVALID_POINTER;
        if (rightPointer != INVALID_POINTER) {
            moveRight();
        } else {
            stop();
        }
    } else if (pointer == rightPointer) {
        rightPointer = INVALID_POINTER;
        if (leftPointer != INVALID_POINTER) {
            moveLeft();
        } else {
            stop();
        }
    }
        return false;
    }

    private void moveRight() { speedShip.set(speedMainShip0); }

    private void moveLeft() { speedShip.set(speedMainShip0).rotate(180); }

    private void stop() { speedShip.setZero(); }

}
