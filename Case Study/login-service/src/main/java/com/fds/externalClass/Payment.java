package com.fds.externalClass;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	private long transactionId;
	private int orderId;
	private Date paymentDate;
	private String username;
	//private String paymentLocation;
	private double amount;
	private String transactionStatus;
}
