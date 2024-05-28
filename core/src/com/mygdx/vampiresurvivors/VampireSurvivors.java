package com.mygdx.vampiresurvivors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.vampiresurvivors.screens.GameScreen;

public class VampireSurvivors extends Game {

  private static final float VIRTUAL_WIDTH = 1280;
  private static final float VIRTUAL_HEIGHT = 720;

  private Assets assets;
  private OrthographicCamera camera;
  private SpriteBatch batch;
  private Viewport viewport;

  @Override
  public void create() {
    assets = new Assets(new AssetManager());
    batch = new SpriteBatch();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
    viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
    viewport.apply();

    setScreen(new GameScreen(this));
  }

  @Override
  public void render() {
    ScreenUtils.clear(0, 1, 0, 1);

    super.render();
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  @Override
  public void resize(final int width, final int height) {
    viewport.update(width, height);
  }

  public Assets getAssetManager() {
    return assets;
  }

  public SpriteBatch getBatch() {
    return batch;
  }

  public Viewport getViewport() {
    return viewport;
  }
}
