package com.web2.manutencaoBackend.service;

import com.web2.manutencaoBackend.dto.ReceitaDTO;

import com.web2.manutencaoBackend.repository.ReceitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<ReceitaDTO> obterReceitaPorCategoria() {
        return receitaRepository.findReceitaPorCategoria();
    }

    public List<ReceitaDTO> obterReceitaDiaria(LocalDate inicio, LocalDate fim) {
        // Converte LocalDate para LocalDateTime para cobrir o dia inteiro
        if (inicio == null) {
            inicio = LocalDate.of(2000, 1, 1); 
        }
        if (fim == null) {
            fim = LocalDate.now();
        }
        LocalDateTime dataInicio = inicio.atStartOfDay(); // 00:00:00
        LocalDateTime dataFim = fim.atTime(LocalTime.MAX); // 23:59:59.999

        return receitaRepository.findReceitaDiaria(dataInicio, dataFim);
    }
}