import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IReportingCategory } from 'app/shared/model/reporting-category.model';
import { ReportingCategoryService } from './reporting-category.service';

@Component({
    selector: 'jhi-reporting-category-update',
    templateUrl: './reporting-category-update.component.html'
})
export class ReportingCategoryUpdateComponent implements OnInit {
    private _reportingCategory: IReportingCategory;
    isSaving: boolean;

    constructor(private reportingCategoryService: ReportingCategoryService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ reportingCategory }) => {
            this.reportingCategory = reportingCategory;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.reportingCategory.id !== undefined) {
            this.subscribeToSaveResponse(this.reportingCategoryService.update(this.reportingCategory));
        } else {
            this.subscribeToSaveResponse(this.reportingCategoryService.create(this.reportingCategory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReportingCategory>>) {
        result.subscribe((res: HttpResponse<IReportingCategory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get reportingCategory() {
        return this._reportingCategory;
    }

    set reportingCategory(reportingCategory: IReportingCategory) {
        this._reportingCategory = reportingCategory;
    }
}
