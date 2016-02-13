package net.d80harri.coach.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import net.d80harri.coach.domain.exception.DuplicateIdException;

public class AbstractDaoTest {

	@Test
	public void testGetById() {
		DogEntity theDog = new DogEntity(UUID.randomUUID());
		ElephantEntity theElephant = new ElephantEntity(UUID.randomUUID());

		Collection<Entity> ds = Arrays.asList(theDog, theElephant, new DogEntity(UUID.randomUUID()),
				new DogEntity(UUID.randomUUID()));
		AnimalDao dao = new AnimalDao(ds);

		Assertions.assertThat(dao.getById(theDog.getUuid(), DogEntity.class)).isPresent().containsSame(theDog);
		Assertions.assertThat(dao.getById(theDog.getUuid(), AnimalEntity.class)).isPresent().containsSame(theDog);
		Assertions.assertThat(dao.getById(theDog.getUuid(), ElephantEntity.class)).isEmpty();

		Assertions.assertThat(dao.getById(theElephant.getUuid(), ElephantEntity.class)).isPresent()
				.containsSame(theElephant);
		Assertions.assertThat(dao.getById(theElephant.getUuid(), AnimalEntity.class)).isPresent()
				.containsSame(theElephant);
		Assertions.assertThat(dao.getById(theElephant.getUuid(), DogEntity.class)).isEmpty();

		Assertions.assertThat(dao.getById(UUID.randomUUID(), DogEntity.class)).isEmpty();
	}

	@Test
	public void testPut() {
		Collection<Entity> ds = new ArrayList<>();
		AnimalDao dao = new AnimalDao(ds);

		DogEntity testEntity = new DogEntity(UUID.randomUUID());
		DogEntity result = dao.put(testEntity);

		Assertions.assertThat(result).isSameAs(testEntity);
		Assertions.assertThat(ds).hasSize(1);
	}

	@Test
	public void testGetAll() {
		Collection<Entity> ds = Arrays.asList(new DogEntity(UUID.randomUUID()),
				new ElephantEntity(UUID.randomUUID()),
				new DogEntity(UUID.randomUUID()),
				new DogEntity(UUID.randomUUID()));
		AnimalDao dao = new AnimalDao(ds);

		Assertions.assertThat(dao.getAll(AnimalEntity.class)).hasSize(4);
		Assertions.assertThat(dao.getAll(DogEntity.class)).hasSize(3);
		Assertions.assertThat(dao.getAll(ElephantEntity.class)).hasSize(1);
	}

	@Test
	public void testGetByIdWhenMoreThanOneMatch() {
		UUID uuid = UUID.randomUUID();
		Collection<Entity> ds = Arrays.asList(new DogEntity(uuid),
				new DogEntity(uuid));
		AnimalDao dao = new AnimalDao(ds);

		Assertions.assertThatThrownBy(() -> dao.getById(uuid, AnimalEntity.class))
				.isInstanceOf(DuplicateIdException.class)
				.hasMessage(String.format("More than one entity with ID %s found.", uuid));
	}

	@Test
	public void testPutWhenEntityAlreadyExists() {
		DogEntity theDog = new DogEntity(UUID.randomUUID());

		Collection<Entity> ds = Arrays.asList(theDog);
		AnimalDao dao = new AnimalDao(ds);

		Assertions.assertThatThrownBy(() -> dao.put(theDog)).isInstanceOf(RuntimeException.class).hasMessage("NYI");
	}

	private static abstract class AnimalEntity extends Entity {
		public AnimalEntity(UUID uuid) {
			super(uuid);
		}
	}

	private static final class DogEntity extends AnimalEntity {

		public DogEntity(UUID uuid) {
			super(uuid);
		}

	}

	private static final class ElephantEntity extends AnimalEntity {

		public ElephantEntity(UUID uuid) {
			super(uuid);
		}
	}

	private static final class AnimalDao extends AbstractDao<AnimalEntity> {
		public AnimalDao(Collection<Entity> ds) {
			super(ds);
		}
	}
}
