package com.mygdx.vampiresurvivors.ecs.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.vampiresurvivors.ecs.Mappers;
import com.mygdx.vampiresurvivors.ecs.component.PositionComponent;
import com.mygdx.vampiresurvivors.ecs.component.TextureComponent;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

  private final SpriteBatch batch;
  private final OrthographicCamera camera;
  private final Array<Entity> renderQueue;

  public RenderingSystem(final SpriteBatch batch, final OrthographicCamera camera) {
    super(Family.all(PositionComponent.class, TextureComponent.class).get(), new ZComparator());

    renderQueue = new Array<>();

    this.batch = batch;
    this.camera = camera;
  }

  @Override
  public void update(final float deltaTime) {
    super.update(deltaTime);

    camera.update();
    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    for (final Entity entity : new Array.ArrayIterable<>(renderQueue)) {
      final PositionComponent positionComponent = Mappers.position.get(entity);
      final TextureComponent textureComponent = Mappers.texture.get(entity);

      // Draw the texture scaled down by 10
      batch.draw(textureComponent.texture,
          positionComponent.position.x, positionComponent.position.y,
          0, 0,
          textureComponent.texture.getWidth(), textureComponent.texture.getHeight(),
          1/16f, 1/16f,
          0,
          0, 0,
          textureComponent.texture.getWidth(), textureComponent.texture.getHeight(),
          false, false
      );
    }

    batch.end();
    renderQueue.clear();
  }

  @Override
  public void processEntity(final Entity entity, final float deltaTime) {
    renderQueue.add(entity);
  }

  private static class ZComparator implements Comparator<Entity> {
    private static final ComponentMapper<TextureComponent> textureComponentMapper = ComponentMapper.getFor(TextureComponent.class);

    @Override
    public int compare(final Entity entity1, final Entity entity2) {
      final TextureComponent textureComponent1 = textureComponentMapper.get(entity1);
      final TextureComponent textureComponent2 = textureComponentMapper.get(entity2);

      return Integer.compare(textureComponent1.zIndex, textureComponent2.zIndex);
    }
  }
}
