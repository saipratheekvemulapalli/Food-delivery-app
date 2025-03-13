package com.fds.payment.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	private LocalDate timestamp;
	private String message;
	private String details;
	private String httpCodeMessage;
	

}
