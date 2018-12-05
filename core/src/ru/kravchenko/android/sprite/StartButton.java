package ru.kravchenko.android.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Rectangle;

public class StartButton extends Sprite {

    private Rectangle worldBounds;

    private final float SIZE_START_BUTTON = 0.17f;

    public StartButton(TextureAtlas atlas) {
        super(atlas.findRegion("btPlay"));
        setHeigthProportion(SIZE_START_BUTTON);
    }

    @Override
    public void resize(Rectangle worldBounce) {
        super.resize(worldBounce);
        this.worldBounds = worldBounce;
        position.set(SIZE_START_BUTTON * -1, SIZE_START_BUTTON * -2);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isMe(touch)) setHeigthProportion(SIZE_START_BUTTON / 2);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        setHeigthProportion(SIZE_START_BUTTON);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (isMe(touch)) setHeigthProportion(SIZE_START_BUTTON / 2);
        return super.touchDragged(touch, pointer);
    }

}
