package servicetest;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.revature.model.Request;
import com.revature.repository.RequestRepository;
import com.revature.service.RequestService;

public class RequestServiceTest {
	

	
	@InjectMocks
	private static RequestService requestService;
	
	
	@Mock
	private RequestRepository requestRepository;
	
	{
		
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		requestService = new RequestService();
	}
	
	
	@Test
	public void testFindAll() {
		
		
		Mockito.when(requestRepository.findAllReqs()).thenReturn(
				Arrays.asList(
						new Request(500, "JDoe2", "food", 15.00, "Pending"),
						new Request(501, "JDoe3", "food", 15.11, "Pending"),
						new Request(502, "JDoe4", "food", 15.22, "Pending"),
						new Request(503, "JDoe5", "food", 15.33, "Pending")
						)
				);
						
		List<Request> requests = requestService.findAllReqs();
		
		Assert.assertEquals("The list size should be 4", 4, requests.size());
	}
	
	@Test
	public void testFindName() {
		
		
		Mockito.when(requestRepository.requestsByUserName("test")).thenReturn(
				Arrays.asList(
						new Request(562, "JDoe6", "food", 15.66, "Pending")
						)
				);
						
		List<Request> requests = requestService.requestsByUserName("test");
		
		Assert.assertEquals("The list size should be 1", 1, requests.size());
	}
	
	@Test
	public void testSave() {
		
		
	
		Request test = new Request(545, "JDoe800", "food", 15.99, "Pending");
		
		Mockito.doNothing().when(requestRepository).saveRequest(test);
		
		requestService.save(test);
		
		Mockito.verify(requestRepository,times(1)).saveRequest(test);
	}
	
	@Test
	public void testApprove() {
		
	
		int test = 1;
		
		Mockito.doNothing().when(requestRepository).approve(test);
		
		requestService.approve(test);
		
		Mockito.verify(requestRepository,times(1)).approve(test);
	}
	
	@Test
	public void testDeny() {
		
		int test = 1;
		
		Mockito.doNothing().when(requestRepository).deny(test);
		
		requestService.deny(test);
		
		Mockito.verify(requestRepository,times(1)).deny(test);
	}
}
