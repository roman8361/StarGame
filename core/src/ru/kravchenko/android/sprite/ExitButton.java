package ru.kravchenko.android.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Rectangle;

public class ExitButton extends Sprite {

    private Rectangle worldBounds;

    private final float SIZE_EXIT_BUTTON = 0.15f;

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
        setHeigthProportion(SIZE_EXIT_BUTTON);
        System.out.println("getBottom() " + getBottom());

    }

    @Override
    public void resize(Rectangle worldBounce) {
        super.resize(worldBounce);
        this.worldBounds = worldBounce;   // position.set(0.15f, -0.36f);
        position.set(SIZE_EXIT_BUTTON, SIZE_EXIT_BUTTON * -2 - 0.06f);
    }

    @Override
    public void update(float delta) { super.update(delta); }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isMe(touch))Gdx.app.exit();
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        setHeigthProportion(SIZE_EXIT_BUTTON);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (isMe(touch))Gdx.app.exit();
        return super.touchDragged(touch, pointer);
    }

}
