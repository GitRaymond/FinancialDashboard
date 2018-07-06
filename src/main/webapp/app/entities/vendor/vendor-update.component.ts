import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from './vendor.service';

@Component({
    selector: 'jhi-vendor-update',
    templateUrl: './vendor-update.component.html'
})
export class VendorUpdateComponent implements OnInit {
    private _vendor: IVendor;
    isSaving: boolean;

    constructor(private vendorService: VendorService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ vendor }) => {
            this.vendor = vendor;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.vendor.id !== undefined) {
            this.subscribeToSaveResponse(this.vendorService.update(this.vendor));
        } else {
            this.subscribeToSaveResponse(this.vendorService.create(this.vendor));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVendor>>) {
        result.subscribe((res: HttpResponse<IVendor>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get vendor() {
        return this._vendor;
    }

    set vendor(vendor: IVendor) {
        this._vendor = vendor;
    }
}
