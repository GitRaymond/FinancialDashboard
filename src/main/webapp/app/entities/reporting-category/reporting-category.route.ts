import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ReportingCategory } from 'app/shared/model/reporting-category.model';
import { ReportingCategoryService } from './reporting-category.service';
import { ReportingCategoryComponent } from './reporting-category.component';
import { ReportingCategoryDetailComponent } from './reporting-category-detail.component';
import { ReportingCategoryUpdateComponent } from './reporting-category-update.component';
import { ReportingCategoryDeletePopupComponent } from './reporting-category-delete-dialog.component';
import { IReportingCategory } from 'app/shared/model/reporting-category.model';

@Injectable({ providedIn: 'root' })
export class ReportingCategoryResolve implements Resolve<IReportingCategory> {
    constructor(private service: ReportingCategoryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((reportingCategory: HttpResponse<ReportingCategory>) => reportingCategory.body);
        }
        return Observable.of(new ReportingCategory());
    }
}

export const reportingCategoryRoute: Routes = [
    {
        path: 'reporting-category',
        component: ReportingCategoryComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.reportingCategory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reporting-category/:id/view',
        component: ReportingCategoryDetailComponent,
        resolve: {
            reportingCategory: ReportingCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.reportingCategory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reporting-category/new',
        component: ReportingCategoryUpdateComponent,
        resolve: {
            reportingCategory: ReportingCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.reportingCategory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reporting-category/:id/edit',
        component: ReportingCategoryUpdateComponent,
        resolve: {
            reportingCategory: ReportingCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.reportingCategory.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const reportingCategoryPopupRoute: Routes = [
    {
        path: 'reporting-category/:id/delete',
        component: ReportingCategoryDeletePopupComponent,
        resolve: {
            reportingCategory: ReportingCategoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'financialdashboardApp.reportingCategory.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
