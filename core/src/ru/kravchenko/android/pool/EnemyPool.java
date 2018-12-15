package ru.kravchenko.android.pool;

import com.badlogic.gdx.audio.Sound;

import ru.kravchenko.android.base.SpritesPool;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.sprite.Enemy;
import ru.kravchenko.android.sprite.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;

    private MainShip mainShip;

    private Rectangle worldBounds;

    private ExplosionPool explosionPool;

    private Sound shootSound;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, MainShip mainShip, Rectangle worldBounds, Sound shootSound) {
        this.bulletPool = bulletPool;
        this.mainShip = mainShip;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.shootSound = shootSound;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, mainShip, worldBounds, shootSound);
    }

}
