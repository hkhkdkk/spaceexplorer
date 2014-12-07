package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Rahmat Rasyidi Hakim on 11/27/2014.
 */
public class Assets {

    public static BitmapFont nasa;
    public static BitmapFont space;
    public static BitmapFont spaceHow;
    public static TextureAtlas menuAtlas;
    public static TextureAtlas gameAtlas;
    public static Texture background;
    public static Texture gameBack;
    public static Texture arrow;
    public static Texture PLAYER;
    public static Texture OBSTACLE;
    public static Texture MISSILE;
    public static Texture SHIELDPU;
    public static Texture MISSILEPU;

    private static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load(){
        FreeTypeFontGenerator generator;

        // textures
        background = loadTexture("ui/background.jpg");
        gameBack = loadTexture("ui/bg_game.png");
        arrow = loadTexture("ui/arrow.png");
        
        // fonts
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/nasalization.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        nasa = generator.generateFont(parameter);
        generator.dispose();
        
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/space.ttf"));
        parameter.size = 60;
        space = generator.generateFont(parameter);
        parameter.size = 35;
        spaceHow = generator.generateFont(parameter);
        generator.dispose();

        // textures from atlas
        menuAtlas = new TextureAtlas(Gdx.files.internal("ui/menu.atlas"));
        gameAtlas = new TextureAtlas(Gdx.files.internal("ui/game.atlas"));

        PLAYER = gameAtlas.findRegion("ship").getTexture();
        OBSTACLE = gameAtlas.findRegion("meteorGrey_big1").getTexture();
        MISSILE = gameAtlas.findRegion("missile").getTexture();
        MISSILEPU = gameAtlas.findRegion("pushield1").getTexture();
        SHIELDPU = gameAtlas.createSprite("pumissile1").getTexture();
    }

    public static void dispose() {
    	// textures
        background.dispose();
        gameBack.dispose();
        arrow.dispose();
        
        // fonts
        nasa.dispose();
        space.dispose();
        spaceHow.dispose();
        
        // textures from atlas
        PLAYER.dispose();
        OBSTACLE.dispose();
        MISSILE.dispose();
        SHIELDPU.dispose();
        MISSILEPU.dispose();  	

        menuAtlas.dispose();
        gameAtlas.dispose();
    }
}
