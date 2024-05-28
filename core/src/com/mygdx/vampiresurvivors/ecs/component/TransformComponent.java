package com.mygdx.vampiresurvivors.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class TransformComponent implements Component, Pool.Poolable {

  public final Vector2 position = new Vector2();
  public final Vector2 scale = new Vector2(1, 1);
  public float rotation = 0;

  @Override
  public void reset() {
    position.set(0, 0);
    scale.set(1, 1);
    rotation = 0;
  }
}
