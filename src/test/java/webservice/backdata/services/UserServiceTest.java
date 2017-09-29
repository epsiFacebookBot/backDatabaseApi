package webservice.backdata.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import webservice.backdata.bean.User;
import webservice.backdata.persistence.UserPersistence;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;
	@Mock
	UserPersistence userPersistence;

	@Test
	public void testGetUserByMid_shouldReturnAnUser() {
		final String id = "123";
		User user = createUser();			
		when(userPersistence.getUserById("123")).thenReturn(user);
		User reponse = userService.getUserById(id);
		assertEquals(reponse.getId(), user.getId());
		verify(userPersistence).getUserById(id);
	}

	@Test
	public void testGetUserByMid_shouldSaveUser() {
		User user = createUser();
		when(userPersistence.saveUser(user)).thenReturn(true);
		boolean reponse = userService.saveUser(user);
		assertEquals(reponse, true);
		verify(userPersistence).saveUser(user);
	}

	private User createUser() {
		User user = new User("123", "TestName");
		return user;
	}

}
