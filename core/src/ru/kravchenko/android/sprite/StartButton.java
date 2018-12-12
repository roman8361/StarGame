package ru.kravchenko.android.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.math.Rectangle;
import ru.kravchenko.android.screen.GameScreen;

public class StartButton extends Sprite {

    private Rectangle worldBounds;

    private Game game;

    private final float SIZE_START_BUTTON = 0.17f;

    public StartButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        setHeigthProportion(SIZE_START_BUTTON);
        this.game = game;
    }

    @Override
    public void resize(Rectangle worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
        setLeft(worldBounds.getLeft() + 0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isMe(touch)) {
            setHeigthProportion(SIZE_START_BUTTON / 2);
            actionPerfomed();
        }
        System.out.println("New game");

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

    private void actionPerfomed() { game.setScreen(new GameScreen(game)); }

}
