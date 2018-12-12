package ru.kravchenko.android.sprite;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.kravchenko.android.base.Ship;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.pool.BulletPool;

public class Enemy extends Ship {

    private MainShip mainShip;

    private Vector2 speedEnemy = new Vector2();

    public Enemy(BulletPool bulletPool, MainShip mainShip, Rectangle worldBounds) {
        this.bulletPool = bulletPool;
        this.mainShip = mainShip;
        this.worldBounds = worldBounds;
        this.speedShip.set(speedEnemy);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        position.mulAdd(speedEnemy, delta);
        reloadTimer += delta;
        if (reloadInterval >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        if (getBottom() <= worldBounds.getBottom()) this.setDestroyed(true);
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
    }

}
