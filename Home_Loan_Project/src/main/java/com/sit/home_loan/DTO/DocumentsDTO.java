package com.sit.home_loan.DTO;

import java.time.LocalDate;

import com.sit.home_loan.Enum.DocumentType;
import com.sit.home_loan.Enum.VerficationStatus;

import lombok.Data;

@Data
public class DocumentsDTO {
	private Long id;
    private String fileUrl;
    private LocalDate uploadDate;
    private String filename;
    private DocumentType documentType;
    private VerficationStatus verficationStatus;

}
