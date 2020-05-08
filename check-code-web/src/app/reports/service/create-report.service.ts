import { UserModel } from './../../home/model/user.model';
import { Report } from '../model/report.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CreateReportService {
  private report = `${environment.commandApi}/reports`;

  constructor(private httpClient: HttpClient) {}

  createReport(user: UserModel) {
    return this.httpClient.post<Report>(this.report, user);
  }
}
