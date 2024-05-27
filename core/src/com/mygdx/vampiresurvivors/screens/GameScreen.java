package com.mygdx.vampiresurvivors.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.vampiresurvivors.VampireSurvivors;
import com.mygdx.vampiresurvivors.ecs.component.PositionComponent;
import com.mygdx.vampiresurvivors.ecs.component.TextureComponent;
import com.mygdx.vampiresurvivors.ecs.component.VelocityComponent;
import com.mygdx.vampiresurvivors.ecs.system.MovementSystem;
import com.mygdx.vampiresurvivors.ecs.system.RenderingSystem;

public class GameScreen extends ScreenAdapter {

  private final AssetManager assetManager;
  private final Engine engine;
  private final SpriteBatch batch;
  private final Viewport viewport;

  private final Texture img;

  public GameScreen(final VampireSurvivors context) {
    this.assetManager = context.getAssetManager();
    engine = new PooledEngine();
    this.batch = context.getBatch();
    this.viewport = context.getViewport();

    assetManager.load("badlogic.jpg", Texture.class);
    assetManager.finishLoading();

    img = assetManager.get("badlogic.jpg", Texture.class);
    final Entity entity = engine.createEntity();

    final PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
    positionComponent.position.set(0, 0);

    final TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
    textureComponent.texture = img;

    final VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
    velocityComponent.speed = 10;
    velocityComponent.velocity.set(1, 1);

    entity.add(positionComponent);
    entity.add(textureComponent);
    entity.add(velocityComponent);

    Gdx.app.log("GameScreen", "Adding entity with position " + positionComponent.position + " and velocity " + velocityComponent.velocity);

    engine.addEntity(entity);

    engine.addSystem(new RenderingSystem(batch, (OrthographicCamera) viewport.getCamera()));
    engine.addSystem(new MovementSystem());
  }

  private void update(final float delta) {
    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      Gdx.app.exit();
    }

    engine.update(delta);
  }

  @Override
  public void render(final float delta) {
    update(delta);

  }
}
