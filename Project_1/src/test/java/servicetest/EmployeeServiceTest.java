package servicetest;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.revature.model.Employee;
import com.revature.repository.EmployeeRepository;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {
	

	
	@InjectMocks
	private static EmployeeService employeeService;
	
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	{
		
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		employeeService = new EmployeeService();
	}
	
	@Test
	public void testGetByUsername() {
		
		
		Mockito.when(employeeRepository.getByUsername("u")).thenReturn(
				new Employee(300, "n", "u", "p")
				);
						
		Employee emp = employeeService.getByUsername("u");
		
		Assert.assertEquals(emp.getEmployeeId(), 1);
		Assert.assertEquals(emp.getEmployeeName(), "n");
		Assert.assertEquals(emp.getEmployeeUserName(), "u");
		Assert.assertEquals(emp.getEmployeePassword(), "p");
	}
}
