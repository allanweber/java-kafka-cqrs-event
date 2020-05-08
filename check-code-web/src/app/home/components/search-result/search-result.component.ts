import { Subscription } from 'rxjs';
import { FeedbackMessageService } from './../../../shared/service/feedback-message.service';
import { Router } from '@angular/router';
import { CreateReportService } from './../../../reports/service/create-report.service';
import { UserModel } from './../../model/user.model';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';

@Component({
  selector: 'check-code-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.sass'],
})
export class SearchResultComponent implements OnInit, OnDestroy {
  @Input() users: UserModel[];
  private subscription: Subscription;

  constructor(
    private createReportService: CreateReportService,
    private router: Router,
    private messageService: FeedbackMessageService
  ) {}

  ngOnInit(): void {}

  createReport(user: UserModel) {
    this.subscription = this.createReportService.createReport(user).subscribe(
      (response) => {
        this.messageService.showSuccessMessage(
          `Report for ${response.user} started.`
        );
        this.router.navigate(['reports', response.user, 'results']);
      },
      () => {
        this.router.navigate(['/home']);
      }
    );
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
