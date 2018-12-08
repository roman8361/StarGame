package ru.kravchenko.android.pool;

import ru.kravchenko.android.base.SpritesPool;
import ru.kravchenko.android.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

}
