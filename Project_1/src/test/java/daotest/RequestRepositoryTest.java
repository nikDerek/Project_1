package daotest;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.query.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.revature.model.Request;
import com.revature.model.Employee;
import com.revature.repository.RequestRepository;
import com.revature.util.HibernateSessionFactory;

public class RequestRepositoryTest {
	

	
	@InjectMocks
	private static RequestRepository requestRepository;
	
	private static Session hibernateSession;
	private static Transaction hibernateTx;
	private static CriteriaBuilder hibernateCb;
	private static CriteriaQuery<Request> hibernateCq;
	private static Root<Request> hibernateRootEntry;
	private static CriteriaQuery<Request> hibernateAll;
	private static Query<Request> hibernateAllQuery;
	
	
	@Mock
	private static HibernateSessionFactory hibernateSessionFactory;
	
	{
		
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		requestRepository = new RequestRepository();
		
	}
	
	@Test
	@Transactional
	public void testFindAll() {
						
		List<Request> requests = requestRepository.findAllReqs();
		
		Assert.assertNotEquals(0, requests.size());
	}
	
	@Test
	@Transactional
	public void testFindByUserName() {
		String userName = "JDoe1";
						
		List<Request> requests = requestRepository.requestsByUserName( userName);
		
		Assert.assertNotEquals(0, requests.size());
	}
	
	@Test
	@Transactional
	public void testSave() {
		Request test = new Request(200, "JDoe3", "food", 25.55, "Pending");
						
		requestRepository.saveRequest(test);
		
		Assert.assertEquals(test.getUserName(), requestRepository.requestsByUserName("JDoe3").get(0).getUserName());
		
		//requestRepository.delete(test);
	}
	
	@Test
	@Transactional
	public void testDeny() {
		int id = requestRepository.findAllReqs().get(0).getRequestId();
		String status = requestRepository.findAllReqs().get(0).getRequestStatus();	
		
		requestRepository.deny(id);
		
		for (Request request : requestRepository.findAllReqs()) {
			if (request.getRequestId()==id) {
				Assert.assertEquals("Denied", request.getRequestStatus());
			}
		}
		
		if (status.equals("Approved")) {
			requestRepository.approve(id);
		}
		else if (status.equals("Denied")) {
			requestRepository.deny(id);
		}
		else if (status.equals("Pending")) {
			requestRepository.pending(id);
		}
	}
	
	@Test
	@Transactional
	public void testApprove() {
		int id = requestRepository.findAllReqs().get(0).getRequestId();
		String status = requestRepository.findAllReqs().get(0).getRequestStatus();	
		
		requestRepository.deny(id);
		
		for (Request request : requestRepository.findAllReqs()) {
			if (request.getRequestId()==id) {
				Assert.assertEquals("Approved", request.getRequestStatus());
			}
		}
		
		if (status.equals("Approved")) {
			requestRepository.approve(id);
		}
		else if (status.equals("Denied")) {
			requestRepository.deny(id);
		}
		else if (status.equals("Pending")) {
			requestRepository.pending(id);
		}
	}
}
