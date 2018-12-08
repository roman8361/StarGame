package ru.kravchenko.android.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Rectangle;

public class EnemyShip1 extends Sprite {

    private Vector2 speedEnemyShip0 = new Vector2(0.5f, 0);

    private Vector2 speedEnemyShip = new Vector2();

    private TextureAtlas atlas;

    private Rectangle worldBounds;

    public EnemyShip1(TextureAtlas atlas) {
        super(atlas.findRegion("enemy1"), 1, 2, 2);
        setHeigthProportion(0.15f);
        this.atlas = atlas;
    }

    @Override
    public void resize(Rectangle worldBounce) {
        super.resize(worldBounce);
        setTop(worldBounce.getTop() - 0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
