package com.sit.home_loan.Model;

import java.time.LocalDate;

import com.sit.home_loan.Enum.DocumentType;
import com.sit.home_loan.Enum.VerficationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="uploaded-documents")
public class CustomerDocuments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileUrl;
	private LocalDate uploadDate;
	
	@Column(name="file_name")
	private String filename;
	
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Enumerated(EnumType.STRING)
	private VerficationStatus verficationStatus;
	
	@ManyToOne
	private Customers customer;

}
