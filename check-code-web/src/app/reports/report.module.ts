import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReportResultsComponent } from './report-results/report-results.component';
import { ReportRoutingModule } from './report.routing';
import { ReportComponent } from './report/report.component';
import { LanguageBoxComponent } from './language-box/language-box.component';
import { ChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    ReportComponent,
    ReportResultsComponent,
    LanguageBoxComponent,
  ],
  imports: [CommonModule, ReportRoutingModule, ChartsModule],
})
export class StatementModule {}
