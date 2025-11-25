package com.web2.manutencaoBackend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReceitaDTO {
    
    String getNomeCategoria();
    
    LocalDate getData();
    
    BigDecimal getTotal();
}