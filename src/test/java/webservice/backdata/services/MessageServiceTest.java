package webservice.backdata.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public void testGetMessageFromJson_shouldReturnAMessage() throws JsonProcessingException, IOException {
		final String json = "{\"mid\":\"123\",\"text\":\"123\",	\"idFrom\":\"456\",	\"idTo\":\"478\"}";
		JsonNode jsonNode = new ObjectMapper().readTree(json);
		Message message =  MessageService.getMessageFromJson(jsonNode);
		assertEquals(message.getMid(),"123");
		assertEquals(message.getText(),"123");
		assertEquals(message.getIdFrom(),"456");
		assertEquals(message.getIdTo(),"478");
		
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
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetMessageByMid_shouldThrowException_whenNull() {
		final String mid = null;
		messageService.getMessageByMid(mid);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetMessageByMid_shouldThrowException_whenEmpty() {
		final String mid = "";
		messageService.getMessageByMid(mid);
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
	@Test
	public void testGetConversation_shouldReturnAListMessage(){
		List<Message> listMessage = new ArrayList<Message>();
		listMessage.add(createMessage());
		when(messagePersistence.getConversation("123")).thenReturn(listMessage);
		messageService.getConversation("123");
		assertTrue(listMessage != null);
		assertTrue(listMessage.size() != 0);
		verify(messagePersistence).getConversation("123");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testGetConversation_shouldThrowException_whenNull() {
		messageService.getConversation(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testGetConversation_shouldThrowException_whenEmpty() {
		messageService.getConversation("");
	}
/*	@Test(expected = IllegalArgumentException.class)
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
	}*/
	

	private Message createMessage() {
		Message message = new Message("123", "Salut!", "942", "1357");
		return message;
	}
}
