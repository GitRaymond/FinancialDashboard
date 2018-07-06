import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISplitTransaction } from 'app/shared/model/split-transaction.model';

@Component({
    selector: 'jhi-split-transaction-detail',
    templateUrl: './split-transaction-detail.component.html'
})
export class SplitTransactionDetailComponent implements OnInit {
    splitTransaction: ISplitTransaction;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ splitTransaction }) => {
            this.splitTransaction = splitTransaction;
        });
    }

    previousState() {
        window.history.back();
    }
}
