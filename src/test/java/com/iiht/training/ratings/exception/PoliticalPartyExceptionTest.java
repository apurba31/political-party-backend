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

import com.iiht.training.ratings.controller.PoliticalPartyController;
import com.iiht.training.ratings.dto.PoliticalPartyDto;
import com.iiht.training.ratings.exceptions.PoliticalPartyNotFoundException;
import com.iiht.training.ratings.model.exception.ExceptionResponse;
import com.iiht.training.ratings.service.PoliticalPartyService;
import com.iiht.training.ratings.testutils.MasterData;

@WebMvcTest(PoliticalPartyController.class)
@AutoConfigureMockMvc
public class PoliticalPartyExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PoliticalPartyService partyService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalPartyInvalidDataException() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		PoliticalPartyDto savedPoliticalPartyDto = MasterData.getPartyDto();
		savedPoliticalPartyDto.setPoliticalPartyId(1L);

		politicalPartyDto.setPartyName("Ab");

		when(this.partyService.registerParty(politicalPartyDto)).thenReturn(savedPoliticalPartyDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/parties")
				.content(MasterData.asJsonString(politicalPartyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	
	@Test
	public void testUpdatePoliticalPartyInvalidDataException() throws Exception {
		PoliticalPartyDto politicalPartyDto = MasterData.getPartyDto();
		PoliticalPartyDto savedPoliticalPartyDto = MasterData.getPartyDto();
		savedPoliticalPartyDto.setPoliticalPartyId(1L);

		politicalPartyDto.setPartyName("Ab");

		when(this.partyService.registerParty(politicalPartyDto)).thenReturn(savedPoliticalPartyDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/parties")
				.content(MasterData.asJsonString(politicalPartyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}


	@Test
	public void testDeletePoliticalPartyNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Party with Id - 2 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.partyService.deleteParty(2L)).thenThrow(new PoliticalPartyNotFoundException("Political Party with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/parties/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
	@Test
	public void testGetByIdPoliticalPartyNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Political Party with Id - 2 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());
		
		when(this.partyService.getPartyById(2L)).thenThrow(new PoliticalPartyNotFoundException("Political Party with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/parties/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}

}
