import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBudget } from 'app/shared/model/budget.model';
import { BudgetService } from './budget.service';
import { IReportingCategory } from 'app/shared/model/reporting-category.model';
import { ReportingCategoryService } from 'app/entities/reporting-category';

@Component({
    selector: 'jhi-budget-update',
    templateUrl: './budget-update.component.html'
})
export class BudgetUpdateComponent implements OnInit {
    private _budget: IBudget;
    isSaving: boolean;

    reportingcategories: IReportingCategory[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private budgetService: BudgetService,
        private reportingCategoryService: ReportingCategoryService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ budget }) => {
            this.budget = budget;
        });
        this.reportingCategoryService.query().subscribe(
            (res: HttpResponse<IReportingCategory[]>) => {
                this.reportingcategories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.budget.id !== undefined) {
            this.subscribeToSaveResponse(this.budgetService.update(this.budget));
        } else {
            this.subscribeToSaveResponse(this.budgetService.create(this.budget));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBudget>>) {
        result.subscribe((res: HttpResponse<IBudget>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackReportingCategoryById(index: number, item: IReportingCategory) {
        return item.id;
    }
    get budget() {
        return this._budget;
    }

    set budget(budget: IBudget) {
        this._budget = budget;
    }
}
