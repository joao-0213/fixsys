package com.web2.manutencaoBackend.repository;
import com.web2.manutencaoBackend.dto.ReceitaDTO;

import com.web2.manutencaoBackend.entity.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Pagamento, Long> {

    // Receita por Categoria
    @Query(value = """
        SELECT 
            c.nome AS nomeCategoria, 
            SUM(o.valor) AS total
        FROM servicos s
        JOIN categoriae c ON c.id = s.categoria_id
        JOIN orcamento o ON s.orcamento_id = o.id
        WHERE o.id IN (SELECT DISTINCT orcamento_id FROM pagamento)
        GROUP BY c.nome
        ORDER BY total DESC
    """, nativeQuery = true)
    List<ReceitaDTO> findReceitaPorCategoria();

    // Receita Diária 
    @Query(value = """
        SELECT 
            DATE(p.data_hora_pagamento) AS data, 
            SUM(p.valor_pago) AS total
        FROM pagamento p
        WHERE p.data_hora_pagamento >= :dataInicio 
          AND p.data_hora_pagamento <= :dataFim
        GROUP BY DATE(p.data_hora_pagamento)
        ORDER BY data ASC
    """, nativeQuery = true)
    List<ReceitaDTO> findReceitaDiaria(
        @Param("dataInicio") LocalDateTime dataInicio, 
        @Param("dataFim") LocalDateTime dataFim
    );
}
