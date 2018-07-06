import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ITransactionIng } from 'app/shared/model/transaction-ing.model';
import { TransactionIngService } from './transaction-ing.service';
import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from 'app/entities/vendor';
import { IBankAccount } from 'app/shared/model/bank-account.model';
import { BankAccountService } from 'app/entities/bank-account';
import { IReportingCategory } from 'app/shared/model/reporting-category.model';
import { ReportingCategoryService } from 'app/entities/reporting-category';
import { ITag } from 'app/shared/model/tag.model';
import { TagService } from 'app/entities/tag';

@Component({
    selector: 'jhi-transaction-ing-update',
    templateUrl: './transaction-ing-update.component.html'
})
export class TransactionIngUpdateComponent implements OnInit {
    private _transactionIng: ITransactionIng;
    isSaving: boolean;

    vendors: IVendor[];

    bankaccounts: IBankAccount[];

    reportingcategories: IReportingCategory[];

    tags: ITag[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private transactionIngService: TransactionIngService,
        private vendorService: VendorService,
        private bankAccountService: BankAccountService,
        private reportingCategoryService: ReportingCategoryService,
        private tagService: TagService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ transactionIng }) => {
            this.transactionIng = transactionIng;
        });
        this.vendorService.query().subscribe(
            (res: HttpResponse<IVendor[]>) => {
                this.vendors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bankAccountService.query().subscribe(
            (res: HttpResponse<IBankAccount[]>) => {
                this.bankaccounts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.reportingCategoryService.query().subscribe(
            (res: HttpResponse<IReportingCategory[]>) => {
                this.reportingcategories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tagService.query().subscribe(
            (res: HttpResponse<ITag[]>) => {
                this.tags = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.transactionIng.id !== undefined) {
            this.subscribeToSaveResponse(this.transactionIngService.update(this.transactionIng));
        } else {
            this.subscribeToSaveResponse(this.transactionIngService.create(this.transactionIng));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITransactionIng>>) {
        result.subscribe((res: HttpResponse<ITransactionIng>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackVendorById(index: number, item: IVendor) {
        return item.id;
    }

    trackBankAccountById(index: number, item: IBankAccount) {
        return item.id;
    }

    trackReportingCategoryById(index: number, item: IReportingCategory) {
        return item.id;
    }

    trackTagById(index: number, item: ITag) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get transactionIng() {
        return this._transactionIng;
    }

    set transactionIng(transactionIng: ITransactionIng) {
        this._transactionIng = transactionIng;
    }
}
