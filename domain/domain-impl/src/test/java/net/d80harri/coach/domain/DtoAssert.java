package net.d80harri.coach.domain;

import java.util.UUID;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

public abstract class DtoAssert<M extends DtoAssert<M, T>, T extends Dto> extends AbstractObjectAssert<M, T> {

	protected DtoAssert(T actual, Class<? extends M> selfType) {
		super(actual, selfType);
	}

	public M hasId(UUID uuid) {
		Assertions.assertThat(actual.getUuid()).isEqualTo(uuid);
		return (M) this.myself;
	}

}
