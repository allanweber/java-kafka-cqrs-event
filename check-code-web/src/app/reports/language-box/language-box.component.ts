import { Language } from './../model/language.model';
import { Component, OnInit, Input } from '@angular/core';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';

@Component({
  selector: 'check-code-language-box',
  templateUrl: './language-box.component.html',
  styleUrls: ['./language-box.component.sass'],
})
export class LanguageBoxComponent implements OnInit {
  @Input() languages: Language[] = [];

  public pieChartPlugins = [pluginDataLabels];
  public barChartOptions = {
    responsive: true,
    legend: {
      position: 'left',
    },
    plugins: {
      datalabels: {
        formatter: (value, ctx) => {
          const label = ctx.chart.data.labels[ctx.dataIndex];
          return label;
        },
      },
    }
  };
  public barChartLabels = [];
  public barChartType = 'pie';
  public barChartLegend = true;
  public barChartData = [{ data: [], label: 'Series A' }];

  constructor() {}

  ngOnInit(): void {
    this.languages.forEach((lang) => {
      this.barChartLabels.push(lang.name);
      this.barChartData[0].data.push(lang.percentage);
    });
  }
}
