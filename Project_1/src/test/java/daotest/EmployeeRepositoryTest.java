package daotest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.repository.EmployeeRepository;
import com.revature.util.HibernateSessionFactory;


public class EmployeeRepositoryTest {
	

	
	@InjectMocks
	private static EmployeeRepository employeeRepository;
	
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
		employeeRepository = new EmployeeRepository();
		
	}
	
	
	
	@Test
	@Transactional
	public void testSaveEmployee() {
		Employee employee = new Employee(185, "testEmployee", "user1", "pass1" );
						
		//employee = employeeRepository.save(employee);
		
		Assert.assertEquals("pass1", employee.getEmployeePassword());
	}
}
