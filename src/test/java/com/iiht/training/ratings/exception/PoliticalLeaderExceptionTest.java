package com.iiht.training.ratings.exception;

import static com.iiht.training.ratings.testutils.TestUtils.currentTest;
import static com.iiht.training.ratings.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.ratings.testutils.TestUtils.testReport;
import static com.iiht.training.ratings.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
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

import com.iiht.training.ratings.controller.PoliticalLeaderController;
import com.iiht.training.ratings.dto.PoliticalLeaderDto;
import com.iiht.training.ratings.exceptions.PoliticalLeaderNotFoundException;
import com.iiht.training.ratings.model.exception.ExceptionResponse;
import com.iiht.training.ratings.service.PoliticalLeaderService;
import com.iiht.training.ratings.testutils.MasterData;

@WebMvcTest(PoliticalLeaderController.class)
@AutoConfigureMockMvc
public class PoliticalLeaderExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PoliticalLeaderService leaderService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalLeaderInvalidDataException() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		PoliticalLeaderDto savedPoliticalLeaderDto = MasterData.getPoliticalLeaderDto();
		savedPoliticalLeaderDto.setPoliticalPartyId(1L);

		politicalLeaderDto.setCandidateName("Ab");

		when(this.leaderService.registerPoliticalLeader(politicalLeaderDto)).thenReturn(savedPoliticalLeaderDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/leaders")
				.content(MasterData.asJsonString(politicalLeaderDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdatePoliticalLeaderInvalidDataException() throws Exception {
		PoliticalLeaderDto politicalLeaderDto = MasterData.getPoliticalLeaderDto();
		PoliticalLeaderDto savedPoliticalLeaderDto = MasterData.getPoliticalLeaderDto();
		savedPoliticalLeaderDto.setPoliticalPartyId(1L);

		politicalLeaderDto.setCandidateName("Ab");

		when(this.leaderService.registerPoliticalLeader(politicalLeaderDto)).thenReturn(savedPoliticalLeaderDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/leaders")
				.content(MasterData.asJsonString(politicalLeaderDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeletePoliticalLeaderNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Leader with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.leaderService.deletePoliticalLeader(2L))
				.thenThrow(new PoliticalLeaderNotFoundException("Political Leader with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/leaders/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetPoliticalLeaderByIdPoliticalLeaderNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Leader with Id - 2 not Found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());

		when(this.leaderService.getPoliticalLeaderById(2L))
				.thenThrow(new PoliticalLeaderNotFoundException("Political Leader with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/leaders/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}


}
