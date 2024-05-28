package com.mygdx.vampiresurvivors.ecs;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.vampiresurvivors.ecs.component.BoundsComponent;
import com.mygdx.vampiresurvivors.ecs.component.TransformComponent;
import com.mygdx.vampiresurvivors.ecs.component.TextureComponent;
import com.mygdx.vampiresurvivors.ecs.component.VelocityComponent;

public class Mappers {

  public static final ComponentMapper<BoundsComponent> bounds = ComponentMapper.getFor(BoundsComponent.class);
  public static final ComponentMapper<TransformComponent> position = ComponentMapper.getFor(TransformComponent.class);
  public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
  public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);

  private Mappers() {
  }
}
