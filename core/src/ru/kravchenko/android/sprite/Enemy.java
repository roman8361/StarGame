package ru.kravchenko.android.sprite;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.kravchenko.android.base.Ship;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.pool.BulletPool;
import ru.kravchenko.android.pool.ExplosionPool;

public class Enemy extends Ship {

    private enum State {DESCENT, FIGHT}

    private MainShip mainShip;

    private Vector2 speedEnemy = new Vector2();

    private Vector2 descentSpeed = new Vector2(0, 0.15f);

    private State state;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, MainShip mainShip, Rectangle worldBounds, Sound shootSound) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.mainShip = mainShip;
        this.worldBounds = worldBounds;
        this.speedShip.set(speedEnemy);
        this.shootSound = shootSound;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        position.mulAdd(speedShip, delta);
        switch (state) {
            case DESCENT:
                if (getTop() <= worldBounds.getTop()) {
                    speedShip.set(speedEnemy);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:
                reloadTimer += delta;
                if (reloadTimer >= reloadInterval) {
                    reloadTimer = 0f;
                    shoot();
                }
                if (getBottom() <= worldBounds.getBottom()) {
                    this.setDestroyed(true);
                    boom();
                }
                break;
        }

    }

    public void set (
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletSpeed,
            int bulletDamage,
            float reloadInterval,
            float height,
            int health
    ){
        this.regions = regions;
        this.speedEnemy.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletSpeed.set(0f, bulletSpeed);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.health = health;
        setHeigthProportion(height);
        reloadTimer = reloadInterval;
        speedShip.set(v0);
        state = State.DESCENT;
    }

    public boolean isBulletCollision(Rectangle bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < position.y);
    }

}
