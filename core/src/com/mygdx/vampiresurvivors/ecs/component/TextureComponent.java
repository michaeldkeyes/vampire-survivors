package com.mygdx.vampiresurvivors.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent implements Component, Pool.Poolable {

  public Texture texture;
  public int zIndex;

  public TextureComponent() {
  }

  @Override
  public void reset() {
    texture = null;
    zIndex = 0;
  }
}
