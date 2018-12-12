package ru.kravchenko.android.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Rectangle;

public class Bullet extends Sprite {

    private Rectangle worldBounds;

    private Vector2 speedBullet = new Vector2();

    private int damage;

    private Object owner;

    public Bullet() {
        regions = new TextureRegion[1];
    }

    public void set(
            Object owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rectangle worldBounds,
            int damage
            ) {
        this.owner = owner;
        this.regions[0] = region;
        this.position.set(pos0);
        this.speedBullet.set(v0);
        setHeigthProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        position.mulAdd(speedBullet, delta);
        if (isOutside(worldBounds)) setDestroyed(true);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Object getOwner() { return owner; }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

}
