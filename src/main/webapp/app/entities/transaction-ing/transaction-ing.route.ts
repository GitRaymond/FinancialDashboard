import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { TransactionIng } from 'app/shared/model/transaction-ing.model';
import { TransactionIngService } from './transaction-ing.service';
import { TransactionIngComponent } from './transaction-ing.component';
import { TransactionIngDetailComponent } from './transaction-ing-detail.component';
import { TransactionIngUpdateComponent } from './transaction-ing-update.component';
import { TransactionIngDeletePopupComponent } from './transaction-ing-delete-dialog.component';
import { ITransactionIng } from 'app/shared/model/transaction-ing.model';

@Injectable({ providedIn: 'root' })
export class TransactionIngResolve implements Resolve<ITransactionIng> {
    constructor(private service: TransactionIngService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((transactionIng: HttpResponse<TransactionIng>) => transactionIng.body);
        }
        return Observable.of(new TransactionIng());
    }
}

export const transactionIngRoute: Routes = [
    {
        path: 'transaction-ing',
        component: TransactionIngComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.transactionIng.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transaction-ing/:id/view',
        component: TransactionIngDetailComponent,
        resolve: {
            transactionIng: TransactionIngResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.transactionIng.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transaction-ing/new',
        component: TransactionIngUpdateComponent,
        resolve: {
            transactionIng: TransactionIngResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.transactionIng.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transaction-ing/:id/edit',
        component: TransactionIngUpdateComponent,
        resolve: {
            transactionIng: TransactionIngResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.transactionIng.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transactionIngPopupRoute: Routes = [
    {
        path: 'transaction-ing/:id/delete',
        component: TransactionIngDeletePopupComponent,
        resolve: {
            transactionIng: TransactionIngResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.transactionIng.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
