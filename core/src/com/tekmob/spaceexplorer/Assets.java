package com.tekmob.spaceexplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    public static Texture arrowflip;
    public static Texture PLAYER;
    public static Texture OBSTACLE;
    public static Texture MISSILE;
    public static Texture SHIELDPU;
    public static Texture MISSILEPU;
    public static Texture dummy;

    public static Sound hitSound;
    public static Sound hitpuSound;
    public static Sound laserSound;
    public static Music menuMusic;
    public static Music gameMusic;

    private static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load(){
        FreeTypeFontGenerator generator;

        // textures
        background = loadTexture("ui/background.jpg");
        gameBack = loadTexture("ui/bg_game.png");
        arrow = loadTexture("ui/arrow.png");
        arrowflip = loadTexture("ui/arrowflip.png");
        dummy = loadTexture("badlogic.jpg");

        // fonts
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/space.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        nasa = generator.generateFont(parameter);
        generator.dispose();
        
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/nasalization.ttf"));
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

        hitSound = Gdx.audio.newSound(Gdx.files.internal("sound/explode.wav"));
        hitpuSound = Gdx.audio.newSound(Gdx.files.internal("sound/pu.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sound/laser.wav"));

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/game.mp3"));
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/menu.mp3"));
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

        // sound
        hitSound.dispose();
        hitpuSound.dispose();
        laserSound.dispose();
        gameMusic.dispose();
        menuMusic.dispose();

        menuAtlas.dispose();
        gameAtlas.dispose();
    }
}
