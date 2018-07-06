import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SplitTransaction } from 'app/shared/model/split-transaction.model';
import { SplitTransactionService } from './split-transaction.service';
import { SplitTransactionComponent } from './split-transaction.component';
import { SplitTransactionDetailComponent } from './split-transaction-detail.component';
import { SplitTransactionUpdateComponent } from './split-transaction-update.component';
import { SplitTransactionDeletePopupComponent } from './split-transaction-delete-dialog.component';
import { ISplitTransaction } from 'app/shared/model/split-transaction.model';

@Injectable({ providedIn: 'root' })
export class SplitTransactionResolve implements Resolve<ISplitTransaction> {
    constructor(private service: SplitTransactionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((splitTransaction: HttpResponse<SplitTransaction>) => splitTransaction.body);
        }
        return Observable.of(new SplitTransaction());
    }
}

export const splitTransactionRoute: Routes = [
    {
        path: 'split-transaction',
        component: SplitTransactionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.splitTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'split-transaction/:id/view',
        component: SplitTransactionDetailComponent,
        resolve: {
            splitTransaction: SplitTransactionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.splitTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'split-transaction/new',
        component: SplitTransactionUpdateComponent,
        resolve: {
            splitTransaction: SplitTransactionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.splitTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'split-transaction/:id/edit',
        component: SplitTransactionUpdateComponent,
        resolve: {
            splitTransaction: SplitTransactionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.splitTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const splitTransactionPopupRoute: Routes = [
    {
        path: 'split-transaction/:id/delete',
        component: SplitTransactionDeletePopupComponent,
        resolve: {
            splitTransaction: SplitTransactionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.splitTransaction.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
