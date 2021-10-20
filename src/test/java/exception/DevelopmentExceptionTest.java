package exception;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.DevelopmentController;
import com.example.demo.dto.DevelopmentDto;
import com.example.demo.exceptions.PoliticalLeaderNotFoundException;
import com.example.demo.exceptions.model.ExceptionResponse;
import com.example.demo.service.DevelopmentService;

@WebMvcTest(DevelopmentController.class)
@AutoConfigureMockMvc
public class DevelopmentExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DevelopmentService developmentService;

	@AfterAll
	public static void afterAll() {
//		testReport();
	}

	@Test
	public void testCreateDevelopmentInvalidDataException() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);

		developmentDto.setActivity("Ab");

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/developments")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateDevelopmentInvalidDataException() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);

		developmentDto.setActivity("Ab");

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/developments")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteDevelopmentNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Development with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.developmentService.deleteDevelopment(2L))
				.thenThrow(new PoliticalLeaderNotFoundException("Development with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/developments/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	@Test
	public void testGetDevelopmentByIdDevelopmentNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Development with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.developmentService.getDevelopmentById(2L))
		.thenThrow(new PoliticalLeaderNotFoundException("Development with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/developments/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}

	

}
