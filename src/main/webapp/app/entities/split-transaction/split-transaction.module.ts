import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FinancialdashboardSharedModule } from 'app/shared';
import {
    SplitTransactionComponent,
    SplitTransactionDetailComponent,
    SplitTransactionUpdateComponent,
    SplitTransactionDeletePopupComponent,
    SplitTransactionDeleteDialogComponent,
    splitTransactionRoute,
    splitTransactionPopupRoute
} from './';

const ENTITY_STATES = [...splitTransactionRoute, ...splitTransactionPopupRoute];

@NgModule({
    imports: [FinancialdashboardSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SplitTransactionComponent,
        SplitTransactionDetailComponent,
        SplitTransactionUpdateComponent,
        SplitTransactionDeleteDialogComponent,
        SplitTransactionDeletePopupComponent
    ],
    entryComponents: [
        SplitTransactionComponent,
        SplitTransactionUpdateComponent,
        SplitTransactionDeleteDialogComponent,
        SplitTransactionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FinancialdashboardSplitTransactionModule {}
