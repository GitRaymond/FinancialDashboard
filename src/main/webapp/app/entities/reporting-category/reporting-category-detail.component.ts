import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReportingCategory } from 'app/shared/model/reporting-category.model';

@Component({
    selector: 'jhi-reporting-category-detail',
    templateUrl: './reporting-category-detail.component.html'
})
export class ReportingCategoryDetailComponent implements OnInit {
    reportingCategory: IReportingCategory;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reportingCategory }) => {
            this.reportingCategory = reportingCategory;
        });
    }

    previousState() {
        window.history.back();
    }
}
