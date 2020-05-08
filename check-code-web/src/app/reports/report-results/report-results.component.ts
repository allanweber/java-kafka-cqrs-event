import { ReportRepository } from './../model/report-repository.model';
import { ReportService } from '../service/report.service';
import { FeedbackMessageService } from '../../shared/service/feedback-message.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'check-code-report-results',
  templateUrl: './report-results.component.html',
  styleUrls: ['./report-results.component.sass'],
})
export class ReportResultsComponent implements OnInit, OnDestroy {
  private routeSubscription: Subscription;
  private serviceSubscription: Subscription;

  public user: string;
  public result: ReportRepository[];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private messageService: FeedbackMessageService,
    private reportService: ReportService
  ) {}

  ngOnInit(): void {
    this.routeSubscription = this.activatedRoute.params.subscribe((params) => {
      if (params.id) {
        this.user = params.id;
        this.serviceSubscription = this.reportService
          .getRepositoriesReport(this.user)
          .subscribe((response) => (this.result = response));
      } else {
        this.messageService.showWarningMessage('Invalid user');
        this.router.navigate(['/home']);
      }
    });
  }

  showNotification() {
    if (this.result) {
      return this.result.length === 0;
    }
  }

  ngOnDestroy(): void {
    if (this.routeSubscription) {
      this.routeSubscription.unsubscribe();
    }
    if (this.serviceSubscription) {
      this.serviceSubscription.unsubscribe();
    }
  }
}
