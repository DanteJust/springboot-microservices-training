package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorGetAllServiceApplicationTests {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private DoctorRepository doctorRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testFindAllDoctorsByHospitalId() throws Exception {
		Doctor doctor1 = new Doctor(1L, "Dave Smith", "Cardiology", 1);
		Doctor doctor2 = new Doctor(2L, "Alan Lamb", "Neurologist", 1);
		
		List<Doctor> doctors = Arrays.asList(doctor1, doctor2);
		
		when(doctorRepository.findByHospitalId(1)).thenReturn(doctors);
		
		mockmvc.perform(get("/doctors/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].doctorName", is("Dave Smith")))
			.andExpect(jsonPath("$[1].doctorName", is("Alan Lamb")));
	}
	
}
