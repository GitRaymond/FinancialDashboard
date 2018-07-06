import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVendor } from 'app/shared/model/vendor.model';
import { Principal } from 'app/core';
import { VendorService } from './vendor.service';

@Component({
    selector: 'jhi-vendor',
    templateUrl: './vendor.component.html'
})
export class VendorComponent implements OnInit, OnDestroy {
    vendors: IVendor[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private vendorService: VendorService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.vendorService.query().subscribe(
            (res: HttpResponse<IVendor[]>) => {
                this.vendors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVendors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVendor) {
        return item.id;
    }

    registerChangeInVendors() {
        this.eventSubscriber = this.eventManager.subscribe('vendorListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
