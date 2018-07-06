import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Vendor } from 'app/shared/model/vendor.model';
import { VendorService } from './vendor.service';
import { VendorComponent } from './vendor.component';
import { VendorDetailComponent } from './vendor-detail.component';
import { VendorUpdateComponent } from './vendor-update.component';
import { VendorDeletePopupComponent } from './vendor-delete-dialog.component';
import { IVendor } from 'app/shared/model/vendor.model';

@Injectable({ providedIn: 'root' })
export class VendorResolve implements Resolve<IVendor> {
    constructor(private service: VendorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((vendor: HttpResponse<Vendor>) => vendor.body);
        }
        return Observable.of(new Vendor());
    }
}

export const vendorRoute: Routes = [
    {
        path: 'vendor',
        component: VendorComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.vendor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vendor/:id/view',
        component: VendorDetailComponent,
        resolve: {
            vendor: VendorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.vendor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vendor/new',
        component: VendorUpdateComponent,
        resolve: {
            vendor: VendorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.vendor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'vendor/:id/edit',
        component: VendorUpdateComponent,
        resolve: {
            vendor: VendorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.vendor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vendorPopupRoute: Routes = [
    {
        path: 'vendor/:id/delete',
        component: VendorDeletePopupComponent,
        resolve: {
            vendor: VendorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.vendor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
