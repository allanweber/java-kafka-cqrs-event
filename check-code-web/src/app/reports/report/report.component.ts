import { Report } from './../model/report.model';
import { Subscription } from 'rxjs';
import { ReportService } from '../service/report.service';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'check-code-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.sass'],
})
export class ReportComponent implements OnInit, OnDestroy {
  public reports: Report[] = [];

  private serviceSubscription: Subscription;

  constructor(private service: ReportService) {}

  ngOnInit(): void {
    this.serviceSubscription = this.service
      .getAllReports()
      .subscribe((response) => (this.reports = response));
  }

  ngOnDestroy(): void {
    if (this.serviceSubscription) {
      this.serviceSubscription.unsubscribe();
    }
  }

  showNotification() {
    return this.reports.length === 0;
  }
}
