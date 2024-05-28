package com.mygdx.vampiresurvivors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

  public static final String PLAYER = "idle_0.png";
  public static final String COLLISIONTESTER = "tile_0084.png";

  private final AssetManager assetManager;

  public Assets(final AssetManager assetManager) {
    this.assetManager = assetManager;
  }

  public void load() {
    assetManager.load(PLAYER, Texture.class);
    assetManager.load(COLLISIONTESTER, Texture.class);
    assetManager.finishLoading();
  }

  public Texture get(final String name) {
    return assetManager.get(name, Texture.class);
  }
}
