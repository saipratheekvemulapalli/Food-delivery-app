package com.fds.payment.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {

	@Id
	private long transactionId;
	private int orderId;
	private Date paymentDate;
	private String username;
	//private String paymentLocation;
	private double amount;
	private String transactionStatus;
}

