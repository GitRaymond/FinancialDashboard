import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITransactionIng } from 'app/shared/model/transaction-ing.model';

@Component({
    selector: 'jhi-transaction-ing-detail',
    templateUrl: './transaction-ing-detail.component.html'
})
export class TransactionIngDetailComponent implements OnInit {
    transactionIng: ITransactionIng;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transactionIng }) => {
            this.transactionIng = transactionIng;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
