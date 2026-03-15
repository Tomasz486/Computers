import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ComputerDto } from '../models/computer.model';
import { PageResponse } from '../models/page-response.model';

@Injectable({
  providedIn: 'root'
})
export class ComputerService {
  private readonly apiUrl = 'http://localhost:8080/api/computers';

  constructor(private http: HttpClient) {}

  getComputers(
    page: number,
    size: number,
    sortField: string,
    sortDirection: string,
    name?: string,
    accountingDate?: string
  ): Observable<PageResponse<ComputerDto>> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', `${sortField},${sortDirection}`);

    if (name && name.trim() !== '') {
      params = params.set('name', name);
    }

    if (accountingDate && accountingDate.trim() !== '') {
      params = params.set('accountingDate', accountingDate);
    }

    return this.http.get<PageResponse<ComputerDto>>(this.apiUrl, { params });
  }
}
