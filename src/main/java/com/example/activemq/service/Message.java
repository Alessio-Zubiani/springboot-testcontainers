package com.example.activemq.service;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder

@Data
@AllArgsConstructor
public class Message {

	private Long id;
	private String content;
	private Date date;
	
}
