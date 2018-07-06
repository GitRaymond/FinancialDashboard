import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISplitTransaction } from 'app/shared/model/split-transaction.model';
import { Principal } from 'app/core';
import { SplitTransactionService } from './split-transaction.service';

@Component({
    selector: 'jhi-split-transaction',
    templateUrl: './split-transaction.component.html'
})
export class SplitTransactionComponent implements OnInit, OnDestroy {
    splitTransactions: ISplitTransaction[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private splitTransactionService: SplitTransactionService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.splitTransactionService.query().subscribe(
            (res: HttpResponse<ISplitTransaction[]>) => {
                this.splitTransactions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSplitTransactions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISplitTransaction) {
        return item.id;
    }

    registerChangeInSplitTransactions() {
        this.eventSubscriber = this.eventManager.subscribe('splitTransactionListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
