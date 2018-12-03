package ru.kravchenko.android.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Randomes;
import ru.kravchenko.android.math.Rectangle;

public class Star extends Sprite {

    private Rectangle worldBounds;

    private Vector2 speedStar = new Vector2();

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeigthProportion(0.01f);
        speedStar.set(Randomes.nextFloat(-0.005f, 0.005f), Randomes.nextFloat(-0.5f, -0.1f));
    }

    @Override
    public void resize(Rectangle worldBounce) {
        super.resize(worldBounce);
        this.worldBounds = worldBounce;
        float positionXStar = Randomes.nextFloat(worldBounce.getLeft(), worldBounce.getRight());
        float positionYStar = Randomes.nextFloat(worldBounce.getBottom(), worldBounce.getTop());
        System.out.println("star X " + positionXStar + " star Y " + positionYStar);
        position.set(positionXStar,positionYStar);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        position.mulAdd(speedStar, delta);
        chekInBounds();
    }

    private void chekInBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

}
