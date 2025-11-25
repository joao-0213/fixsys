import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Receita } from './receita.model';

// serviço injetável para as chamadas HTTP.

@Injectable({
  providedIn: 'root'
})
export class RelatorioService {
  
private http = inject(HttpClient);
  
// Endpoint do RestController no Spring

private readonly API_URL = 'http://localhost:8080/api/receitas';

  
// Busca receitas no backend, filtrando por data
// O backend recebe via @RequestParam.
   
getReceitas(dataInicial?: string, dataFinal?: string): Observable<Receita[]> {
      let params = new HttpParams();
      
  // no formato que o input type="date" fornece.
  if (dataInicial) {
      params = params.set('inicio', dataInicial);
  }
  if (dataFinal) {
      params = params.set('fim', dataFinal);
  }

  return this.http.get<Receita[]>(this.API_URL, { params });
  }

  
//Busca dados já agrupados por categoria do backend
//Deixar o back fazer o GROUP BY.
    
getReceitasAgrupadasPorCategoria(): Observable<Receita[]> {
    return this.http.get<Receita[]>(`${this.API_URL}/por-categoria`);
  }
}