package com.web2.manutencaoBackend.controller;

import com.web2.manutencaoBackend.dto.ReceitaDTO;
import com.web2.manutencaoBackend.service.ReceitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")

public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/por-categoria")
    public ResponseEntity<List<ReceitaDTO>> getReceitaPorCategoria() {
        return ResponseEntity.ok(receitaService.obterReceitaPorCategoria());
    }

    @GetMapping()
    public ResponseEntity<List<ReceitaDTO>> getReceitaDiaria(
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
    ) {
        // Exemplo de chamada: /api/dashboard/receita-diaria?inicio=2025-01-01&fim=2025-01-31
        return ResponseEntity.ok(receitaService.obterReceitaDiaria(inicio, fim));
    }
}
