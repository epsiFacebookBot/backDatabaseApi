package webservice.backdata.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import webservice.backdata.bean.Message;
import webservice.backdata.persistence.MessagePersistence;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {
	@InjectMocks
	MessageService messageService;
	@Mock
	MessagePersistence messagePersistence;
	@Mock
	private HttpClient client;

	@PostConstruct
	private void init() {

		client = new HttpClient();
		client.getParams().setParameter("http.useragent", "Test Client");

	}

	@Test
	public void testGetMessageByMid_shouldReturnAMessage() {
		final String mid = "123";
		Message message = createMessage();
		when(messagePersistence.getMessageByMid("123")).thenReturn(message);
		Message reponse = messageService.getMessageByMid(mid);
		assertEquals(reponse.getMid(), message.getMid());
		verify(messagePersistence).getMessageByMid(mid);
	}

	@Test
	public void testSaveMessage_shouldSaveMessage_whenMessageOk() throws HttpException, IOException {
		Message message = createMessage();
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenMessageNull() throws HttpException, IOException {
		Message message = null;
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenMidNull() throws HttpException, IOException {
		Message message = createMessage();
		message.setMid(null);
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenMidEmpty() throws HttpException, IOException {
		Message message = createMessage();
		message.setMid("");
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenTextNull() throws HttpException, IOException {
		Message message = createMessage();
		message.setText(null);
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenTextEmpty() throws HttpException, IOException {
		Message message = createMessage();
		message.setText("");
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenIdFromNull() throws HttpException, IOException {
		Message message = createMessage();
		message.setIdFrom(null);
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenIdFromEmpty() throws HttpException, IOException {
		Message message = createMessage();
		message.setIdFrom("");
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenIdToNull() throws HttpException, IOException {
		Message message = createMessage();
		message.setIdTo(null);
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveMessage_shouldNotSaveMessage_whenIdToEmpty() throws HttpException, IOException {
		Message message = createMessage();
		message.setIdTo("");
		when(messagePersistence.saveMessage(message)).thenReturn(true);
		boolean reponse = messageService.saveMessage(message);
		assertEquals(reponse, true);
		verify(messagePersistence, times(0)).saveMessage(message);
	}

	private Message createMessage() {
		Message message = new Message("123", "Salut!", "942", "1357");
		return message;
	}
}
