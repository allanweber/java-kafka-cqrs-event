import { Report } from './../model/report.model';
import { ReportRepository } from './../model/report-repository.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  private reportsUrl = `${environment.queryApi}/github/reports`;

  constructor(private httpClient: HttpClient) {}

  getRepositoriesReport(userName: string) {
    const url = `${this.reportsUrl}/${userName}/repositories`;
    return this.httpClient.get<ReportRepository[]>(url);
  }

  getAllReports() {
    return this.httpClient.get<Report[]>(this.reportsUrl);
  }
}
