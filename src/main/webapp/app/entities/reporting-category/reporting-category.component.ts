import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IReportingCategory } from 'app/shared/model/reporting-category.model';
import { Principal } from 'app/core';
import { ReportingCategoryService } from './reporting-category.service';

@Component({
    selector: 'jhi-reporting-category',
    templateUrl: './reporting-category.component.html'
})
export class ReportingCategoryComponent implements OnInit, OnDestroy {
    reportingCategories: IReportingCategory[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private reportingCategoryService: ReportingCategoryService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.reportingCategoryService.query().subscribe(
            (res: HttpResponse<IReportingCategory[]>) => {
                this.reportingCategories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInReportingCategories();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IReportingCategory) {
        return item.id;
    }

    registerChangeInReportingCategories() {
        this.eventSubscriber = this.eventManager.subscribe('reportingCategoryListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
