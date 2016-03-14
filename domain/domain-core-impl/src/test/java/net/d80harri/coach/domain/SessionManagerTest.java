package net.d80harri.coach.domain;

import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import net.d80harri.coach.domain.SessionHolder;
import net.d80harri.coach.domain.SessionManager;

public class SessionManagerTest {
	private SessionManager target;
	private SessionFactory mockedSessionFactory;
	
	@Before
	public void init() {
		mockedSessionFactory = Mockito.mock(SessionFactory.class);
		target = new SessionManager(mockedSessionFactory);
	}
	
	@Test
	public void testGetOrCreateSession() {
		SessionHolder holder = target.getOrCreateSession();
		
		Assertions.assertThat(target.sessions.get()).hasSize(1);
		Assertions.assertThat(holder).isNotNull();
		Assertions.assertThat(holder.getAttachedCount()).isEqualTo(1);
	}
	
	@Test
	public void testGetOrCreateMultipleSessions() {
		SessionHolder holder1 = target.getOrCreateSession();
		SessionHolder holder2 = target.getOrCreateSession();
		
		Assertions.assertThat(target.sessions.get()).hasSize(1);
		Assertions.assertThat(holder1).isNotNull();
		Assertions.assertThat(holder2).isSameAs(holder1);
		Assertions.assertThat(holder1.getAttachedCount()).isEqualTo(2);
	}
}
