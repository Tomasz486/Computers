package com.project.computers.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.project.computers.service.ComputerService;

@WebMvcTest(ComputerController.class)
class ComputerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ComputerService computerService;

	@Test
	void shouldReturnOkStatus() throws Exception {
		when(computerService.GetComputers(any(), any(), any(Pageable.class))).thenReturn(Page.empty());

		mockMvc.perform(get("/computers").param("name", "Dell").param("accountingDate", "2024-03-14"))
				.andExpect(status().isOk());
	}

	@Test
	void shouldPassParamsToService() throws Exception {
		String name = "HP";
		LocalDate date = LocalDate.of(2024, 1, 1);
		when(computerService.GetComputers(eq(name), eq(date), any(Pageable.class))).thenReturn(Page.empty());

		mockMvc.perform(get("/computers").param("name", name).param("accountingDate", "2024-01-01"))
				.andExpect(status().isOk());
	}
}
