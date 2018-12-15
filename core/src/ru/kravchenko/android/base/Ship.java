package ru.kravchenko.android.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.pool.BulletPool;
import ru.kravchenko.android.pool.ExplosionPool;
import ru.kravchenko.android.sprite.Bullet;
import ru.kravchenko.android.sprite.Explosion;

public class Ship extends Sprite {

    protected Vector2 speedShip = new Vector2();

    protected BulletPool bulletPool;

    protected ExplosionPool explosionPool;

    protected TextureRegion bulletRegion;

    protected Vector2 bulletSpeed = new Vector2();

    protected float bulletHeight;

    protected int bulletDamage;

    protected int health;

    protected float reloadInterval;

    protected float reloadTimer;

    protected Rectangle worldBounds;

    protected Sound shootSound;

    private float damageAnimateInterval = 0.1f;

    private float damageAnimateTimer = damageAnimateInterval;

    public Ship(TextureRegion region, int rows, int cols, int frame, Sound shootSound) {
        super(region, rows, cols, frame);
        this.shootSound = shootSound;
    }

    public Ship(TextureRegion region, int rows, int cols, int frame) {
        super(region, rows, cols, frame);

    }

    public Ship() {}

    @Override
    public void update(float delta) {
        super.update(delta);
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= damageAnimateInterval) frame = 0;
    }

    @Override
    public void resize(Rectangle worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, position, bulletSpeed, bulletHeight, worldBounds, bulletDamage);
    }

    public void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), position);
    }

    public void damage(int damage) {
        health -= damage;
        if (health <= 0) {
            setDestroyed(true);
            boom();
        }
        damageAnimateTimer = 0f;
        frame = 1;
    }

    public int getHealth() {
        return health;
    }
}
