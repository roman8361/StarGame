package ru.kravchenko.android.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Rectangle;

public class StartButton extends Sprite {

    private Rectangle worldBounds;

    public StartButton(TextureAtlas atlas) {
        super(atlas.findRegion("btPlay"));
        setHeigthProportion(0.2f);
    }

    @Override
    public void resize(Rectangle worldBounce) {
        super.resize(worldBounce);
        this.worldBounds = worldBounce;
        position.set(-0.22f, -0.32f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (chekInForm(touch)) setHeigthProportion(0.1f);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        setHeigthProportion(0.2f);
        return super.touchUp(touch, pointer);
    }

    private boolean chekInForm(Vector2 touch){
        if (touch.x >= 40 && touch.x <= 140 && touch.y >= 40 && touch.y <= 170) return true;
        return false;
    }

}
