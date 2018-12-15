package ru.kravchenko.android.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.kravchenko.android.base.Sprite;

public class MessageGameOver extends Sprite {

    public MessageGameOver(TextureAtlas atlas){
        super(atlas.findRegion("message_game_over"));
        setHeigthProportion(0.07f);
        setBottom(0.009f);
    }
}
