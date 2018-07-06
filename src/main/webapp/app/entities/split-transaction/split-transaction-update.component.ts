import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISplitTransaction } from 'app/shared/model/split-transaction.model';
import { SplitTransactionService } from './split-transaction.service';
import { ITransactionIng } from 'app/shared/model/transaction-ing.model';
import { TransactionIngService } from 'app/entities/transaction-ing';

@Component({
    selector: 'jhi-split-transaction-update',
    templateUrl: './split-transaction-update.component.html'
})
export class SplitTransactionUpdateComponent implements OnInit {
    private _splitTransaction: ISplitTransaction;
    isSaving: boolean;

    transactionings: ITransactionIng[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private splitTransactionService: SplitTransactionService,
        private transactionIngService: TransactionIngService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ splitTransaction }) => {
            this.splitTransaction = splitTransaction;
        });
        this.transactionIngService.query().subscribe(
            (res: HttpResponse<ITransactionIng[]>) => {
                this.transactionings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.splitTransaction.id !== undefined) {
            this.subscribeToSaveResponse(this.splitTransactionService.update(this.splitTransaction));
        } else {
            this.subscribeToSaveResponse(this.splitTransactionService.create(this.splitTransaction));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISplitTransaction>>) {
        result.subscribe((res: HttpResponse<ISplitTransaction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTransactionIngById(index: number, item: ITransactionIng) {
        return item.id;
    }
    get splitTransaction() {
        return this._splitTransaction;
    }

    set splitTransaction(splitTransaction: ISplitTransaction) {
        this._splitTransaction = splitTransaction;
    }
}
