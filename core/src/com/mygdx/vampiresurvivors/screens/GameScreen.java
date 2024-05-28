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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.vampiresurvivors.Assets;
import com.mygdx.vampiresurvivors.VampireSurvivors;
import com.mygdx.vampiresurvivors.ecs.component.*;
import com.mygdx.vampiresurvivors.ecs.system.MovementSystem;
import com.mygdx.vampiresurvivors.ecs.system.PlayerSystem;
import com.mygdx.vampiresurvivors.ecs.system.RenderingSystem;

public class GameScreen extends ScreenAdapter {

  private final Engine engine;

  private final Assets assets;
  private final RandomXS128 random;

  public GameScreen(final VampireSurvivors context) {
    assets = context.getAssetManager();
    engine = new PooledEngine();
    random = new RandomXS128();
    final SpriteBatch batch = context.getBatch();
    final Viewport viewport = context.getViewport();

    assets.load();

    final Entity player = createPlayer();

    for (int i = 0; i < 6; i++) {
      final Entity entity = engine.createEntity();

      final BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
      boundsComponent.bounds.width = 256;
      boundsComponent.bounds.height = 256;

      final TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
      transformComponent.position.set(random.nextInt(1280), random.nextInt(720));
      transformComponent.scale.set(32, 32);

      final TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
      textureComponent.texture = new TextureRegion(assets.get(Assets.COLLISIONTESTER));

      entity.add(boundsComponent);
      entity.add(transformComponent);
      entity.add(textureComponent);

      engine.addEntity(entity);
    }

    engine.addEntity(player);

    engine.addSystem(new MovementSystem());
    engine.addSystem(new PlayerSystem());
    engine.addSystem(new RenderingSystem(batch, (OrthographicCamera) viewport.getCamera()));
  }

  private void update(final float delta) {
    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      Gdx.app.exit();
    }

    final PlayerSystem playerSystem = engine.getSystem(PlayerSystem.class);

    playerSystem.setVelocity(0, 0);

    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      playerSystem.setVelocityY(1);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      playerSystem.setVelocityY(-1);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      playerSystem.setVelocityX(-1);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      playerSystem.setVelocityX(1);
    }

    engine.update(delta);
  }

  @Override
  public void render(final float delta) {
    update(delta);

  }

  private Entity createPlayer() {
    final Texture img = assets.get(Assets.PLAYER);
    final Entity entity = engine.createEntity();

    final BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
    boundsComponent.bounds.width = PlayerComponent.WIDTH;
    boundsComponent.bounds.height = PlayerComponent.HEIGHT;

    final PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);

    final TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
    transformComponent.position.set(400, 300);

    final TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
    textureComponent.texture = new TextureRegion(img);

    final VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
    velocityComponent.speed = PlayerComponent.SPEED;

    entity.add(boundsComponent);
    entity.add(playerComponent);
    entity.add(transformComponent);
    entity.add(textureComponent);
    entity.add(velocityComponent);

    return entity;
  }
}
