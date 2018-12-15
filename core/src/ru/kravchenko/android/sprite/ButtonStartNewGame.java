package ru.kravchenko.android.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.kravchenko.android.base.ScaledButton;
import ru.kravchenko.android.base.Sprite;
import ru.kravchenko.android.screen.GameScreen;

public class ButtonStartNewGame extends ScaledButton {

    private GameScreen gameScreen;

    public ButtonStartNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
        setHeigthProportion(0.05f);
        setTop(-0.012f);
    }

    @Override
    protected void actionPerformed() {
        gameScreen.startNewGame();
    }
}
