package ru.kravchenko.android.pool;

import ru.kravchenko.android.base.SpritesPool;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.sprite.Enemy;
import ru.kravchenko.android.sprite.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;

    private MainShip mainShip;

    private Rectangle worldBounds;

    public EnemyPool(BulletPool bulletPool, MainShip mainShip, Rectangle worldBounds) {
        this.bulletPool = bulletPool;
        this.mainShip = mainShip;
        this.worldBounds = worldBounds;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, mainShip, worldBounds);
    }

}
