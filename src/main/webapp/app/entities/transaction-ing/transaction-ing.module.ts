import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FinancialdashboardSharedModule } from 'app/shared';
import {
    TransactionIngComponent,
    TransactionIngDetailComponent,
    TransactionIngUpdateComponent,
    TransactionIngDeletePopupComponent,
    TransactionIngDeleteDialogComponent,
    transactionIngRoute,
    transactionIngPopupRoute
} from './';

const ENTITY_STATES = [...transactionIngRoute, ...transactionIngPopupRoute];

@NgModule({
    imports: [FinancialdashboardSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransactionIngComponent,
        TransactionIngDetailComponent,
        TransactionIngUpdateComponent,
        TransactionIngDeleteDialogComponent,
        TransactionIngDeletePopupComponent
    ],
    entryComponents: [
        TransactionIngComponent,
        TransactionIngUpdateComponent,
        TransactionIngDeleteDialogComponent,
        TransactionIngDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FinancialdashboardTransactionIngModule {}
