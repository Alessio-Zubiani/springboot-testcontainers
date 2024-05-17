package com.example.activemq.service;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String content;
	private Date date;
	
}
