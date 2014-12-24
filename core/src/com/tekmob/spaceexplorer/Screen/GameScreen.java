package com.tekmob.spaceexplorer.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.tekmob.spaceexplorer.Assets;
import com.tekmob.spaceexplorer.Controller.ObjectContainer;
import com.tekmob.spaceexplorer.Controller.PreferenceController;
import com.tekmob.spaceexplorer.Model.SpaceObject;
import com.tekmob.spaceexplorer.SpaceExplorer;

import java.util.Iterator;

/**
 * Created by Rahmat Rasyidi Hakim on 11/20/2014.
 */

public class GameScreen extends BaseScreen {

    private static final int VELOCITY_PLANE = 7;
    private static final int INIT_X_SHIP = 1280/2;
    private static final int INIT_Y_SHIP = 120;

    private SpriteBatch batch;
    private Image background;
    private Skin skin;
    private TextureRegion imgPlane;
    private TextureRegion imgObstacle;
    private TextureRegion imgButtonShield;
    private TextureRegion imgButtonMissile;
    private TextureRegion imgMissilePU;
    private TextureRegion imgShieldPU;
    private TextureRegion imgShield;
    private Image buttonMissile;
    private Image buttonShield;
    private OrthographicCamera camera;
    private Plane ship;

    private Rectangle rectShield;

    private Array<Obstacle> obstacles;
    private Array<PUMissile> powerUpMissile;
    private Array<PUShield> powerUpShield;

    private long lastObstacleSpawnTime = 0, lastShieldSpawnTime = 0;
    private int score = 0;
    private int seconds = 0;
    private double miles = 0.0;
    private int temp = 0;
    private int countShield = 0;
    private int countMissile = 0;
    private float adjustedY;
    private int obstacleVelocity = 0;
    private int powerupVelocity = 0;
    private float timer = 0;
    private boolean gameOver = false, activedMissile = false, activedShield = false;


    public GameScreen(SpaceExplorer s){
        super(s);

        batch = new SpriteBatch();
        skin = new Skin();
        skin.addRegions(Assets.gameAtlas);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, spaceExplorer.WIDTH, spaceExplorer.HEIGHT);

        Assets.gameMusic.setLooping(true);

        imgPlane = skin.getRegion("ship");
        imgObstacle = skin.getRegion("meteorGrey_big1");
        imgButtonShield = skin.getRegion("shield");
        imgButtonMissile = skin.getRegion("missile");
        imgShieldPU = skin.getRegion("pushield1");
        imgMissilePU = skin.getRegion("pumissile1");
        imgShield = skin.getRegion("shield1");

        background = new Image(Assets.gameBack);
        background.setSize(SpaceExplorer.WIDTH, SpaceExplorer.HEIGHT);

        rectShield = new Rectangle();

        obstacles = new Array<Obstacle>();
        powerUpMissile = new Array<PUMissile>();
        powerUpShield = new Array<PUShield>();

        //setup button missile
        buttonMissile = new Image(imgButtonMissile);
        buttonMissile.setPosition(spaceExplorer.WIDTH-120,10);

        buttonShield = new Image(imgButtonShield);
        buttonShield.setPosition(20,10);

        stage.addActor(background);
        stage.addActor(buttonMissile);
        stage.addActor(buttonShield);

        resetWorld();
        createButtonPowerUp();
        spawnObstacle();
    }

    private void initShip(){
        ship = new Plane(INIT_X_SHIP, INIT_Y_SHIP, imgPlane);
    }

    private void resetWorld(){
        score = 0;
        countShield = 0;
        countMissile = 0;
        miles = 0.0;
        timer = 0;
        seconds = 0;
        temp = 0;
        activedMissile = false;
        activedShield = false;

        obstacles.clear();
        powerUpMissile.clear();
        powerUpShield.clear();
        initShip();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        drawWorld();
        updateWorld();
        onBackScreen();
    }

    private void updateWorld(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        timer += deltaTime;

        rectShield.set(ship.position.x, ship.position.y, imgShield.getRegionWidth(), imgShield.getRegionHeight());

        if(!gameOver){

            if(score >= 40){
                obstacleVelocity = 320; //320 <- Jangan Lupa Tukar lagi
            }
            else{
                obstacleVelocity = 300;
            }

            powerupVelocity = 220;

            if (timer >= 1) {
                score++;
                timer -= 1;
                seconds++;
                temp++;
                miles += 1.0;  //jangan lupa dihapus
            }

            if(temp >= 30){
                miles += 0.5;
                temp = 0;
            }
            controlInput();
        }

        if (gameOver) {
            powerupVelocity = 0;
            obstacleVelocity = 0;

            for (int i = 0; i < 10; i++) {
                cekUnlockedPlanet(ObjectContainer.objects.get(i));
            }

            addStat();
            if(Gdx.input.justTouched()) {
                gameOver = false;
                resetWorld();
            }
        }

        spawnEntity();

        Iterator<Obstacle> iterObstacle = obstacles.iterator();
        Iterator<PUMissile> iterPowerUpMissile = powerUpMissile.iterator();
        Iterator<PUShield> iterPowerUpShield = powerUpShield.iterator();

        while (iterObstacle.hasNext()){
            Obstacle o = iterObstacle.next();
            o.position.y -= obstacleVelocity * deltaTime;

            if(o.position.y + o.image.getRegionHeight() < 0){
                iterObstacle.remove();
            }

            if(o.getBounds().overlaps(ship.getBounds())){
                if(!activedShield){
                    if(spaceExplorer.getPreferences().isSoundEnabled())
                    Assets.hitSound.play();
                    iterObstacle.remove();
                    gameOver = true;
                }
                if(activedShield){
                    iterObstacle.remove();
                    activedShield = false;
                }
            }

            /*while (iterMissile.hasNext()){
                Missile mis = iterMissile.next();
                mis.position.y += powerupVelocity * deltaTime;

                if(mis.position.y > spaceExplorer.HEIGHT){
                    iterMissile.remove();
                }

                if(mis.getBounds().overlaps(o.getBounds())){
                    iterMissile.remove();
                    iterObstacle.remove();
                }

                if(mis == null){
                    drawMissile = false;
                }
            }*/
        }

        while (iterPowerUpMissile.hasNext()){
            PUMissile mis = iterPowerUpMissile.next();
            mis.position.y -= powerupVelocity * deltaTime;

            if(mis.position.y + mis.image.getRegionHeight() < 0){
                iterPowerUpMissile.remove();
            }

            if(mis.getBounds().overlaps(ship.getBounds())){
                countMissile++;
                score += score + 1;

                if(spaceExplorer.getPreferences().isSoundEnabled())
                Assets.hitpuSound.play();

                iterPowerUpMissile.remove();
            }

        }

        while (iterPowerUpShield.hasNext()){
            PUShield sh = iterPowerUpShield.next();
            sh.position.y -= powerupVelocity * deltaTime;

            if(sh.position.y + sh.image.getRegionHeight() < 0){
                iterPowerUpShield.remove();
            }

            if(sh.getBounds().overlaps(ship.getBounds())){
                countShield++;
                score += score + 2;

                if(spaceExplorer.getPreferences().isSoundEnabled())
                Assets.hitpuSound.play();
                iterPowerUpShield.remove();
            }
        }

    }

    private void spawnEntity(){
        if(TimeUtils.nanoTime() - lastObstacleSpawnTime > MathUtils.random(180000000,200000000)) spawnObstacle();

        if(seconds >= 6) {
            spawnPUMissile();
            seconds = 0;
        }

        if(activedMissile){
            obstacles.clear();
            if(spaceExplorer.getPreferences().isSoundEnabled())
            Assets.hitSound.play(3f);
            activedMissile = false;
        }

        if(TimeUtils.nanoTime() - lastShieldSpawnTime > MathUtils.random(1800000000,2000000000)) spawnPUShield();
    }

    private void drawWorld(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

            for(Obstacle obs:obstacles){
                batch.draw(obs.image, obs.position.x, obs.position.y);
            }

            for(PUMissile mis:powerUpMissile){
                batch.draw(mis.image, mis.position.x, mis.position.y);
            }

            for(PUShield sh:powerUpShield){
                batch.draw(sh.image, sh.position.x, sh.position.y);
            }
        batch.end();

        batch.begin();
            Assets.nasaGame.draw(batch, miles + " AU", 20, spaceExplorer.HEIGHT - 10);
            Assets.nasaGame.draw(batch, "score: "+score, spaceExplorer.WIDTH-240, spaceExplorer.HEIGHT-10);
            batch.draw(imgPlane, ship.position.x, ship.position.y);
            if(activedShield)
                batch.draw(imgShield, ship.position.x - 10, ship.position.y);
            buttonShield.draw(batch,1f);
            buttonMissile.draw(batch,1f);
            Assets.nasaGame.draw(batch, countShield + "", 140, 50);
            Assets.nasaGame.draw(batch, countMissile + "", spaceExplorer.WIDTH-200, 50);
            if(gameOver)
            batch.draw(Assets.gameOver, spaceExplorer.WIDTH/2 - Assets.gameOver.getWidth()/2, spaceExplorer.HEIGHT/2 - Assets.gameOver.getHeight()/2);
        batch.end();
    }

    PreferenceController prefCont = new PreferenceController();

    private void cekUnlockedPlanet(SpaceObject spaceObject){
        if(miles > spaceObject.getDistance()) {
            // do whatever
            if (!prefCont.getAchievementStatus(spaceObject.getKey())) {
                prefCont.unlockAchievement(spaceObject.getKey());
                batch.begin();
                    Assets.roboto.draw(batch, spaceObject.getName(), spaceExplorer.WIDTH, spaceExplorer.HEIGHT/2+50);
                batch.end();
                Gdx.app.log("NOTIF","ITEM "+spaceObject.getName());
            }
        }
    }

    private void addStat(){
        int prevHighScore = prefCont.getInteger(PreferenceController.STATISTIC, PreferenceController.SCORE);
        if (score > prevHighScore) {
            prefCont.putData(PreferenceController.STATISTIC, PreferenceController.SCORE, score);
        }

        int prevMaxMissile = prefCont.getInteger(PreferenceController.STATISTIC, PreferenceController.MISSILE);
        if (countMissile > prevMaxMissile) {
            prefCont.putData(PreferenceController.STATISTIC, PreferenceController.MISSILE, countMissile);
        }

        int prevMaxShield = prefCont.getInteger(PreferenceController.STATISTIC, PreferenceController.SHIELD);
        if (countShield > prevMaxShield) {
            prefCont.putData(PreferenceController.STATISTIC, PreferenceController.SHIELD, countShield);
        }

        Gdx.app.log("LALALA",prefCont.getString(PreferenceController.STATISTIC, PreferenceController.MILESTONE));
        String [] tempString = prefCont.getString(PreferenceController.STATISTIC, PreferenceController.MILESTONE).split("-");
        Gdx.app.log("SHIT", tempString[0] + " " + tempString[1]);
        String tempMile = "";
        double distRequirement = ObjectContainer.objects.get(Integer.parseInt(tempString[1])).getDistance();
        if (miles > distRequirement) {
            for (int i = Integer.parseInt(tempString[1]) + 1; i < 10; i++) {
                if (miles < ObjectContainer.objects.get(i).getDistance()) {
                    tempMile = ObjectContainer.objects.get(i).getName() + "-" + i;
                    break;
                }
            }

        }
        prefCont.putData(PreferenceController.STATISTIC, PreferenceController.MILESTONE, tempMile);

    }

    private void controlInput(){
        //maju mundur
        /*adjustedX = Gdx.input.getAccelerometerX()-2f;
        if( adjustedX < - 1.5f ) adjustedX = - 1f;
        else if( adjustedX > 1.5f ) adjustedX = 1f;*/

        adjustedY = Gdx.input.getAccelerometerY()-1f;
        if( adjustedY < -1f ) ship.position.x -= Math.abs(adjustedY) + VELOCITY_PLANE;
        else if( adjustedY > 1f ) ship.position.x += Math.abs(adjustedY) + VELOCITY_PLANE;

        /*
        if(adjustedY == -1f) plane.x-= (Math.abs(adjustedY) + VELOCITY_PLANE);
        if(adjustedY == 1f) plane.x+= adjustedY + VELOCITY_PLANE;*/

        if (ship.position.x < 0) ship.position.x = 0;
        if (ship.position.x > spaceExplorer.WIDTH - ship.image.getRegionWidth()) ship.position.x = spaceExplorer.WIDTH - ship.image.getRegionWidth();
        if (ship.position.y < 0) ship.position.y = 0;
        if (ship.position.y > spaceExplorer.HEIGHT - ship.image.getRegionHeight()) ship.position.y = spaceExplorer.HEIGHT - ship.image.getRegionHeight();
    }

    private void createButtonPowerUp(){
        buttonMissile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (countMissile != 0 && !activedMissile) {
                    countMissile--;
                    activedMissile = true;
                }
            }
        });

        buttonShield.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(countShield != 0 && !activedShield) {
                    countShield--;
                    activedShield = true;
                }
            }
        });
    }

    private void spawnObstacle(){
        obstacles.add(new Obstacle(MathUtils.random(0, spaceExplorer.WIDTH - 101), spaceExplorer.HEIGHT, imgObstacle));
        lastObstacleSpawnTime = TimeUtils.nanoTime();
    }

    private void spawnPUMissile(){
        powerUpMissile.add(new PUMissile(MathUtils.random(0, spaceExplorer.WIDTH - 51), spaceExplorer.HEIGHT, imgMissilePU));
    }

    private void spawnPUShield(){
        powerUpShield.add(new PUShield(MathUtils.random(0, spaceExplorer.WIDTH - 51), spaceExplorer.HEIGHT, imgShieldPU));
        lastShieldSpawnTime = TimeUtils.nanoTime();
    }

    /*private void spawnMisille(){
        missile.add(new Missile(ship.position.x+(ship.image.getRegionWidth()/2), ship.position.y+10+(ship.image.getRegionHeight()/2), imgMissile));
    }*/

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        //Assets.gameMusic.play();
    }

    @Override
    public void dispose() {
        skin.dispose();
        batch.dispose();
        super.dispose();
    }

    static class Obstacle{
        Vector2 position = new Vector2();
        TextureRegion image;

        public Obstacle(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }
        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }

    /*
    static class Missile{
        Vector2 position = new Vector2();
        TextureRegion image;

        public Missile(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }*/

    static class PUMissile{
        Vector2 position = new Vector2();
        TextureRegion image;

        public PUMissile(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }

    static class PUShield{
        Vector2 position = new Vector2();
        TextureRegion image;

        public PUShield(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }

    static class Plane{
        Vector2 position = new Vector2();
        TextureRegion image;

        public Plane(float x, float y, TextureRegion image){
            this.position.x = x;
            this.position.y = y;
            this.image = image;
        }

        public Rectangle getBounds(){
            return new Rectangle(position.x, position.y, image.getRegionWidth(), image.getRegionHeight());
        }
    }
}
