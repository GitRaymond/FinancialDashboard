import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FinancialdashboardSharedModule } from 'app/shared';
import {
    ReportingCategoryComponent,
    ReportingCategoryDetailComponent,
    ReportingCategoryUpdateComponent,
    ReportingCategoryDeletePopupComponent,
    ReportingCategoryDeleteDialogComponent,
    reportingCategoryRoute,
    reportingCategoryPopupRoute
} from './';

const ENTITY_STATES = [...reportingCategoryRoute, ...reportingCategoryPopupRoute];

@NgModule({
    imports: [FinancialdashboardSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReportingCategoryComponent,
        ReportingCategoryDetailComponent,
        ReportingCategoryUpdateComponent,
        ReportingCategoryDeleteDialogComponent,
        ReportingCategoryDeletePopupComponent
    ],
    entryComponents: [
        ReportingCategoryComponent,
        ReportingCategoryUpdateComponent,
        ReportingCategoryDeleteDialogComponent,
        ReportingCategoryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FinancialdashboardReportingCategoryModule {}
