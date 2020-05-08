import { Report } from '../model/report.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CreateReportService {
  private report = `${environment.commandApi}/reports/`;

  constructor(private httpClient: HttpClient) {}

  createReport(userName: string) {
    const params = new HttpParams().set('user', userName);
    return this.httpClient.post<Report>(this.report, null, { params });
  }
}
